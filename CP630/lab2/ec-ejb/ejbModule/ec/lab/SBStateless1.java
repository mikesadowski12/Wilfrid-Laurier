package ec.lab;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class SBStateless
 */
@Stateless
@LocalBean
public class SBStateless1 implements SBStatelessRemote1, SBStatelessLocal1 {
	private static final Logger LOGGER = Logger.getLogger(SBStateless1.class);
	
	@EJB
	private SBSingletonLocal1 sbsgt;

    /**
     * Default constructor. 
     */
    public SBStateless1() {
    }

	@Override
	public String getSBType() {
		LOGGER.info("SBStateless.getSBType method is called");
		return "stateless";
	}

	@Override
	public String getPrediction(int a) {
		LOGGER.info("getPrediciton method is called "+ a);
		sbsgt.incCounter();
		if (a >= 70)
		    return "Pass";
		else
		    return "Fail";
	}

	@Override
	public int getCounter() {
		return sbsgt.getCounter();
	}

}