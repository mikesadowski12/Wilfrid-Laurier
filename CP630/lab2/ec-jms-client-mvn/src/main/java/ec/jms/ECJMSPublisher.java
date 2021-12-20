package ec.jms;

import javax.naming.Context;
import javax.jms.Topic;
import javax.jms.TextMessage;
import javax.jms.TopicPublisher;
import javax.jms.DeliveryMode;
import javax.jms.TopicSession;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
                                                                            
public class ECJMSPublisher {
    static TopicConnection connection = null;
    
    public static void main(String[] args) throws Exception {
     
        System.out.println("Create JNDI Context");
        Context context = ContextUtil.getInitialContext();
        TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        connection = connectionFactory.createTopicConnection("quickstartUser", "quickstartPwd1!");
        
        System.out.println("Create topic session");
        TopicSession topicSession = connection.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
        
        Topic topic = (Topic) context.lookup("jms/topic/test");
        
        System.out.println("Start topic connection");
        connection.start();
                               
       TopicPublisher topicPublisher = topicSession.createPublisher(topic);
       topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                                                                           
       TextMessage message = topicSession.createTextMessage();
       message.setText("This is EC lab jms topic message");
                          
       topicPublisher.publish(message);
       System.out.println("Message published: " + message.getText());
       connection.close();
    }
}