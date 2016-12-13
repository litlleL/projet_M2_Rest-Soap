package com.service.soap.test;

public class SoapToRestProxy implements com.service.soap.test.SoapToRest {
  private String _endpoint = null;
  private com.service.soap.test.SoapToRest soapToRest = null;
  
  public SoapToRestProxy() {
    _initSoapToRestProxy();
  }
  
  public SoapToRestProxy(String endpoint) {
    _endpoint = endpoint;
    _initSoapToRestProxy();
  }
  
  private void _initSoapToRestProxy() {
    try {
      soapToRest = (new com.service.soap.test.SoapToRestServiceLocator()).getSoapToRest();
      if (soapToRest != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)soapToRest)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)soapToRest)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (soapToRest != null)
      ((javax.xml.rpc.Stub)soapToRest)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.service.soap.test.SoapToRest getSoapToRest() {
    if (soapToRest == null)
      _initSoapToRestProxy();
    return soapToRest;
  }
  
  public java.lang.String auth(java.lang.String pseudoAuth, java.lang.String mdp) throws java.rmi.RemoteException{
    if (soapToRest == null)
      _initSoapToRestProxy();
    return soapToRest.auth(pseudoAuth, mdp);
  }
  
  public boolean getOut(java.lang.String pseudoSupp, java.lang.String pass) throws java.rmi.RemoteException{
    if (soapToRest == null)
      _initSoapToRestProxy();
    return soapToRest.getOut(pseudoSupp, pass);
  }
  
  public boolean inscription(java.lang.String pseudoInscrit, java.lang.String mdpSend, java.lang.String mdpVerification) throws java.rmi.RemoteException{
    if (soapToRest == null)
      _initSoapToRestProxy();
    return soapToRest.inscription(pseudoInscrit, mdpSend, mdpVerification);
  }
  
  
}