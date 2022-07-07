package stats.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatsDBClient {
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
   
   private static final Logger logger = LogManager.getLogger(StatsDBClient.class.getName()); 
   public static void main(String[] args) throws Exception {
       System.out.println("DB connection");
       Connection connection = null;
       Statement statement = null;
       String sql;

       try {
           Class.forName(JDBC_DRIVER);
           connection = DriverManager.getConnection(DB_URL, USER, PASS);
           statement = connection.createStatement();

           try {
               sql = "DROP TABLE IF EXISTS ECUSER";
               statement.execute(sql);
               
               sql = "DROP TABLE IF EXISTS MODEL";
               statement.execute(sql);
           } catch (SQLException e) {
               e.printStackTrace();
           }

           sql = "CREATE TABLE ECUSER (ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50) NOT NULL UNIQUE, PASSWORD VARCHAR(255) NOT NULL, ROLE INT)";
           statement.execute(sql);
           
           sql = "CREATE TABLE MODEL (ID INT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50), CLASSNAME VARCHAR(255) NOT NULL, OBJECT MEDIUMBLOB, DATE DATE)";
           statement.execute(sql);
           
			sql = "INSERT INTO ECUSER(NAME, PASSWORD, ROLE) VALUES('user1', 'password', 1)";
			statement.execute(sql);
			
			sql = "INSERT INTO ECUSER(NAME, PASSWORD, ROLE) VALUES('user2', 'password', 2)";
			statement.execute(sql);
		
			sql = "INSERT INTO ECUSER(NAME, PASSWORD, ROLE) VALUES('user3', 'password', 3)";
			statement.execute(sql);
           
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
