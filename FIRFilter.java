/**
 * FIRFilter extends Filter, and expresses commonality with
 * ScalarLinearFilter through containment.  It simple initializes
 * a ScalarLinearFilter with an empty a input and the processInput
 * function simple sets the output to the output of calling filter
 * on the contained ScalarLinearFilter.
 * @author thomas
 *
 */
public class FIRFilter extends ScalarFilter {
	private ScalarLinearFilter fir;
	
	/**
	 * A FIRFilter is just a ScalarLinearFilter with
	 * an empty a
	 * @param b
	 */
	public FIRFilter(Double[] b){
		Double[] a = new Double[0];
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
