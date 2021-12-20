package ec.osgi;

import java.util.Hashtable;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import ec.osgi.serviceapi.CalculatorService;
import ec.osgi.serviceimpl.CalculatorServiceImpl;

public class Activator implements BundleActivator {
    private CalculatorService service;
    
    public void start(BundleContext context) throws Exception {
        service = new CalculatorServiceImpl();
        Hashtable props = new Hashtable();
        //can add properties
        context.registerService(CalculatorService.class.getName(), service, props);
        System.out.println("calculator service start");
    }
    public void stop(BundleContext context) throws Exception {
        service = null;
        System.out.println("service stop");
    }
}