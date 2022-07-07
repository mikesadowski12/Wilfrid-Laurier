package ec.lab;

import javax.ejb.Remote;

@Remote
public interface SBStatelessRemote1 {
	 public String getSBType();
	 public String getPrediction(int a);
}