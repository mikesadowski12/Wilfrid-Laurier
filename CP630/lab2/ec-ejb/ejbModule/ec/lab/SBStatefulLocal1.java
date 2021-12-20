package ec.lab;

import javax.ejb.Local;

@Local
public interface SBStatefulLocal1 {
	public String Predict(int a);	
}