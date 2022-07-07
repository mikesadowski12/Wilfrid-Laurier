package ec.lab;

import javax.ejb.Remote;

@Remote
public interface SBSingletonRemote1 {
	 public String getSBType();
	 public int getCounter();
}