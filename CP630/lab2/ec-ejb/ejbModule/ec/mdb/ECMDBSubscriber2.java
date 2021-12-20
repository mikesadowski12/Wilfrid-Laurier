package ec.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.ec.lab.SBStateless;

@MessageDriven(name = "sub2", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/test"),
})
public class ECMDBSubscriber2 implements MessageListener {
    @EJB
    SBStateless sbsl;

    public void onMessage(Message message) {
        try {
            String mstr = ((TextMessage) message).getText();
            System.out.println("Message received by subscriber2 : "+ mstr);
            System.out.println("Subscriber2 invoking get Type: "+ sbsl.getSBType());
                        
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}