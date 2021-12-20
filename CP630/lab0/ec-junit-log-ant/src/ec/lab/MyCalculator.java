package ec.lab;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** 
* @mainpage Doxygen example 
* 
* How to document program for Doxygen retrieval<br> 
* 
* Keywords are identified with an @ or backslash \ to signify attributes of the code or to 
* call special format options. The most common keywords are:<br> 
* \@brief - a short code description<br>
* \@param abc - describes the parameter abc of an function<br>
* \@return - describes the return value of an function<br>
* \@class abc - describes the class abc<br>
* \@file abc.java - describes the file abc.java<br>
*  
* @author HBF 
*/ 

/** 
* @file MyCalculator.java 
* @brief This is file the MyCalculator class containing the main function.
* @author HBF 
*/ 

/** 
* @class MyCalculator 
* 
* @brief MyCalcuator class for the main function.
* 
* <ul> 
* <li>main function creates Calculator instance</li> 
* <li>call method sum with parameters</li> 
* <li>print the result of sum</li> 
* </ul>
*/ 
public class MyCalculator {
	/** 
	* @brief main function
	* 
	* Calculate the sum of two given numbers and print the result
	*/ 
	private static final Logger logger = LogManager.getLogger(MyCalculator.class.getName());  
    public static void main(String[] args) {
    	Calculator calculator = new ECCalculator();
    	int result = calculator.sum(3, 4);
    	System.out.println(result);
        logger.info("The sum of 3 and 4 is " + result); 
    }
}