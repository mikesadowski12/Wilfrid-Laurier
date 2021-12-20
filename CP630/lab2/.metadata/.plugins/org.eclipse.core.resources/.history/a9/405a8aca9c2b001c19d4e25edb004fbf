package ec.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;

public class ECJMSConsumer implements MessageListener {
    public static void main(String[] args) throws NamingException, JMSException {
        Connection connection = null;
        try {
            System.out.println("Create JNDI Context");
            Context context = ContextUtil.getInitialContext();
            
            System.out.println("Get connection factory");
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
            
            System.out.println("Create connection");
            connection = connectionFactory.createConnection("quickstartUser", "quickstartPwd1!");
            
            System.out.println("Create session");
            Session session = connection.createSession(false,QueueSession.AUTO_ACKNOWLEDGE);
            
            System.out.println("Lookup queue");
            Destination queue = (Destination) context.lookup("jms/queue/test"); 
            
            System.out.println("Start connection");
            connection.start();
            
            System.out.println("Create consumer");
            MessageConsumer consumer = session.createConsumer(queue);
            
            System.out.println("set message listener");
            consumer.setMessageListener(new ECJMSConsumer());           
                        
        } finally {
            if (connection != null) {
                System.out.println("close the connection");
                connection.close();
            }
            System.out.println("consumer done");
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("message received");
            String mstr = ((TextMessage) message).getText();
            System.out.println(mstr);   
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}