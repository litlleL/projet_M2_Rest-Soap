/**
 * SoapToRestServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.service.soap.test;

public class SoapToRestServiceLocator extends org.apache.axis.client.Service implements com.service.soap.test.SoapToRestService {

    public SoapToRestServiceLocator() {
    }


    public SoapToRestServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SoapToRestServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SoapToRest
    private java.lang.String SoapToRest_address = "http://localhost:8080/SoapImpl/services/SoapToRest";

    public java.lang.String getSoapToRestAddress() {
        return SoapToRest_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SoapToRestWSDDServiceName = "SoapToRest";

    public java.lang.String getSoapToRestWSDDServiceName() {
        return SoapToRestWSDDServiceName;
    }

    public void setSoapToRestWSDDServiceName(java.lang.String name) {
        SoapToRestWSDDServiceName = name;
    }

    public com.service.soap.test.SoapToRest getSoapToRest() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SoapToRest_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSoapToRest(endpoint);
    }

    public com.service.soap.test.SoapToRest getSoapToRest(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.service.soap.test.SoapToRestSoapBindingStub _stub = new com.service.soap.test.SoapToRestSoapBindingStub(portAddress, this);
            _stub.setPortName(getSoapToRestWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSoapToRestEndpointAddress(java.lang.String address) {
        SoapToRest_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.service.soap.test.SoapToRest.class.isAssignableFrom(serviceEndpointInterface)) {
                com.service.soap.test.SoapToRestSoapBindingStub _stub = new com.service.soap.test.SoapToRestSoapBindingStub(new java.net.URL(SoapToRest_address), this);
                _stub.setPortName(getSoapToRestWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SoapToRest".equals(inputPortName)) {
            return getSoapToRest();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://test.soap.service.com", "SoapToRestService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://test.soap.service.com", "SoapToRest"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SoapToRest".equals(portName)) {
            setSoapToRestEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
