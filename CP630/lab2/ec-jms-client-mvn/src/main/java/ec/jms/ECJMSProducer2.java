package ec.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.naming.Context;
import javax.naming.NamingException;

public class ECJMSProducer2 {
    public static void main(String[] args) throws NamingException, JMSException {
        System.out.println("Create JNDI Context");
        Context context = ContextUtil.getInitialContext();
        System.out.println("Get connection facory");
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        System.out.println("Lookup queue");
        Destination destination = (Destination) context.lookup("jms/queue/test");
        System.out.println("JMS context");
        JMSContext jmscontext = connectionFactory.createContext("quickstartUser", "quickstartPwd1!");
        JMSProducer jmsproducer = jmscontext.createProducer();
        jmsproducer.send(destination, "first message");
        jmsproducer.send(destination, "second message");
        jmsproducer.send(destination, "stop");
        System.out.println("producer done");
    }
}