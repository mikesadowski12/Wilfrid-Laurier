package ec.lab;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@WebServlet("/ecws")
public class ECWSServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        URL url = new URL("http://localhost:8080/ec-ws/ECWSImpl?WSDL");
        QName qname = new QName("http://lab.ec/", "ECWSImplService");
        Service service = Service.create(url, qname);
        IECWS ms  = service.getPort(IECWS.class);
        System.out.println(ms.predict(90));
    
        response.setContentType( "text/html;charset=UTF-8" );
        PrintWriter out = response.getWriter();        
        try {                        
            out.println( "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" );
            out.println( "<title>EJB, web service, Servelet</title></head>" );
            out.println( "<body><h1>predict result: " );
            out.print( ms.predict(90)+"");
            out.print( "</h1>" );        
            out.println( "</body></html>" );
        } 
        catch ( Exception ex )
        {
            throw new ServletException( ex );
        }
        finally
        {
            out.close();
        }
    }
}