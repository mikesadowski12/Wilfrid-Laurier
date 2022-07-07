package ec.lab;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "ec.lab.IECWS")
public class ECWSImpl implements IECWS{
    @EJB
    SBStatelessLocal sbsl;
            
    @WebMethod()
    public String predict(int value) {
        return sbsl.getPrediction(value);
    }
}