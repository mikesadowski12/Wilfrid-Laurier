package stats_osgi_web;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.service.http.HttpService;

import stats.osgi.impl.StatsOSGiImpl;

public class Activator implements BundleActivator {
	private StatsOSGiImpl service;
	private ServiceTracker tracker;
	private ServiceTracker httpServiceTracker;
	
	@Override
	public void start(BundleContext context) throws Exception {
        tracker = new ServiceTracker(context, StatsOSGiImpl.class.getName(), null);
        tracker.open();
        service = (StatsOSGiImpl) tracker.getService();
        
        httpServiceTracker = new ServiceTracker(context, HttpService.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference ref) {
                HttpService httpService = (HttpService) context.getService(ref);
                try {
                   httpService.registerServlet("/stats-osgi-web", new OSGiServlet(service), null, null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return httpService;
            }
            public void removedService(ServiceReference ref, Object service) {
                try {
                    ((HttpService) service).unregister("/stats-osgi-web");
               
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        
        httpServiceTracker.open();
		System.out.println();
		System.out.println();
		System.out.println("stats-osgi-web start");
	}
	
	@Override
	public void stop(BundleContext context) throws Exception {
        httpServiceTracker.close();
        tracker.close();
        tracker = null;
	}

}
