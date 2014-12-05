/**
 * AveragingFilterN extends FilterN, enforcing a double input and
 * keeping track of the number of inputs there has been.
 * However, once the size reaches n, the calculation for the average
 * changes, subtracting the oldest input an adding the newest:
 * average = average - (last / N) + (input / N);
 * @author gmh73
 *
 */
public class AveragingFilterN extends FilterN<Double>{
	private int size;
	private double average;
	
	/**
	 * Makes an Averaging Filter that only remembers N
	 * elements
	 * @param n The number of elements the filter will remember
	 */
	public AveragingFilterN(int n) {
		super(n);
		size = 0;
		average = 0.0;
	}
	
	/**
	 * Recalculates the average with the given input.
	 * If there has been N inputs, the oldest will be forgotten
	 * and subtracted from the average.
	 */
	@Override
	protected void processInput(Double input) {
		assert size <= N : "The size should never increase to be greater than N.";
		if(size == N ){
			double last = buf.push(input);
			average = average - (last / N) + (input / N);
		}else{
			size++;
			average = (((average * (size - 1)) + input) / size);
			buf.push(input);
		}
		setOutput(average);
	}
	
	/**
	 * Resets the average to r and fills our memory with
	 * r.  So, for a filter with N = 3, reset(2.0) would  fill 
	 * the memory with (2.0, 2.0, 2.0) and the average is 2.0.
	 * However, the previous output will be remembered (until a
	 * new input is given)
	 */
	@Override
	public void reset(Double r) {
		super.reset(r);
		average = r;
		size = N;
		buf.reset(r);
	}
	
}
