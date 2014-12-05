/**
 * AveragingFilter extends Filter, enforcing a double input and
 * keeping track of the number of inputs there has been.
 * It simple overrides the processInput function such that the 
 * next output is set to be the average. This is simple arithemetic:
 * size++;
 * average = (((average * (size - 1)) + input) / size);
 * @author gmh73
 *
 */
public class AveragingFilter extends ScalarFilter{
	private int size;
	private double average;
	
	/**
	 * Constructs an Averaging Filter with initial size 0 and 
	 * initial average 0.  Previous output is null.
	 */
	public AveragingFilter(){
		size = 0;
		average = 0.0;
	}
	
	/**
	 * The new average is calculated and outputted
	 */
	@Override
	protected void processInput(Double input) {
		size++;
		average = (((average * (size - 1)) + input) / size);
		setOutput(average);
	}
	
	/**
	 * The default reset for averaging filter is to set the average
	 * to r and set the size to 1
	 */
	@Override
	public void reset(Double r) {
		reset(r, 1);
	}
	
	/**
	 * An alternate reset function sets the average to r and the
	 * size to i.
	 * The previous output remains the same.
	 * @param r		the average
	 * @param i		the size
	 */
	public void reset(Double r, int i) {
		average = r;
		size = i;
	}

}
