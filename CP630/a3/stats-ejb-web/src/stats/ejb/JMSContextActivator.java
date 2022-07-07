package stats.ejb;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;

@Stateless
public class JMSContextActivator  { 
    @Inject JMSContext context;
}