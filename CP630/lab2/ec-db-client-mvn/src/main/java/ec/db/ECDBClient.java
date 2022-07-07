package ec.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ECDBClient {
//// use this for standalone H2 
//  static final String JDBC_DRIVER = "org.h2.Driver";
//  static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
//  static final String USER = "sa";
//  static final String PASS = "sa";

// use this for MyLS mysql connector version 6.+, see pom.xml 
// for version 5. use "com.mysql.jdbc.Driver";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "";
    
    private static final Logger logger = LogManager.getLogger(ECDBClient.class.getName()); 
    public static void main(String[] args) throws Exception {
        System.out.println("DB connection");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String sql;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            statement = connection.createStatement();

            try {
                sql = "DROP TABLE TEST";
                statement.execute(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            sql = "CREATE TABLE TEST (ID INT, NAME VARCHAR(50))";
            statement.execute(sql);

            sql = "INSERT INTO TEST VALUES(1, 'dbtest')";
            statement.execute(sql);

            sql = "SELECT * FROM TEST";
            resultSet = statement.executeQuery(sql);

            if (resultSet != null) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("NAME");
                    System.out.print("ID: " + id);
                    System.out.print("\nNAME: " + name);
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
        logger.info("connection is done");
    }
}