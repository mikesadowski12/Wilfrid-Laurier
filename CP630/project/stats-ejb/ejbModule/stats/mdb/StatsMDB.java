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
//@MessageDriven(name = "testQueue", activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
//        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "queue/test"),
//})
//public class StatsMDB implements MessageListener {
//    @EJB
//    StatsEJBSingleton statsEJBSingleton;
//
//    public void onMessage(Message message) {
//        try {
//            String mstr = ((TextMessage) message).getText();
//            System.out.println("StatsMDB: Message recieved - " + mstr);
//            
//            if (mstr.equals("save")) {
//            	System.out.println("StatsMDB: Save message recieved, computing and saving data");
//            	statsEJBSingleton.stats();
//            	statsEJBSingleton.saveModel();
//            }
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
//}