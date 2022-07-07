package ec.lab;


/** 
* @class Calculator
* @brief This is the interface for Calculator class
* @file  Calculator.java
*/ 
public interface Calculator {
		
	/**
	  * @brief Compute and return the sum of two integers. 
	  * 
	  * @param a
	  * @param b 
	  * @return a+b
	  *  	  
	  */ 
	int sum(int a, int b);
	
	 	
	/**
	  * @brief Compute and return the difference of two operands. 
	  * 
	  * @param a 
	  * @param b
	  * @return a-b 
	  */ 
	int subtraction(int a, int b);
	
	 
	/**
	  * @brief Compute and return the multiplication of two integers. 
	  * 
	  * @param a 
	  * @param b
	  * @return a*b
	  */ 
	int multiplication(int a, int b);
	
	
	 /**
	  * @brief Compute and return the devision of two integers. 
	  * 
	  * @param a 
	  * @param b
	  * @return a/b
	  * @throws Exception if  b == 0 
	  */ 
	int divison(int a, int b) throws Exception;
	
	 
	/**
	  * @brief Check if two integer operands are equal. 
	  * 
	  * @param a 
	  * @param b
	  * 
	  * @return a==b
	  */ 
	boolean equalIntegers(int a, int b);
	
}
