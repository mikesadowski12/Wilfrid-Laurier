package stats.ejb;

import javax.ejb.Local;

@Local
public interface StatsEJBJMSStatelessLocal {
    public void produce(String mstr);
    public void publish(String mstr);
}