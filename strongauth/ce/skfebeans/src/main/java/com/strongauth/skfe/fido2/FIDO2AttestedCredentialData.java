/**
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
 * Copyright (c) 2001-2015 StrongAuth, Inc.
 *
 * $Date: 2018-06-18 14:47:15 -0400 (Mon, 18 Jun 2018) $
 * $Revision: 50 $
 * $Author: pmarathe $
 * $URL: https://svn.strongkey.com/repos/topaz4/branches/preFIDO2/strongauth/ce/skcebeans/src/main/java/com/strongauth/skce/fido2/FIDO2AttestedCredentialData.java $
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
 */
package com.strongauth.skfe.fido2;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORParser;
import com.strongauth.crypto.utility.cryptoCommon;
import com.strongauth.skfe.utilities.skfeCommon;
import com.strongauth.skfe.utilities.skfeConstants;
import com.strongauth.skfe.utilities.skfeLogger;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Map;
import java.util.logging.Level;
import org.bouncycastle.util.encoders.Base64;

public class FIDO2AttestedCredentialData {

    private byte[] aaguid;

    private byte[] credentialId;

    private int length;

    private FIDO2KeyObject fko;

    private PublicKey publicKey;

    public byte[] getAaguid() {
        return aaguid;
    }

    public byte[] getCredentialId() {
        return credentialId;
    }

    public int getLength() {
        return length;
    }

    public FIDO2KeyObject getFko() {
        return fko;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    //Returns size of FIDO2AttestedCredentialData
    public int decodeAttCredData(byte[] data) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException, InvalidParameterSpecException {
        int remainingDataIndex = 0;

        aaguid = new byte[16];
        credentialId = new byte[]{};
        System.arraycopy(data, 0, aaguid, 0, 16);
        remainingDataIndex += 16;

        byte[] lengthValue = new byte[2];
        System.arraycopy(data, remainingDataIndex, lengthValue, 0, 2);
        remainingDataIndex += 2;
        length = ByteBuffer.wrap(lengthValue).getShort();

        credentialId = new byte[length];
        System.arraycopy(data, remainingDataIndex, credentialId, 0, length);
        remainingDataIndex += length;

        skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "AAGUID : " + Base64.toBase64String(aaguid));
        byte[] cbor = new byte[data.length - remainingDataIndex];
        System.arraycopy(data, remainingDataIndex, cbor, 0, data.length - remainingDataIndex);

        skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "cbor (hex): \n" + bytesToHexString(cbor, cbor.length));
        CBORFactory f = new CBORFactory();
        ObjectMapper mapper = new ObjectMapper(f);
        int kty = 0;
        CBORParser parser = f.createParser(cbor);
        Map<String, Object> pkObjectMap = mapper.readValue(parser, new TypeReference<Map<String, Object>>() {
        });
        
        skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "pkObjectMap: ");
        for(String key: pkObjectMap.keySet()){
            skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001",
                    "Key: " + key + ", Object: " + pkObjectMap.get(key).toString());
        }
        kty = (int) pkObjectMap.get("1");
        skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "KTY = " + kty);
        if (kty == 2) {
            ECKeyObject eck = new ECKeyObject();
            eck.decode(cbor);
            
            int crv = eck.getCrv();
            skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "crv = " + crv);
            String curveString = skfeCommon.getCurveFromFIDOECCCurveID(crv);
            skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "curveString = " + curveString);
            skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "X = " + org.bouncycastle.util.encoders.Hex.toHexString(eck.getX()));
            skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "Y = " + org.bouncycastle.util.encoders.Hex.toHexString(eck.getY()));
            publicKey = cryptoCommon.getUserECPublicKey(eck.getX(), eck.getY(), curveString);

            fko = eck;
        } else {
            RSAKeyObject rko = new RSAKeyObject();
            rko.decode(cbor);
            
            
            RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(1,rko.getN()), new BigInteger(1,rko.getE()));
            publicKey = KeyFactory.getInstance("RSA").generatePublic(spec);
            
            fko = rko;
        }
        
        //Return size of AttestedCredentialData
        skfeLogger.log(skfeConstants.SKFE_LOGGER, Level.FINE, "FIDO-MSG-2001", 
                    "FIDO2AttestedCredentialData size (bytes: " + parser.getCurrentLocation().getByteOffset());
        return remainingDataIndex + (int) parser.getCurrentLocation().getByteOffset();
    }
    
    private static String bytesToHexString(byte[] rawBytes, int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            if (i % 16 == 0) {
                sb.append('\n');
            }
            sb.append(String.format("%02x ", rawBytes[i]));
        }
        return sb.toString();
    }
}