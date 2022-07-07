package ec.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

public class DBTest {   
//    static final String JDBC_DRIVER = "org.h2.Driver";
//    static final String DB_URL = "jdbc:h2:tcp://localhost/~/test";
//    static final String USER = "sa";
//    static final String PASS = "sa"; 
    
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    static final String USER = "root";
    static final String PASS = "";

    static Connection connection = null;
    static Statement statement = null;
    static ResultSet resultSet = null;
    static String sql;

    static int id_before = 1;
    static int id_after = 0;    
    static String name_before = "test";
    static String name_after = "null";

    @BeforeClass
    public static void initDBClient() throws Exception, SQLException {

        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
        statement = connection.createStatement();

        sql = "DROP TABLE TEST";
        statement.execute(sql);

        sql = "CREATE TABLE TEST (ID INT, NAME VARCHAR(50))";
        statement.execute(sql);

        sql = "INSERT INTO TEST VALUES("+ String.valueOf(id_before) +",'"+name_before+"')";
        statement.execute(sql);
    }

    @Before
    public void beforeEachTest() throws Exception, SQLException {
        System.out.println("This is executed before each Test");
        sql = "SELECT * FROM TEST";
        resultSet = statement.executeQuery(sql);
        if (resultSet != null) {
            while (resultSet.next()) {
                id_after = resultSet.getInt("id");
                name_after = resultSet.getString("NAME");
            }
        }
    }
    
    @Test
    public void test() {
        assertEquals(id_before, id_after);
        assertEquals(name_before, name_after);
    }   
    
    @After
    public void afterEachTest() throws SQLException {

        System.out.println("This is exceuted after each Test");
        if (connection != null)
            connection.close();
    }
}