//package stats.mdb;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.EJB;
//import javax.ejb.MessageDriven;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
//import stats.ejb.StatsEJBSingleton;
//
//@MessageDriven(name = "sub1", activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
//        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/test"),
//})
//public class StatsMDBAdd implements MessageListener {
//    @EJB
//    StatsEJBSingleton statsEJBSingleton;
//
//    public void onMessage(Message message) {
//        try {
//            String mstr = ((TextMessage) message).getText();
//            System.out.println("StatsMDBAdd: Message recieved - " + mstr);
//            
//            double value = Double.parseDouble(mstr);
//            System.out.println("StatsMDBAdd: Value recieved, adding data - " + value);
//            statsEJBSingleton.addData(value);
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
//}
