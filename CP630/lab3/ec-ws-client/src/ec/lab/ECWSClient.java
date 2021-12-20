package ec.lab;
public class ECWSClient {
    public static void main(String[] args) {
        ECWSServiceLocator locator = new ECWSServiceLocator();
        
        ECWS service;
        try {
            service = locator.getECWSPort();
            String result = service.predict("90");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}