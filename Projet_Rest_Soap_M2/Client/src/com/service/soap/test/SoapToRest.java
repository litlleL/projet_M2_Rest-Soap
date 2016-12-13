/**
 * SoapToRest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.service.soap.test;

public interface SoapToRest extends java.rmi.Remote {
    public java.lang.String auth(java.lang.String pseudoAuth, java.lang.String mdp) throws java.rmi.RemoteException;
    public boolean getOut(java.lang.String pseudoSupp, java.lang.String pass) throws java.rmi.RemoteException;
    public boolean inscription(java.lang.String pseudoInscrit, java.lang.String mdpSend, java.lang.String mdpVerification) throws java.rmi.RemoteException;
}
