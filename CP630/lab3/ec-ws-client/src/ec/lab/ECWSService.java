/**
 * ECWSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ec.lab;

public interface ECWSService extends javax.xml.rpc.Service {
    public java.lang.String getECWSPortAddress();

    public ec.lab.ECWS getECWSPort() throws javax.xml.rpc.ServiceException;

    public ec.lab.ECWS getECWSPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
