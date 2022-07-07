package ec.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.ec.lab.SBStateless;

@MessageDriven(name = "sub1", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/test"),
})
public class ECMDBSubscriber1 implements MessageListener {
    @EJB
    SBStateless sbsl;

    public void onMessage(Message message) {
        try {
            String mstr = ((TextMessage) message).getText();
            System.out.println("MDB topic subscriber1 received message : "+ mstr);
            System.out.println("Subscriber1 invoking getCount: "+ sbsl.getCounter());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}