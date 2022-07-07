package stats_osgi_service;

import java.util.Hashtable;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import stats.osgi.impl.StatsOSGiImpl;

public class Activator implements BundleActivator {
	private StatsOSGiImpl service;
	
	@Override
	public void start(BundleContext context) throws Exception {		
		service = new StatsOSGiImpl();
		Hashtable props = new Hashtable();
		
		context.registerService(StatsOSGiImpl.class.getName(), service, props);
		System.out.println("StatsOSGiImpl service start");
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
		service = null;
		System.out.println("StatsOSGiImpl service stop");
	}
}
