/**
 * StatsWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package stats.ejb;

public interface StatsWS extends java.rmi.Remote {
    public double getMin() throws java.rmi.RemoteException;
    public double getSTD() throws java.rmi.RemoteException;
    public java.lang.String summaryString() throws java.rmi.RemoteException;
    public double getMax() throws java.rmi.RemoteException;
    public int getCount() throws java.rmi.RemoteException;
    public double getMean() throws java.rmi.RemoteException;
}
