package ec.lab;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class ECCQLClient {
    public static void main(String args[]) {
        // creating Cluster object
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();

        // Creating Session object
        Session session = cluster.connect();

        // Query
        String query = "CREATE KEYSPACE ecKeyspace WITH REPLICATION = {'class' : 'NetworkTopologyStrategy', 'datacenter1' : 3 } AND DURABLE_WRITES = false;";

        // Executing the query
        session.execute(query);

        // Using the KeySpace
        session.execute("USE ecKeyspace;");

        // Creating a table
        session.execute("CREATE TABLE user ( first_name text , last_name text, PRIMARY KEY (first_name));");

        // Inserting record to table
        session.execute("INSERT INTO user (first_name, last_name) VALUES ('HB', 'Fan');");

        // Selecting record from table
        ResultSet results = session.execute("SELECT * FROM user WHERE first_name='HB';");

        // Printing rows of reselecting
        System.out.println(String.format("%-30s\t%-20s", "FirstName", "LastName"));
        for (Row row : results) {
            System.out.println(String.format("%-30s\t%-20s", row.getString("first_name"), row.getString("last_name")));
        }
    
        // Updating record
        session.execute("UPDATE user SET last_name = 'Fan' WHERE first_name = 'HB';");
        results = session.execute("SELECT * FROM user WHERE first_name='HB';");
        System.out.println(String.format("%-30s\t%-20s", "First Name", "LastName"));
        for (Row row : results) {
            System.out.println(String.format("%-30s\t%-20s", row.getString("first_name"), row.getString("last_name")));
        }
        System.out.println();

        // Deleting record
        session.execute("DELETE FROM USER WHERE first_name='HB';");
        results = session.execute("SELECT COUNT (*) FROM user;");
        System.out.println(String.format("%-30s", "Count"));
        for (Row row : results) {
            System.out.println(String.format("%-30s", row.getLong("count")));
        }
        
        // Dropping table
        session.execute("DROP TABLE user;");
        // Dropping keyspace 
        session.execute(" DROP KEYSPACE ecKeyspace;");
        session.close();
        session.getCluster().close();
        System.out.println("client end");    
    }
}