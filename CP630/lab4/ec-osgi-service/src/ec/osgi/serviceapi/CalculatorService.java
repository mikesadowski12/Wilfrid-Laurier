package ec.osgi.serviceapi;

public interface CalculatorService {
    int sum(int a, int b);
    int subtraction(int a, int b);
    int multiplication(int a, int b);
    int divison(int a, int b) throws Exception; 
}