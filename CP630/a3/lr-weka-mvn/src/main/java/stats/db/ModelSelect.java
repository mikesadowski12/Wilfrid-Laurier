package stats.db;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ec.jpa.Model;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ModelSelect {
////use this for standalone H2 
// static final String JDBC_DRIVER = "org.h2.Driver";
// static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
// static final String USER = "sa";
// static final String PASS = "sa";

//use this for MyLS mysql connector version 6.+, see pom.xml 
//for version 5. use "com.mysql.jdbc.Driver";
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/test";
   static final String USER = "root";
   static final String PASS = "";
   
   private static final Logger logger = LogManager.getLogger(ModelSelect.class.getName()); 
   public static void main(String[] args) throws Exception {
       System.out.println("DB connection");
       Connection connection = null;
       Statement statement = null;
       String sql;
              
       try {
           Class.forName(JDBC_DRIVER);
           connection = DriverManager.getConnection(DB_URL, USER, PASS);
           statement = connection.createStatement();

           sql = "SELECT * FROM MODEL WHERE name = 'weka-lr' ORDER BY date DESC";
           ResultSet resultSet = statement.executeQuery(sql);

           if (resultSet != null) {
        	   ByteArrayInputStream bis = null; // new ByteArrayInputStream
        	   
               while (resultSet.next()) {
                   bis = new ByteArrayInputStream(resultSet.getBytes("OBJECT"));
               }
               
               ObjectInput in = null;
	           	try {
	          	  in = new ObjectInputStream(bis);
	          	  Classifier cls = (Classifier) in.readObject();
	          	  
	          	Instances predicationDataSet = DataSource.read("data/house_unknown.arff");
	          	predicationDataSet.setClassIndex(predicationDataSet.numAttributes()-1);
	          	
	          	Instance predicationDataInstance = predicationDataSet.lastInstance();
	    		double value = cls.classifyInstance(predicationDataInstance);
	    		System.out.println(value);
	    		
	    	    for (int i = 0; i < predicationDataSet.numInstances(); i++) {
	    		      double clsLabel = cls.classifyInstance(predicationDataSet.instance(i));
	    		      System.out.println(predicationDataSet.instance(i));
	    		      predicationDataSet.instance(i).setClassValue(clsLabel);
	    		    }
	    	    
    		    	String predictionResults = predicationDataSet.toString();
	    		    System.out.println(predictionResults);
	        	} finally {
	          	  try {
	          	    if (in != null) {
	          	      in.close();
	          	    }
	          	  } catch (IOException ex) {
	          	    // ignore close exception
	          	  }
	          	}
           }
       } catch (SQLException e) { // Handle errors for JDBC
           e.printStackTrace();
       } catch (Exception e) { 
           e.printStackTrace();
       } finally { // finally block used to close resources
           try {
               if (statement != null) statement.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
           try {
               if (connection != null) connection.close();
           } catch (SQLException se) {
               se.printStackTrace();
           }
       }
   }
}
