package ec.lab;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class SBSingleton
 */
@Singleton
@LocalBean
public class SBSingleton1 implements SBSingletonRemote1, SBSingletonLocal1 {
	private int counter = 0;

    /**
     * Default constructor. 
     */
    public SBSingleton1() {
    }

	@Override
	public int getCounter() {
		return counter;
	}

	@Override
	public int incCounter() {
		counter++;
		return counter;
	}

	@Override
	public String getSBType() {
		return "singleton";
	}
}