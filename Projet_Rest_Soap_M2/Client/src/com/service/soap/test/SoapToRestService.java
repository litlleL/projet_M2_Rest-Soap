/**
 * SoapToRestService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.service.soap.test;

public interface SoapToRestService extends javax.xml.rpc.Service {
    public java.lang.String getSoapToRestAddress();

    public com.service.soap.test.SoapToRest getSoapToRest() throws javax.xml.rpc.ServiceException;

    public com.service.soap.test.SoapToRest getSoapToRest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
