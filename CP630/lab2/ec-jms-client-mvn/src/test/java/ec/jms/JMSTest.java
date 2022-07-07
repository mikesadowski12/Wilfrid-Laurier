package ec.jms;

import static org.junit.Assert.*;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JMSTest {
    private static Connection connection = null;
    static Context context;
    static String mstr_sent = "EC lab jms";
    static String mstr_received = "null";

    @BeforeClass
    public static void initJMS() throws NamingException, JMSException {
        try {
            context = ContextUtil.getInitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
            connection = connectionFactory.createConnection("quickstartUser", "quickstartPwd1!");
            Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
            Destination queue = (Destination) context.lookup("jms/queue/test");
            connection.start();

            MessageProducer producer = session.createProducer(queue);
            Message msg = session.createTextMessage("EC lab jms");

            producer.send(msg);

        } finally {
            if (connection != null) {
                System.out.println("close the connection");
            }
        }
    }

    @Before
    public void beforeEachTest() throws NamingException, JMSException {
        System.out.println("This is executed before each Test");
        try {
            context = ContextUtil.getInitialContext();
            QueueSession session = (QueueSession) connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);

            Destination queue = (Destination) context.lookup("jms/queue/test");
            QueueReceiver receiver = session.createReceiver((Queue) queue);

            mstr_received = ((TextMessage) receiver.receive()).getText();

        } finally {
            if (connection != null) {
                System.out.println("close the connection");
                connection.close();
            }
        }
    }

    @After
    public void afterEachTest() {
        System.out.println("This is exceuted after each Test");
    }

    @Test
    public void testComsumer() {
        assertEquals(mstr_sent, mstr_received);
    }
}