package stats.ejb;

public class StatsWSProxy implements stats.ejb.StatsWS {
  private String _endpoint = null;
  private stats.ejb.StatsWS statsWS = null;
  
  public StatsWSProxy() {
    _initStatsWSProxy();
  }
  
  public StatsWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initStatsWSProxy();
  }
  
  private void _initStatsWSProxy() {
    try {
      statsWS = (new stats.ejb.StatsWSServiceLocator()).getStatsWSPort();
      if (statsWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)statsWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)statsWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (statsWS != null)
      ((javax.xml.rpc.Stub)statsWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public stats.ejb.StatsWS getStatsWS() {
    if (statsWS == null)
      _initStatsWSProxy();
    return statsWS;
  }
  
  public double getMin() throws java.rmi.RemoteException{
    if (statsWS == null)
      _initStatsWSProxy();
    return statsWS.getMin();
  }
  
  public double getSTD() throws java.rmi.RemoteException{
    if (statsWS == null)
      _initStatsWSProxy();
    return statsWS.getSTD();
  }
  
  public java.lang.String summaryString() throws java.rmi.RemoteException{
    if (statsWS == null)
      _initStatsWSProxy();
    return statsWS.summaryString();
  }
  
  public double getMax() throws java.rmi.RemoteException{
    if (statsWS == null)
      _initStatsWSProxy();
    return statsWS.getMax();
  }
  
  public int getCount() throws java.rmi.RemoteException{
    if (statsWS == null)
      _initStatsWSProxy();
    return statsWS.getCount();
  }
  
  public double getMean() throws java.rmi.RemoteException{
    if (statsWS == null)
      _initStatsWSProxy();
    return statsWS.getMean();
  }
  
  
}