package stats.ejb;

public class StatsWSClient {
    public static void main(String[] args) {
        StatsWSServiceLocator locator = new StatsWSServiceLocator();
        
        StatsWS service;
        try {
            service = locator.getStatsWSPort();
            
            int count = service.getCount();
            double min = service.getMin();
            double max = service.getMax();
            double mean = service.getMean();
            double std = service.getSTD();
            String summaryString = service.summaryString();
            
            System.out.println("STATISTICS:");
            System.out.println("count: " + count);
            System.out.println("min: " + min);
            System.out.println("max: " + max);
            System.out.println("mean: " + mean);
            System.out.println("std: " + std);
            System.out.println("summaryString: " + summaryString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
