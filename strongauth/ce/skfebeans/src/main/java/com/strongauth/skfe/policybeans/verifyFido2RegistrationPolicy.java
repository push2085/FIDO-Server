/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License, as published by the Free Software Foundation and
 * available at http://www.fsf.org/licensing/licenses/lgpl.html,
 * version 2.1 or above.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2001-2018 StrongAuth, Inc.
 *
 * $Date: 
 * $Revision:
 * $Author: mishimoto $
 * $URL: 
 *
 * *********************************************
 *                    888
 *                    888
 *                    888
 *  88888b.   .d88b.  888888  .d88b.  .d8888b
 *  888 "88b d88""88b 888    d8P  Y8b 88K
 *  888  888 888  888 888    88888888 "Y8888b.
 *  888  888 Y88..88P Y88b.  Y8b.          X88
 *  888  888  "Y88P"   "Y888  "Y8888   88888P'
 *
 * *********************************************
 * 
 *
 *
 */
package com.strongauth.skfe.policybeans;

import com.google.common.primitives.Longs;
import com.strongauth.skfe.utilities.skfeLogger;
import com.strongauth.crypto.utility.cryptoCommon;
import com.strongauth.skce.pojos.MDSClient;
import com.strongauth.skfe.fido.policyobjects.CounterPolicyOptions;
import com.strongauth.skfe.fido.policyobjects.CryptographyPolicyOptions;
import com.strongauth.skfe.fido.policyobjects.FidoPolicyObject;
import com.strongauth.skfe.fido.policyobjects.MdsPolicyOptions;
import com.strongauth.skfe.fido.policyobjects.RegistrationPolicyOptions;
import com.strongauth.skfe.fido.policyobjects.RpPolicyOptions;
import com.strongauth.skfe.fido2.ECKeyObject;
import com.strongauth.skfe.fido2.FIDO2AttestationObject;
import com.strongauth.skce.pojos.UserSessionInfo;
import com.strongauth.skce.utilities.PKIXChainValidation;
import com.strongauth.skfe.utilities.SKFEException;
import com.strongauth.skfe.utilities.skfeCommon;
import com.strongauth.skfe.utilities.skfeConstants;
import com.strongauth.skfe.fido2.FIDO2AttestationStatement;
import com.strongauth.skfe.pojos.FidoPolicyMDSObject;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.JsonArray;
import javax.json.JsonObject;

@Stateless
public class verifyFido2RegistrationPolicy implements verifyFido2RegistrationPolicyLocal {

    @EJB
    getCachedFidoPolicyMDSLocal getpolicybean;
    
    @Override
    public void execute(UserSessionInfo userInfo, JsonObject clientJson, FIDO2AttestationObject attObject) throws SKFEException {
        try{
            //Get policy from userInfo
            FidoPolicyMDSObject fidoPolicyMDS = getpolicybean.getByMapKey(userInfo.getPolicyMapKey());
            FidoPolicyObject fidoPolicy = fidoPolicyMDS.getFp();
            
            verifyCryptographyOptions(fidoPolicy.getCryptographyOptions(), clientJson, attObject, fidoPolicy.getVersion());
            verifyRpOptions(fidoPolicy.getRpOptions(), clientJson, attObject, fidoPolicy.getVersion());
            verifyTimeout(fidoPolicy.getTimeout(), clientJson, attObject, fidoPolicy.getVersion());
            verifyMDS(fidoPolicy.getMdsOptions(), clientJson, attObject, fidoPolicyMDS.getMds(), fidoPolicy.getVersion());
            verifyTokenBinding(fidoPolicy.getTokenBindingOption(), clientJson, attObject, fidoPolicy.getVersion());
            verifyCounter(fidoPolicy.getCounterOptions(), clientJson, attObject, fidoPolicy.getVersion());
            verifyUserSettings(fidoPolicy.isUserSettingsRequired(), clientJson, attObject, fidoPolicy.getVersion());
            handleSignature(fidoPolicy.isStoreSignatures(), clientJson, attObject, fidoPolicy.getVersion());
            verifyRegistration(fidoPolicy.getRegistrationOptions(), clientJson, attObject, 
                    userInfo.getUserVerificationReq(), userInfo.getAttestationPreferance(), fidoPolicy.getVersion());
        }
        catch(Exception ex){
            ex.printStackTrace();
            skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.SEVERE, "FIDO-MSG-0053", ex.getLocalizedMessage());
            throw new SKFEException(ex.getLocalizedMessage());
        }
    }
    
    //TODO move private functions to be public functions of PolicyOption Objects
    //Currently blocked by the need to move more objects to common.
    
    //TODO refactor exceptions to have standard error messages
    private void verifyCryptographyOptions(CryptographyPolicyOptions cryptoOp, JsonObject clientJson, 
            FIDO2AttestationObject attObject, Integer version) throws SKFEException {
        ArrayList<String> allowedRSASignatures = cryptoOp.getAllowedRSASignatures();
        ArrayList<String> allowedECSignatures = cryptoOp.getAllowedECSignatures();
        ArrayList<String> supportedCurves = cryptoOp.getSupportedEllipticCurves();
        ArrayList<String> allowedAttestationFormats = cryptoOp.getAllowedAttestationFormats();
        ArrayList<String> allowedAttestationTypes = cryptoOp.getAllowedAttestationTypes();

        //Verify attestation key
        ArrayList certificateChain = attObject.getAttStmt().getX5c();
        if(certificateChain != null){
            X509Certificate attestationCert = cryptoCommon.generateX509FromBytes((byte[]) certificateChain.get(0));
            
            if(attestationCert == null){
                throw new SKFEException("Failed to parse X509Certificate. Check logs for details");
            }
            PublicKey attestationKey = attestationCert.getPublicKey();
            String attestationAlgType = attestationKey.getAlgorithm();
            if(!attestationAlgType.equalsIgnoreCase("RSA") && !attestationAlgType.equalsIgnoreCase("EC")){
                throw new SKFEException("Unknown key algorithm (Attestation)");
            }
            if((allowedRSASignatures == null
                    || !allowedRSASignatures.contains(skfeCommon.getPolicyAlgFromAlg(attestationCert.getSigAlgName())))
                    && (allowedECSignatures == null
                    || !allowedECSignatures.contains(skfeCommon.getPolicyAlgFromAlg(attestationCert.getSigAlgName())))){
                throw new SKFEException("Signature Algorithm not supported by policy (Attestation): " + attestationCert.getSigAlgName());
            }

            //TODO verify that the curve used by the attestation key is approved
//                if(algorithmType.equalsIgnoreCase("EC")){
//                }
        }

        //Verify signing key
        PublicKey signingKey = attObject.getAuthData().getAttCredData().getPublicKey();
        String signingAlgType = signingKey.getAlgorithm();
        if(!signingAlgType.equalsIgnoreCase("RSA") && !signingAlgType.equalsIgnoreCase("EC")){
            throw new SKFEException("Unknown attestation key algorithm (Signing)");
        }
        if((allowedRSASignatures == null
                || !allowedRSASignatures.contains(skfeCommon.getPolicyAlgFromIANACOSEAlg(attObject.getAuthData().getAttCredData().getFko().getAlg())))
                && (allowedECSignatures == null
                ||!allowedECSignatures.contains(skfeCommon.getPolicyAlgFromIANACOSEAlg(attObject.getAuthData().getAttCredData().getFko().getAlg())))){
            throw new SKFEException("Rejected key algorithm (Signing): " +
                    skfeCommon.getPolicyAlgFromIANACOSEAlg(attObject.getAuthData().getAttCredData().getFko().getAlg()));
        }
        if(signingAlgType.equalsIgnoreCase("EC")){
            ECKeyObject eckey = (ECKeyObject) attObject.getAuthData().getAttCredData().getFko();
            if(!supportedCurves.contains(skfeCommon.getPolicyCurveFromFIDOECCCurveID(eckey.getCrv()))){
                throw new SKFEException("Signature Algorithm not supported by policy (Signing)");
            }
        }
        
        //Verify allowed AttestationFormat
        if(!allowedAttestationFormats.contains(attObject.getAttFormat())){
            throw new SKFEException("Attestation format not supported by policy: " + attObject.getAttFormat());
        }
        
        //Verify allowed AttestationType
        if (!allowedAttestationTypes.contains(attObject.getAttStmt().getAttestationType())) {
            throw new SKFEException("Attestation type not supported by policy: " + attObject.getAttStmt().getAttestationType());
        }
    }
    
    //Currently no checks on the RP options
    private void verifyRpOptions(RpPolicyOptions rpOp, JsonObject clientJson, 
            FIDO2AttestationObject attObject, Integer version) throws SKFEException {
    }
    
    private void verifyTimeout(Integer timeoutOp, JsonObject clientJson, 
            FIDO2AttestationObject attObject, Integer version) throws SKFEException {
    }
    
    //TODO implement MDS for checking
    //TODO simplify logic
    private void verifyMDS(MdsPolicyOptions mdsOp, JsonObject clientJson, 
            FIDO2AttestationObject attObject, MDSClient mds, Integer version) throws SKFEException, CertificateException, NoSuchProviderException{
        //MDS not configured, skipping checks
        if(mdsOp == null || mds == null){
            return;
        }
        
        boolean isPolicyQualifiersRejected = true;
        byte[] aaguidbytes = attObject.getAuthData().getAttCredData().getAaguid();
        byte[] aaguidbytes1 = new byte[8];
        byte[] aaguidbytes2 = new byte[8];
        System.arraycopy(aaguidbytes, 0, aaguidbytes1, 0, 8);
        System.arraycopy(aaguidbytes, 8, aaguidbytes2, 0, 8);
        UUID uuid = new UUID(Longs.fromByteArray(aaguidbytes1),
                Longs.fromByteArray(aaguidbytes2));
        JsonObject trustAnchors = mds.getTrustAnchors(uuid.toString());
        
        FIDO2AttestationStatement attStmt = attObject.getAttStmt();
        //TODO check that none attestation is supported.
        if(attStmt == null){
            return;
        }
        
        //TODO check that fido-u2f attestation is supported
        if(attObject.getAttFormat().equals("fido-u2f")){
            return;
        }
        
        if (attObject.getAttFormat().equals("tpm")) {
            isPolicyQualifiersRejected = false;
        }
        
        //TODO check
        List<Certificate> certchain = new ArrayList<>();
        ArrayList attBytesChain = attObject.getAttStmt().getX5c();
        if(attBytesChain == null || attBytesChain.isEmpty()){
            return;
        }
        
        //TODO check that self attestation is supported.
        X509Certificate leafCert = cryptoCommon.generateX509FromBytes((byte[]) attBytesChain.get(0)); //check leaf if it is self signed
        certchain.add(leafCert);
        if(leafCert.getSubjectDN().equals(leafCert.getIssuerDN())){
            return;
        }
        
            
        //Create certificate path
        if (!attBytesChain.isEmpty()) {
            for (int attCertIndex = 1; attCertIndex < attBytesChain.size(); attCertIndex++) {
                X509Certificate attestationCert = cryptoCommon.generateX509FromBytes((byte[]) attBytesChain.get(attCertIndex));
                skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001",
                        "CertPath " + attCertIndex + ": " + attestationCert);
                certchain.add(attestationCert);
            }
        } else {
            throw new IllegalArgumentException("Expected Certificate chain missing");
        }
        CertPath certPath = CertificateFactory.getInstance("X.509", "BCFIPS").generateCertPath(certchain);
        
        //Create list of possible roots from MDS
        Set<TrustAnchor> rootAnchors = new HashSet<>();
        JsonArray roots = trustAnchors.getJsonArray("attestationRootCertificates");
        
        //TODO perform comprehensive checks on errors
        JsonArray errors = trustAnchors.getJsonArray("errors");
        if(!errors.isEmpty()){
            throw new IllegalArgumentException("MDS error(s): " + errors.toString());
        }
        
        //TODO handle case where aaguid is not in MDS
        if(roots == null){
            throw new IllegalArgumentException("Root certificates not found in MDS");
        }
        for(int rootIndex = 0; rootIndex < roots.size(); rootIndex++) {
            byte[] certBytes = java.util.Base64.getDecoder().decode(roots.getString(rootIndex));
            rootAnchors.add(new TrustAnchor(cryptoCommon.generateX509FromBytes(certBytes), null));
        }
        
        //Verify chain chains up to one of the roots.
        if(!PKIXChainValidation.pkixvalidate(certPath, rootAnchors, false, isPolicyQualifiersRejected)){    //TODO check CRLs if they exist, otherwise don't
            throw new IllegalArgumentException("Failed to verify certificate path");
        }
        
        //TODO att ECDAA attestation
    }
    
    //TODO expand checks as token binding spec changes
    private void verifyTokenBinding(String tokenBindingOp, JsonObject clientJson,
            FIDO2AttestationObject attObject, Integer version) throws SKFEException {
        JsonObject tokenBinding = clientJson.getJsonObject(skfeConstants.JSON_KEY_TOKENBINDING);
        if(tokenBindingOp != null){
            if(tokenBinding == null){
                throw new SKFEException("Policy requires Token Binding");
            }
            String tokenBindingStatus = tokenBinding.getString("status", null);
            if(!tokenBindingOp.equalsIgnoreCase(tokenBindingStatus)){
                throw new SKFEException("Returned token binding does not match policy (" 
                        + tokenBindingStatus + " != " + tokenBindingOp + ")");
            }
        }
    }
    
    //Currently no checks on the Counter options
    private void verifyCounter(CounterPolicyOptions counterOp, JsonObject clientJson,
            FIDO2AttestationObject attObject, Integer version) throws SKFEException {
    }
    
    //Currently no checks on the UserSettings options
    private void verifyUserSettings(Boolean userOp, JsonObject clientJson,
            FIDO2AttestationObject attObject, Integer version){
    }
    
    //TODO store registration signature if required
    private void handleSignature(Boolean signatureOp, JsonObject clientJson,
            FIDO2AttestationObject attObject, Integer version){
    }
    
    private void verifyRegistration(RegistrationPolicyOptions regOp, JsonObject clientJson,
            FIDO2AttestationObject attObject, String userVerificationReq, String attestationPreference,
            Integer version) throws SKFEException {
        //Default blank to Webauthn defined defaults
        userVerificationReq = (userVerificationReq == null) ? skfeConstants.POLICY_CONST_PREFERRED : userVerificationReq;
        attestationPreference = (attestationPreference == null) ? skfeConstants.POLICY_CONST_NONE : attestationPreference;
        
        //Double check that what was stored in UserSessionInfo is valid for the policy
        if(regOp.getAuthenticatorSelection() == null){
            throw new IllegalArgumentException("Policy Exception: Null policy");
        }
        if(!regOp.getAuthenticatorSelection().getUserVerification().contains(userVerificationReq)){
            throw new IllegalArgumentException("Policy Exception: Prereg userVerificationRequirement does not meet policy");
        }
        if(!regOp.getAttestation().contains(attestationPreference)){
            throw new IllegalArgumentException("Policy Exception: Prereg AttestationConveyancePreference does not meet policy");
        }
        
        //If None attestation was requested (or defaulted to), ensure None attestation is given
        //+ no attestation data is given. Conformance requirement.
        if (attestationPreference.equalsIgnoreCase(skfeConstants.POLICY_CONST_NONE)
                && attObject.getAttFormat().equalsIgnoreCase(userVerificationReq)) {
            throw new SKFEException("Policy requested none attestation, was given attestation");
        }
        
        //If User Verification was required, verify it was provided
        if(userVerificationReq.equalsIgnoreCase(skfeConstants.POLICY_CONST_REQUIRED) && !attObject.getAuthData().isUserVerified()){
            throw new SKFEException("User Verification required by policy");
        }
        
        //If User Verification was required, verify it was provided
        if(userVerificationReq.equalsIgnoreCase(skfeConstants.POLICY_CONST_REQUIRED) && !attObject.getAuthData().isUserVerified()){
            throw new SKFEException("User Verification required by policy");
        }
        
        //TODO other checks?
    }
}