/**
 * FIRFilter extends Filter, and expresses commonality with
 * ScalarLinearFilter through containment.  It simple initializes
 * a ScalarLinearFilter with an empty a input and the processInput
 * function simple sets the output to the output of calling filter
 * on the contained ScalarLinearFilter.
 * @author thomas
 *
 */
public class FIRFilter extends Filter<Double> {
	private ScalarLinearFilter fir;
	
	/**
	 * A FIRFilter is just a ScalarLinearFilter with
	 * an empty a
	 * @param b
	 */
	public FIRFilter(double[] b){
		double[] a = new double[0];
		fir = new ScalarLinearFilter(a,b);
	}
	
	@Override
	protected void processInput(Double input) {
		setOutput(fir.filter(input));
	}

	@Override
	public void reset(Double r) {
		fir.reset(r);
	}

}
