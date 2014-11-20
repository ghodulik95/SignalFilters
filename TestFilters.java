import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests each concrete filter class.
 * @author gmh73
 *
 */
public class TestFilters {
	//The length on increasingNums and DecreasingNums
	private final int N = 20;
	//An array of strictly increasing Doubles
	//{0.0, 1.0*multiplier, ..., (N-1)*multiplier}
	private Double[] increasingNums;
	//An array of strictly decreasing Doubles
	//{(N-1)*multiplier, (N-2)*multiplier, ..., 0.0}
	private Double[] decreasingNums;
	//Multipler of increasingNums and decreasingNums
	private final Double multiplier = 2.0;
	//The allowable imprecision difference for the Doubles
	//-- sometimes assertEquals will not work because the expected
	//might be 18.0000000000000 but the actual is 18.000000000003
	private final double allowableDifference = 0.00001;
	//Our filter
	Filter<Double> fil;
	
	/**
	 * Returns true if the absolute difference between expected and
	 * actual is within the allowable difference
	 * @param expected	an expected value
	 * @param actual	an actual value
	 * @return	returns true if the difference is allowable
	 */
	private boolean allowableDifference(Double expected, Double actual){
		return Math.abs(expected - actual) <= allowableDifference;
	}
	
	/**
	 * Set increasingNums and decreasingNums to size N,
	 * and fill increasingNums with [0.0, 1.0, ..., N - 1]*multiplier
	 * and fill decreasingNums wtih [N-1, N - 2, ..., 1.0, 0.0]*multiplier
	 */
	@Before
	public void init(){
		assertTrue("Some of these tests will not work if N is not > 3.", N > 3 );
		increasingNums = new Double[N];
		decreasingNums = new Double[N];
		for(int i = 0; i < N; i++){
			increasingNums[i] = multiplier*i;
			decreasingNums[N - 1 - i] = multiplier*i; 
		}
	}
	
	/**
	 * Tesing the identity filter
	 */
	@Test
	public void testIdentityFiler(){
		//Initialize a Doube IdentityFilter
		fil = new IdentityFilter<Double>();
		//Make sure the return for each number in increasingNums
		//and decreasingNums is itself
		for(int i = 0; i < N; i++){
			assertEquals(increasingNums[i], fil.filter(increasingNums[i]));
			assertEquals(decreasingNums[i], fil.filter(decreasingNums[i]));
		}
		
		//The reset for IdentityFilter will change the previous
		//output to its parameter.  This really does nothing
		//except getPrevOutput() should be the parameter.
		fil.reset(5.5);
		assertEquals(new Double(5.5), fil.getPrevOutput());
	}
	
	/**
	 * Test AveragingFilter on increasing and decreasing input
	 */
	@Test
	public void testAveraging() {
		fil = new AveragingFilter();
		//initial sum and count are 0
		Double sum = 0.0;
		int count = 0;
		for(int i = 0; i < N; i++){
			//increment count and add next number to sum
			count++;
			sum += increasingNums[i];
			//calculate the expected average
			Double expected = sum / count;
			//Because of precision loss, expected may not equal the filtered value,
			//so I am just checking if it is close "enough" by seeing if the absolute
			//difference is allowable
			assertTrue(allowableDifference(expected, fil.filter(increasingNums[i])));
		}
		
		//Do the same with decreasing numbers
		for(int i = 0; i < N; i++){
			count++;
			sum += decreasingNums[i];
			Double expected = sum / count;
			//Because of precision loss, expected may not equal the filtered value,
			//so I am just checking if it is close "enough" by seeing if the absolute
			//difference is allowable
			assertTrue(allowableDifference(expected, fil.filter(decreasingNums[i])));
		}
		
		//Test reset
		fil.reset(10.0);
		assertTrue(allowableDifference(5.0, fil.filter(0.0)));
		assertTrue(allowableDifference(10/3.0, fil.filter(0.0)));
	}
	
	@Test 
	public void testAveragingN(){
		fil = new AveragingFilterN(N);
		//initial sum and count are 0
		Double sum = 0.0;
		int count = 0;
		for(int i = 0; i < N; i++){
			//increment count and add next number to sum
			count++;
			sum += increasingNums[i];
			//calculate the expected average
			Double expected = sum / count;
			//Because of precision loss, expected may not equal the filtered value,
			//so I am just checking if it is close "enough" by seeing if the absolute
			//difference is allowable
			assertTrue(allowableDifference(expected, fil.filter(increasingNums[i])));
		}
		
		//Do the same with decreasing numbers
		for(int i = 0; i < N; i++){
			sum += decreasingNums[i];
			sum -= increasingNums[i];
			Double expected = sum / N;
			//Because of precision loss, expected may not equal the filtered value,
			//so I am just checking if it is close "enough" by seeing if the absolute
			//difference is allowable
			assertTrue(allowableDifference(expected, fil.filter(decreasingNums[i])));
		}
		
		//Test reset
		Double r = new Double(10.0);
		fil.reset(r);
		assertTrue(allowableDifference(((N - 1)*r)/N, fil.filter(0.0)));
		assertTrue(allowableDifference(((N - 2)*r)/N, fil.filter(0.0)));
	}
	
	/**
	 * Tests Max Filter
	 */
	@Test
	public void testMaxFilter(){
		fil = new MaxFilter<Double>();
		//Try all increasing numbers: Max should update each time
		for(int i = 0; i < N; i++){
			//each time the output changes to increasingNums[i]
			assertEquals(increasingNums[i], fil.filter(increasingNums[i]));
		}
		//Try all decreasing numbers: Max should not update
		for(int i = 0; i < N; i++){
			//output is consistently increasingNums[N - 1]
			assertEquals(increasingNums[N - 1], fil.filter(decreasingNums[i]));
		}
		//Test reset
		fil.reset(25.0);
		assertEquals(new Double(25.0), fil.filter(13.0));
		assertEquals(new Double(25.0), fil.filter(24.0));
	}
	
	/**
	 * Tests MinFilter
	 */
	@Test
	public void testMinFilter(){
		fil = new MinFilter<Double>();
		//Try all increasing numbers: Min should not update
		for(int i = 0; i < N; i++){
			//output is consistently increasingNums[0] since it is lowest
			assertEquals(increasingNums[0], fil.filter(increasingNums[i]));
		}
		//Try all decreasing numbers: Min should not update
		for(int i = 0; i < N; i++){
			//output is consistently increasingNums[0] since it is lowest
			assertEquals(increasingNums[0], fil.filter(decreasingNums[i]));
		}
		//Try a value that will change the output
		assertEquals(new Double(-1.5), fil.filter(-1.5));
		//Test reset
		fil.reset(25.0);
		assertEquals(new Double(25.0), fil.filter(26.0));
		assertEquals(new Double(13.0), fil.filter(13.0));
	}
	
	/**
	 * Test Max filter N with size N
	 */
	@Test
	public void testMaxFilterN(){
		fil = new MaxFilterN<Double>(N);
		//Try all increasing numbers: Max should update each time
		for(int i = 0; i < N; i++){
			//output changes each time
			assertEquals(increasingNums[i], fil.filter(increasingNums[i]));
		}
		//Try all decreasing numbers: Max should not change
		for(int i = 0; i < N; i++){
			//output is consistently increasingNU=ums[N-1]
			assertEquals(increasingNums[N - 1], fil.filter(decreasingNums[i]));
		}
		//Now max should change as the maxes get deleted
		for(int i = 0; i < N-1; i++){
			//Max changes each time as values get deleted
			assertEquals(decreasingNums[i+1], fil.filter(0.0));
		}
		//Test reset
		Double r = new Double((N+1)*1.0);
		fil.reset(r);
		//All the values in fil's buffer should be r
		for(int i = 0; i < N-1; i++){
			//since each filtered value is < r, the output is r
			assertEquals(new Double(r), fil.filter(0.0));
		}
		//The buffer should be out of r's, so the output should change
		assertTrue(0.0 == fil.filter(0.0));
		assertTrue(1.0 == fil.filter(1.0));
	}
	
	/**
	 * Test MinFilter with size N
	 */
	@Test
	public void testMinFilterN(){
		fil = new MinFilterN<Double>(N);
		//Try all increasing numbers: Max should update each time
		for(int i = 0; i < N; i++){
			assertEquals(increasingNums[0], fil.filter(increasingNums[i]));
		}
		//Try all decreasing numbers: Max should not change
		for(int i = 0; i < N-1; i++){
			assertEquals(increasingNums[i+1], fil.filter(N*multiplier - 1));
		}
		//Now max should change as the maxes get deleted
		for(int i = 0; i < N; i++){
			assertEquals(decreasingNums[i], fil.filter(decreasingNums[i]));
		}
		//Test reset
		Double r = new Double((N+1)*1.0);
		fil.reset(r);
		//All the values in fil's buffer should be r
		for(int i = 0; i < N-1; i++){
			//since each filtered value is > r, the output is r
			assertEquals(new Double(r), fil.filter((N + 2)*1.0));
		}
		//The buffer should be out of r's, so the output should change
		assertTrue(N*1.0 == fil.filter(N*1.0));
		assertTrue(0.0 == fil.filter(0.0));
	}
	
	/**
	 * Test the NBuffer class
	 */
	@Test
	public void testNBuffer(){
		NBuffer<Double> b = new NBuffer<Double>(N);
		//The push function should return null until the buffer is full
		for(int i = 0; i < N; i++){
			Double d = b.push(i*1.0);
			assertTrue(d == null);
		}
		//Now that the buffer is full, push should return the oldest item
		//in the buffer
		for(int i = 0; i < N; i++){
			Double d = b.push(0.0);
			assertEquals(new Double(i*1.0), d);
		}
	}
	
	/**
	 * Test Filter cascade
	 */
	@Test
	public void testFilterCascade(){
		//We will create a filter cascade such that
		//fil(i) = Identity( Max( Average( i) ) )
		Filter<Double> f1 = new AveragingFilter();
		Filter<Double> f2 = new MaxFilter<Double>();
		Filter<Double> f3 = new IdentityFilter<Double>(); 
		LinkedList<Filter<Double>> l = new LinkedList<Filter<Double>>();
		l.add(f1); l.add(f2); l.add(f3);
		fil = new FilterCascade<Double>(l);
		
		//We will input increasingNums, so the output should increase
		//each time since the average is also increasing
		//For this test, we must keep track of the sum and count to
		//calculate the average
		//initial sum and count are 0
		Double sum = 0.0;
		int count = 0;
		Double expected = 0.0;
		for(int i = 0; i < N; i++){
			//increment count and add next number to sum
			count++;
			sum += increasingNums[i];
			//calculate the expected average
			expected = sum / count;
			//Because of precision loss, expected may not equal the filtered value,
			//so I am just checking if it is close "enough" by seeing if the absolute
			//difference is allowable
			assertTrue(allowableDifference(expected, fil.filter(increasingNums[i])));
		}
		//the filter should not change if we enter values less than the
		//maximum average
		for(int i = 0; i < N; i++){
			assertTrue(allowableDifference(expected, fil.filter(0.0)));
		}
		
		//Test reset
		//FilterCascade reset should reset each filter in its cascade
		Double r = new Double(10);
		fil.reset(r);
		//The previous output should now be r
		assertEquals(r, fil.getPrevOutput());
		//Input r+15, and new output is the new average since it is greate than r
		assertEquals(new Double((2*r + 15)/2), fil.filter(r + 15));
		//Input something lower than the old r, and the output should remain the same
		assertEquals(new Double((2*r + 15)/2), fil.filter(0.0));
	}
	
	/**
	 * Test Scalar Linear Filter
	 * I don't really know how to come up with sample data for this
	 * So I just used the example in the assignment.
	 */
	@Test
	public void testSLF(){
		//Using example in assignment description
		Double[] a = {0.1};
		Double[] b = {0.5, 0.5};
		Double[] input = {-1.0, 1.0, 2.0, null, -1.0, 3.0, 1.0, 2.0, 1.0};
		Double[] output = {-.5, 0.05, 1.495, null, -.5, 1.05, 1.895, 1.3105, 1.36895};
		fil = new ScalarLinearFilter(a, b);
		
		//Check each input and see if it equals the output
		for(int i = 0; i < input.length; i++){
			if(input[i] != null){
				assertTrue(allowableDifference(output[i], fil.filter(input[i])));
			}else{
				fil.reset(0.0);
			}
		}
	}
	
	/**
	 * Test FIR Filter
	 */
	@Test
	public void testFIRF(){
		//We are making be 3 0.1's
		Double[] b = {0.1, 0.1, 0.1};
		fil = new FIRFilter(b);
		//Enter the first two values of increasingNums, and see if the output is expected
		//We have to manually input these first 2 inputs so that the loop is uncomplicated
		assertEquals(increasingNums[0], fil.filter(increasingNums[0]));
		assertEquals(new Double((increasingNums[0] + increasingNums[1])*0.1), fil.filter(increasingNums[1]));
		for(int i = 2; i < N; i++){
			//Calculate the expected value based on the 3 increasingNum values
			Double expected = new Double((increasingNums[i] + increasingNums[i - 1] + increasingNums[i - 2])*0.1);
			assertTrue(allowableDifference(expected, fil.filter(increasingNums[i])));
		}
	}
	
	/**
	 * Test Gain filter
	 * Each filtered value should be the input * gain
	 */
	@Test
	public void testGainFilter(){
		//Setting gain to 2
		fil = new GainFilter(2.0);
		//Each output should be the input * 2
		for(int i = 0; i < N; i++){
			assertEquals(new Double(increasingNums[i]*2.0), fil.filter(increasingNums[i]));
		}
		for(int i = 0; i < N; i++){
			assertEquals(new Double(decreasingNums[i]*2.0), fil.filter(decreasingNums[i]));
		}
	}
	
	/**
	 * Test Binomial Filter
	 */
	@Test
	public void testBinomialFilter(){
		//b is {1, 4, 6, 4, 1}
		fil = BinomialFilter.getInstance(4);
		//Calculate expected and test if the output is it
		//We input the first 4 inputs manually to make the for loop easier
		Double expected = new Double(increasingNums[0]);
		assertEquals(expected, fil.filter(increasingNums[0]));
		expected = new Double(increasingNums[0]*4.0 + increasingNums[1]);
		assertEquals(expected, fil.filter(increasingNums[1]));
		expected = new Double(increasingNums[0]*6.0 + increasingNums[1]*4.0 + increasingNums[2]);
		assertEquals(expected, fil.filter(increasingNums[2]));
		expected = new Double(increasingNums[0]*4.0 + increasingNums[1]*6 + increasingNums[2]*4 + increasingNums[3]);
		assertEquals(expected, fil.filter(increasingNums[3]));
		
		for(int i = 4; i < N; i++){
			//Calcuulate expected based on the values of increasingNums
			expected = new Double(increasingNums[i-4] + 
					increasingNums[i-3]*4.0 + increasingNums[i-2]*6 + increasingNums[i-1]*4 + increasingNums[i]);
			assertEquals(expected, fil.filter(increasingNums[i]));
		}
	}
	
	/**
	 * Make sure exceptions are thrown when they are supposed to
	 */
	@Test
	public void testFilterExceptions(){
		/*
		 * The first group is all the subclasses of FilterN
		 * N cannot be negative or 0, so each N should 
		 * throw an exception
		 */
		try{
			fil = new AveragingFilterN(-1);
			fail("The above should throw an exception.");
		}catch(Exception e){
			//We want this
		}
		try{
			fil = new AveragingFilterN(0);
			fail("The above should throw an exception.");
		}catch(Exception e){
			//We want this
		}
		try{
			fil = new MaxFilterN<Double>(-1);
			fail("The above should throw an exception.");
		}catch(Exception e){
			//We want this
		}
		try{
			fil = new MaxFilterN<Double>(0);
			fail("The above should throw an exception.");
		}catch(Exception e){
			//We want this
		}
		try{
			fil = new MinFilterN<Double>(-1);
			fail("The above should throw an exception.");
		}catch(Exception e){
			//We want this
		}
		try{
			fil = new MinFilterN<Double>(0);
			fail("The above should throw an exception.");
		}catch(Exception e){
			//We want this
		}
		/*
		 * All the above are subclasses of FilterN
		 * Below is BinomialFilter, which also throws an exception for negative,
		 * but not 0 input
		 */
		
		try{
			fil = BinomialFilter.getInstance(-1);
			fail("The above should throw an exception.");
		}catch(Exception e){
			//We want this
		}
		try{
			fil = BinomialFilter.getInstance(0);
		}catch(Exception e){
			fail("The above should not throw an exception.");
		}
	}

}
