package ec.lab;

public class ECWSProxy implements ec.lab.ECWS {
  private String _endpoint = null;
  private ec.lab.ECWS eCWS = null;
  
  public ECWSProxy() {
    _initECWSProxy();
  }
  
  public ECWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initECWSProxy();
  }
  
  private void _initECWSProxy() {
    try {
      eCWS = (new ec.lab.ECWSServiceLocator()).getECWSPort();
      if (eCWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)eCWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)eCWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (eCWS != null)
      ((javax.xml.rpc.Stub)eCWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ec.lab.ECWS getECWS() {
    if (eCWS == null)
      _initECWSProxy();
    return eCWS;
  }
  
  public java.lang.String predict(java.lang.String arg0) throws java.rmi.RemoteException{
    if (eCWS == null)
      _initECWSProxy();
    return eCWS.predict(arg0);
  }
  
  
}