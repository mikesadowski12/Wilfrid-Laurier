package stats.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ec.jpa.Model;
import weka.classifiers.Classifier;

import java.sql.Timestamp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.sql.PreparedStatement;

public class ModelInsert {
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
   
   private static final Logger logger = LogManager.getLogger(ModelInsert.class.getName()); 
   public static void main(String[] args) throws Exception {
       System.out.println("DB connection");
       Connection connection = null;
       Statement statement = null;
       String sql;
              
       try {
           Class.forName(JDBC_DRIVER);
           connection = DriverManager.getConnection(DB_URL, USER, PASS);
           statement = connection.createStatement();

//           try {               
//               sql = "DROP TABLE IF EXISTS MODEL";
//               statement.execute(sql);
//           } catch (SQLException e) {
//               e.printStackTrace();
//           }

//           sql = "CREATE TABLE MODEL (ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), CLASSNAME VARCHAR(255) NOT NULL, OBJECT MEDIUMBLOB, DATE DATE)";
//           statement.execute(sql);  
           
           final String FILE_NAME = "model/weka_regression.bin";
   		   Classifier cls = (Classifier) weka.core.SerializationHelper.read(FILE_NAME);

       	   Model model = new Model();
       	   model.setClassname(cls.getClass().getSimpleName());      
       	   model.setName("weka-lr");
           model.setDate(new Timestamp(System.currentTimeMillis()));
           
           // https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
           ByteArrayOutputStream bos = new ByteArrayOutputStream();
           ObjectOutputStream outStream = null;
           try {
				outStream = new ObjectOutputStream(bos);   
				outStream.writeObject(cls);
				outStream.flush();
				byte[] clsBytes = bos.toByteArray();
				model.setObject(clsBytes);
				
				// https://stackoverflow.com/questions/1324641/cant-insert-byte-into-mysql-using-java
				try {
				   String query = "INSERT INTO SOCCER_STATS_MODEL(name, classname, object, date) VALUES(?,?,?,?)";
				   PreparedStatement pstmt = connection.prepareStatement(query);
				   pstmt.setString(1, model.getName());
				   pstmt.setString(2, model.getClassname());
				   pstmt.setBytes(3, model.getObject());
				   pstmt.setTimestamp(4, model.getDate());
				   pstmt.execute();
				} catch (SQLException e) {
				   throw new RuntimeException(e);
				} finally {
					if (connection != null) {
				    	try { connection.close(); } catch (SQLException e) {}
				}
			}
           } finally {
           	try {
           		bos.close();
           	} catch (IOException ex) {
           		// ignore close exception
           	}
           }
           // https://stackoverflow.com/questions/2836646/java-serializable-object-to-byte-array
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
       logger.info("connection is done");
   }
}
