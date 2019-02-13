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
 * Copyright (c) 2001-2018 StrongAuth, Inc.
 *
 * $Date$
 * $Revision$
 * $Author$
 * $URL$
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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.07.23 at 04:16:45 PM PDT 
//


package com.strongauth.skse.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Domains" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="did" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                   &lt;element name="KeyRequestMaster" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="krmid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="projectid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="KeyRequestDetail" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="krdid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                                       &lt;element name="projectid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                                       &lt;element name="kpid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                                       &lt;element name="trsmid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                                       &lt;element name="requester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="requesttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="cn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="ou" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="validity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                                       &lt;element name="istargetgroup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                                       &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="received" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="finished" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="errormsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "domains"
})
@XmlRootElement(name = "listKeyRequestsResponse")
public class ListKeyRequestsResponse {

    @XmlElement(name = "Domains")
    protected List<ListKeyRequestsResponse.Domains> domains;

    /**
     * Gets the value of the domains property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the domains property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDomains().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListKeyRequestsResponse.Domains }
     * 
     * 
     */
    public List<ListKeyRequestsResponse.Domains> getDomains() {
        if (domains == null) {
            domains = new ArrayList<ListKeyRequestsResponse.Domains>();
        }
        return this.domains;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="did" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *         &lt;element name="KeyRequestMaster" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="krmid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="projectid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="KeyRequestDetail" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="krdid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                             &lt;element name="projectid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                             &lt;element name="kpid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                             &lt;element name="trsmid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                             &lt;element name="requester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="requesttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="cn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="ou" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="validity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                             &lt;element name="istargetgroup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                             &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="received" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="finished" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="errormsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "did",
        "keyRequestMaster"
    })
    public static class Domains {

        protected Long did;
        @XmlElement(name = "KeyRequestMaster")
        protected List<ListKeyRequestsResponse.Domains.KeyRequestMaster> keyRequestMaster;

        /**
         * Gets the value of the did property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getDid() {
            return did;
        }

        /**
         * Sets the value of the did property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setDid(Long value) {
            this.did = value;
        }

        /**
         * Gets the value of the keyRequestMaster property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the keyRequestMaster property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getKeyRequestMaster().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ListKeyRequestsResponse.Domains.KeyRequestMaster }
         * 
         * 
         */
        public List<ListKeyRequestsResponse.Domains.KeyRequestMaster> getKeyRequestMaster() {
            if (keyRequestMaster == null) {
                keyRequestMaster = new ArrayList<ListKeyRequestsResponse.Domains.KeyRequestMaster>();
            }
            return this.keyRequestMaster;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="krmid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="projectid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="KeyRequestDetail" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="krdid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *                   &lt;element name="projectid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *                   &lt;element name="kpid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *                   &lt;element name="trsmid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *                   &lt;element name="requester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="requesttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="cn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="ou" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="validity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *                   &lt;element name="istargetgroup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *                   &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="received" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="finished" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="errormsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "krmid",
            "projectid",
            "keyRequestDetail"
        })
        public static class KeyRequestMaster {

            protected Long krmid;
            protected Long projectid;
            @XmlElement(name = "KeyRequestDetail")
            protected List<ListKeyRequestsResponse.Domains.KeyRequestMaster.KeyRequestDetail> keyRequestDetail;

            /**
             * Gets the value of the krmid property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKrmid() {
                return krmid;
            }

            /**
             * Sets the value of the krmid property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKrmid(Long value) {
                this.krmid = value;
            }

            /**
             * Gets the value of the projectid property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getProjectid() {
                return projectid;
            }

            /**
             * Sets the value of the projectid property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setProjectid(Long value) {
                this.projectid = value;
            }

            /**
             * Gets the value of the keyRequestDetail property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the keyRequestDetail property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getKeyRequestDetail().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link ListKeyRequestsResponse.Domains.KeyRequestMaster.KeyRequestDetail }
             * 
             * 
             */
            public List<ListKeyRequestsResponse.Domains.KeyRequestMaster.KeyRequestDetail> getKeyRequestDetail() {
                if (keyRequestDetail == null) {
                    keyRequestDetail = new ArrayList<ListKeyRequestsResponse.Domains.KeyRequestMaster.KeyRequestDetail>();
                }
                return this.keyRequestDetail;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="krdid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
             *         &lt;element name="projectid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
             *         &lt;element name="kpid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
             *         &lt;element name="trsmid" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
             *         &lt;element name="requester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="requesttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="cn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="ou" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="validity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
             *         &lt;element name="istargetgroup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
             *         &lt;element name="target" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="received" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="finished" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="errormsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "krdid",
                "projectid",
                "kpid",
                "trsmid",
                "requester",
                "requesttype",
                "cn",
                "ou",
                "validity",
                "istargetgroup",
                "target",
                "notes",
                "received",
                "finished",
                "status",
                "errormsg"
            })
            public static class KeyRequestDetail {

                protected Long krdid;
                protected Long projectid;
                protected Long kpid;
                protected Long trsmid;
                protected String requester;
                protected String requesttype;
                protected String cn;
                protected String ou;
                protected Long validity;
                protected Boolean istargetgroup;
                protected String target;
                protected String notes;
                protected String received;
                protected String finished;
                protected String status;
                protected String errormsg;

                /**
                 * Gets the value of the krdid property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Long }
                 *     
                 */
                public Long getKrdid() {
                    return krdid;
                }

                /**
                 * Sets the value of the krdid property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Long }
                 *     
                 */
                public void setKrdid(Long value) {
                    this.krdid = value;
                }

                /**
                 * Gets the value of the projectid property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Long }
                 *     
                 */
                public Long getProjectid() {
                    return projectid;
                }

                /**
                 * Sets the value of the projectid property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Long }
                 *     
                 */
                public void setProjectid(Long value) {
                    this.projectid = value;
                }

                /**
                 * Gets the value of the kpid property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Long }
                 *     
                 */
                public Long getKpid() {
                    return kpid;
                }

                /**
                 * Sets the value of the kpid property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Long }
                 *     
                 */
                public void setKpid(Long value) {
                    this.kpid = value;
                }

                /**
                 * Gets the value of the trsmid property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Long }
                 *     
                 */
                public Long getTrsmid() {
                    return trsmid;
                }

                /**
                 * Sets the value of the trsmid property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Long }
                 *     
                 */
                public void setTrsmid(Long value) {
                    this.trsmid = value;
                }

                /**
                 * Gets the value of the requester property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRequester() {
                    return requester;
                }

                /**
                 * Sets the value of the requester property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRequester(String value) {
                    this.requester = value;
                }

                /**
                 * Gets the value of the requesttype property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getRequesttype() {
                    return requesttype;
                }

                /**
                 * Sets the value of the requesttype property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setRequesttype(String value) {
                    this.requesttype = value;
                }

                /**
                 * Gets the value of the cn property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getCn() {
                    return cn;
                }

                /**
                 * Sets the value of the cn property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setCn(String value) {
                    this.cn = value;
                }

                /**
                 * Gets the value of the ou property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getOu() {
                    return ou;
                }

                /**
                 * Sets the value of the ou property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setOu(String value) {
                    this.ou = value;
                }

                /**
                 * Gets the value of the validity property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Long }
                 *     
                 */
                public Long getValidity() {
                    return validity;
                }

                /**
                 * Sets the value of the validity property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Long }
                 *     
                 */
                public void setValidity(Long value) {
                    this.validity = value;
                }

                /**
                 * Gets the value of the istargetgroup property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Boolean }
                 *     
                 */
                public Boolean isIstargetgroup() {
                    return istargetgroup;
                }

                /**
                 * Sets the value of the istargetgroup property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Boolean }
                 *     
                 */
                public void setIstargetgroup(Boolean value) {
                    this.istargetgroup = value;
                }

                /**
                 * Gets the value of the target property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getTarget() {
                    return target;
                }

                /**
                 * Sets the value of the target property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setTarget(String value) {
                    this.target = value;
                }

                /**
                 * Gets the value of the notes property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getNotes() {
                    return notes;
                }

                /**
                 * Sets the value of the notes property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setNotes(String value) {
                    this.notes = value;
                }

                /**
                 * Gets the value of the received property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getReceived() {
                    return received;
                }

                /**
                 * Sets the value of the received property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setReceived(String value) {
                    this.received = value;
                }

                /**
                 * Gets the value of the finished property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getFinished() {
                    return finished;
                }

                /**
                 * Sets the value of the finished property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setFinished(String value) {
                    this.finished = value;
                }

                /**
                 * Gets the value of the status property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getStatus() {
                    return status;
                }

                /**
                 * Sets the value of the status property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setStatus(String value) {
                    this.status = value;
                }

                /**
                 * Gets the value of the errormsg property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getErrormsg() {
                    return errormsg;
                }

                /**
                 * Sets the value of the errormsg property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setErrormsg(String value) {
                    this.errormsg = value;
                }

            }

        }

    }

}