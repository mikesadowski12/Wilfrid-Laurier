package stats.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

@Stateless
@LocalBean
public class StatsEJBJMSStateless implements StatsEJBJMSStatelessLocal {
	@Inject
	JMSContext context;

	@Resource(lookup = "java:/queue/test")
	private Queue queue;

	@Resource(lookup = "java:/topic/test")
	private Topic topic; 
	
	@Override
	public void produce(String mstr) {	    
        try {
	        final Destination destination = queue;
	        context.createProducer().send(destination, mstr);       
	    } catch (Exception ex) {
	        throw (ex);
	    } 
	}

	@Override
	public void publish(String mstr) {		
        try {
	        final Destination destination = topic;
	        context.createProducer().send(destination, mstr);       
	    } catch (Exception ex) {
	        throw (ex);
	    } 
	}
}
