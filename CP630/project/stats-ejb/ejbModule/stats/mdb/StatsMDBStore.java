//package stats.mdb;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//
//import javax.ejb.ActivationConfigProperty;
//import javax.ejb.EJB;
//import javax.ejb.MessageDriven;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
//import java.util.ArrayList;
//
//import stats.ejb.StatsEJBSingleton;
//
//@MessageDriven(name = "sub2", activationConfig = {
//        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
//        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/test"),
//})
//public class StatsMDBStore implements MessageListener {
//    @EJB
//    StatsEJBSingleton statsEJBSingleton;
//    
//    public void onMessage(Message message) {
//        try {
//            String mstr = ((TextMessage) message).getText();
//            System.out.println("StatsMDBStore: Message recieved - " + mstr);
//            
//            double value = Double.parseDouble(mstr);
//            System.out.println("StatsMDBStore: Value recieved, storing data - " + value);
//            
//    	    final String DIR_NAME = "C:\'enterprise\'tmp\'data\'";
//    	    final String FILE_NAME = "stats.dat";
//    	    File directory = new File(DIR_NAME);
//    	    File file = new File(FILE_NAME);
//    	    ArrayList<Double> data = statsEJBSingleton.getData();
//    	    String dataString = "";
//    	    
//            for (double d : data) {
//            	dataString += d + ",";
//            }
//
//    	    if (!directory.exists()) {
//        		directory.mkdir();
//    	    }
//
//    	    try {
//    	    	file.createNewFile();
//    	    	FileOutputStream fileOut = new FileOutputStream(DIR_NAME + FILE_NAME);
//    	    	ObjectOutputStream out = new ObjectOutputStream(fileOut);
//    	    	out.writeObject(dataString);
//    	    	out.close();
//    	    	fileOut.close();
//    	    	System.out.printf("StatsMDBStore: Serialized data is saved in " + DIR_NAME + FILE_NAME);
//    	    } catch (IOException i) {
//    	    	i.printStackTrace();
//    	    }
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
//}
