/**
 * GainFilter extends Filter, and expresses commonality with
 * FIRFilter through containment.  It simply initializes a
 * FIRFilter with an b input of only one element, where b1 = the gain.
 * The processInput function simple sets the output to the output
 * of calling filter on the contained FIRFilter.
 * @author gmh73
 *
 */
public class GainFilter extends ScalarFilter {
	private FIRFilter gainFilter;
	
	/**
	 * A gain filter is just a FIRFilter
	 * with one b element
	 * @param gain
	 */
	public GainFilter(Double gain){
		Double[] b = new Double[1];
		b[0] = gain;
		gainFilter = new FIRFilter(b);
	}

	@Override
	protected void processInput(Double input) {
		setOutput(gainFilter.filter(input));
	}

	@Override
	public void reset(Double r) {
		gainFilter.reset(r);
	}
}
