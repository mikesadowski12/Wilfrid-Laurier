package ec.lab;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class IECWSClient {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/ec-ws/ECWSImpl?WSDL");
        QName qname = new QName("http://lab.ec/", "ECWSImplService");
        Service service = Service.create(url, qname);
        IECWS ms  = service.getPort(IECWS.class);
        System.out.println(ms.predict(90));
    }
}