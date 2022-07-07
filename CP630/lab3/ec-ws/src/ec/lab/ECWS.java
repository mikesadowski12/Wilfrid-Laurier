package ec.lab;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

import ec.lab.SBStatelessLocal;

@WebService()
public class ECWS {
    @EJB
    SBStatelessLocal sbsl;
            
    @WebMethod()
    public String predict(String value) {
        System.out.println("value=" + value);
        return sbsl.getPrediction(Integer.valueOf(value));
    }    
}