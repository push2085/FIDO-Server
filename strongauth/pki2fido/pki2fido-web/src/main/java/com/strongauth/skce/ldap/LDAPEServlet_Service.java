
package com.strongauth.skce.ldap;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "LDAPEServlet", targetNamespace = "http://ldapews.strongauth.com/", wsdlLocation = "https://deiskce02.strongauth.com:8181/ldape/LDAPEServlet?wsdl")
public class LDAPEServlet_Service
    extends Service
{

    private final static URL LDAPESERVLET_WSDL_LOCATION;
    private final static WebServiceException LDAPESERVLET_EXCEPTION;
    private final static QName LDAPESERVLET_QNAME = new QName("http://ldapews.strongauth.com/", "LDAPEServlet");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://deiskce02.strongauth.com:8181/ldape/LDAPEServlet?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LDAPESERVLET_WSDL_LOCATION = url;
        LDAPESERVLET_EXCEPTION = e;
    }

    public LDAPEServlet_Service() {
        super(__getWsdlLocation(), LDAPESERVLET_QNAME);
    }

    public LDAPEServlet_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), LDAPESERVLET_QNAME, features);
    }

    public LDAPEServlet_Service(URL wsdlLocation) {
        super(wsdlLocation, LDAPESERVLET_QNAME);
    }

    public LDAPEServlet_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LDAPESERVLET_QNAME, features);
    }

    public LDAPEServlet_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LDAPEServlet_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns LDAPEServlet
     */
    @WebEndpoint(name = "LDAPEServletPort")
    public LDAPEServlet getLDAPEServletPort() {
        return super.getPort(new QName("http://ldapews.strongauth.com/", "LDAPEServletPort"), LDAPEServlet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LDAPEServlet
     */
    @WebEndpoint(name = "LDAPEServletPort")
    public LDAPEServlet getLDAPEServletPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ldapews.strongauth.com/", "LDAPEServletPort"), LDAPEServlet.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LDAPESERVLET_EXCEPTION!= null) {
            throw LDAPESERVLET_EXCEPTION;
        }
        return LDAPESERVLET_WSDL_LOCATION;
    }

}