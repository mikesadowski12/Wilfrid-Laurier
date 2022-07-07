package ec.lab;

/** 
* @class ECCalculator
* @brief This class implements Calculator interface
* 
*/ 
public class ECCalculator implements Calculator {


	 /**
	  * @brief This implement the sum interface
	  * 
	  */ 
	public int sum(int a, int b) {
		return a + b;
	}

	
	public int subtraction(int a, int b) {
		return a - b;
	}



	public int multiplication(int a, int b) {
		return a * b;
	}

	
	public int divison(int a, int b) throws Exception {
		//this line will not be retrieved by Doxygen
		/*
		 * this will not be retrieved by Doxygen
		 */
		/// this line will be retrieved by Doxygen
		
		/** 
		 * This will be retrieved by Doxygen
		 */
		///throw exception when divider is zero
		if (b == 0) {
			throw new Exception("Divider can't be zero");
		}
		return a / b;
	}

	
	public boolean equalIntegers(int a, int b) {
		boolean result = false;

		if (a == b) {
			result = true;
		}

		return result;
	}


}
