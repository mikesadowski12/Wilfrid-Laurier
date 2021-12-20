package ec.lab;

import javax.ejb.Local;

@Local
public interface SBSingletonLocal1 {
	public int incCounter();
	public int getCounter();
}