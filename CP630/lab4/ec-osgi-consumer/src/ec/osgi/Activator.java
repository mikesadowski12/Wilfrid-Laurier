package ec.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import ec.osgi.serviceapi.CalculatorService;

public class Activator implements BundleActivator {
    private ServiceTracker tracker;
    private CalculatorService service;
    
    public void start(BundleContext context) throws Exception {
        tracker = new ServiceTracker(context, CalculatorService.class.getName(), null);
        tracker.open();
        service = (CalculatorService) tracker.getService();
        System.out.println("3+4="+service.sum(3, 4));
        System.out.println("3-4="+service.subtraction(3, 4));
        System.out.println("3*4="+service.multiplication(3, 4));
        System.out.println("3-4="+service.divison(3, 4));
    }
    
    public void stop(BundleContext context) throws Exception {
        tracker.close();
        tracker = null;
        service = null;
        System.out.println("consumer stop");
    }
}