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
public class AveragingFilter extends Filter<Double>{
	private int size;
	private double average;
	
	public AveragingFilter(){
		size = 0;
		average = 0.0;
	}
	
	@Override
	protected void processInput(Double input) {
		size++;
		average = (((average * (size - 1)) + input) / size);
		setOutput(average);
	}

	@Override
	public void reset(Double r) {
		reset(r, 1);
	}

	public void reset(Double r, int i) {
		average = r;
		size = i;
	}

}
