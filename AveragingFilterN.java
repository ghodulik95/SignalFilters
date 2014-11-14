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
	
	public AveragingFilterN(int n) {
		super(n);
		size = 0;
		average = 0.0;
	}

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

}
