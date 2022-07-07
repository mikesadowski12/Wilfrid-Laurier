package ec.stats.test;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
//import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Ignore;
import org.junit.Test;

import ec.stats.ECStatistics;

public class StatisticsTest {
	private static ECStatistics ecstats;
	
	private static double[] inputData;
	private static int expectedInputCount;
	private static double expectedInputMin;
	private static double expectedInputMax;
	private static double expectedInputMean;
	private static double expectedInputStd;
	
	@BeforeClass
	public static void init() {
		ecstats = new ECStatistics();
		
		inputData = new double[]{ 0.5, 1.5, 2.2, 3.4, 40.9, 10.5, 20.2, 30.4, 42.5, 100.3 };
		expectedInputCount = 10;
		expectedInputMin = 0.5;
		expectedInputMax = 100.3;
		expectedInputMean = 25.24;
		expectedInputStd = 29.376732289347633;
		
		for (int i = 0; i < expectedInputCount; i++) {
			ecstats.addData(inputData[i]);
		}
	}
	
	@Before
	public void beforeEachTest() {
		init();
	}
	
	@Test
	public void testCount() {
		assertEquals(expectedInputCount, ecstats.getCount());
	}
	
	@Test
	public void testMin() {
		assertEquals(expectedInputMin, ecstats.getMin(), 0);
	}
	
	@Test
	public void testMax() {
		assertEquals(expectedInputMax, ecstats.getMax(), 0);
	}

	@Test
	public void testMean() {
		assertEquals(expectedInputMean, ecstats.getMean(), 0);
	}
	
	@Test
	public void testStds() {
		assertEquals(expectedInputStd, ecstats.getSTD(), 0);
	}
	
	@Test
	public void testAddDataAddMinNumber() {
		final double newData = 0.4;
		final int newExpectedInputCount = 11;
		
		assertEquals(expectedInputCount, ecstats.getCount());
		assertEquals(expectedInputMin, ecstats.getMin(), 0);
		
		ecstats.addData(newData);
		
		assertEquals(newExpectedInputCount, ecstats.getCount());
		assertEquals(newData, ecstats.getMin(), 0);
	}
	
	@Test
	public void testAddDataAddMaxNumber() {
		final double newData = 100.4;
		final int newExpectedInputCount = 11;
		
		assertEquals(expectedInputCount, ecstats.getCount());
		assertEquals(expectedInputMax, ecstats.getMax(), 0);
		
		ecstats.addData(newData);
		
		assertEquals(newExpectedInputCount, ecstats.getCount());
		assertEquals(newData, ecstats.getMax(), 0);
	}
	
	@Test
	public void testAddDataNewMean() {
		final double newData = 70.4;
		final int newExpectedInputCount = 11;
		final double newExpectedInputMean = 29.34545454545454;
		
		assertEquals(expectedInputCount, ecstats.getCount());
		assertEquals(expectedInputMean, ecstats.getMean(), 0);
		
		ecstats.addData(newData);
		
		assertEquals(newExpectedInputCount, ecstats.getCount());
		assertEquals(newExpectedInputMean, ecstats.getMean(), 0);
	}
	
	@Test
	public void testAddDataNewStd() {
		final double newData = 70.4;
		final int newExpectedInputCount = 11;
		final double newExpectedInputStd = 30.872092829973145;
		
		assertEquals(expectedInputCount, ecstats.getCount());
		assertEquals(expectedInputStd, ecstats.getSTD(), 0);
		
		ecstats.addData(newData);
		
		assertEquals(newExpectedInputCount, ecstats.getCount());
		assertEquals(newExpectedInputStd, ecstats.getSTD(), 0);
	}
	
	@Test
	public void testStats() {
		ecstats.stats();
		
		assertEquals(expectedInputCount, ecstats.getCount());
		assertEquals(expectedInputMin, ecstats.getMin(), 0);
		assertEquals(expectedInputMax, ecstats.getMax(), 0);
		assertEquals(expectedInputMean, ecstats.getMean(), 0);
		assertEquals(expectedInputStd, ecstats.getSTD(), 0);
	}
	
	@Test
	public void testStatsNewDataMin() {
		final double newData = 0.4;
		final int newExpectedInputCount = 11;
		
		ecstats.addData(newData);
		ecstats.stats();
		
		assertEquals(newExpectedInputCount, ecstats.getCount());
		assertEquals(newData, ecstats.getMin(), 0);
	}
	
	@Test
	public void testStatsNewDataMax() {
		final double newData = 100.4;
		final int newExpectedInputCount = 11;
		
		ecstats.addData(newData);
		ecstats.stats();
		
		assertEquals(newExpectedInputCount, ecstats.getCount());
		assertEquals(newData, ecstats.getMax(), 0);
	}
	
	@Test
	public void testStatsNewDataMean() {
		final double newData = 70.4;
		final int newExpectedInputCount = 11;
		final double newExpectedInputMean = 29.34545454545454;
		
		ecstats.addData(newData);
		ecstats.stats();
		
		assertEquals(newExpectedInputCount, ecstats.getCount());
		assertEquals(newExpectedInputMean, ecstats.getMean(), 0);
	}
	
	@Test
	public void testStatsNewDataStd() {
		final double newData = 70.4;
		final int newExpectedInputCount = 11;
		final double newExpectedInputStd = 30.872092829973145;
		
		ecstats.addData(newData);
		ecstats.stats();
		
		assertEquals(newExpectedInputCount, ecstats.getCount());
		assertEquals(newExpectedInputStd, ecstats.getSTD(), 0);
	}
}
