package stats_osgi_consumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import stats.osgi.impl.StatsOSGiImpl;

public class Activator implements BundleActivator {
	private StatsOSGiImpl service;
	private ServiceTracker tracker;
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println();
		System.out.println();
		System.out.println("stats-osgi-consumer start");
        tracker = new ServiceTracker(context, StatsOSGiImpl.class.getName(), null);
        tracker.open();
        service = (StatsOSGiImpl) tracker.getService();
        
        System.out.println("Count: " + service.getCount());
        System.out.println("Min: " + service.getMin());
        System.out.println("Max: " + service.getMax());
        System.out.println("Mean: " + service.getMean());
        System.out.println("STD: " + service.getSTD());
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
        tracker.close();
        tracker = null;
        service = null;
        System.out.println("stats-osgi-consumer stop");
	}

}
