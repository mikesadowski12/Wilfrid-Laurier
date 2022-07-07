package ec.lab;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import ec.lab.Calculator;
import ec.lab.ECCalculator;

public class CalTest2 {
	private static Calculator calculator;
	
	@BeforeClass
	public static void initCalculator() {
		calculator = new ECCalculator();
	}
	

	@Test
	public void testSum() {
		int result = calculator.sum(3, 4);
		System.out.println(result+"");
		assertEquals(6, result);
	}

	@Test
	public void testDivison() {
		try {
			int result = calculator.divison(10, 2);
			assertEquals(5, result);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	@Test(expected = Exception.class)
	public void testDivisionException() throws Exception {
		calculator.divison(10, 0);
	}

 
}

