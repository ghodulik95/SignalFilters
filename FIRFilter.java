/**
 * FIRFilter extends Filter, and expresses commonality with
 * ScalarLinearFilter through containment.  It simple initializes
 * a ScalarLinearFilter with an empty a input and the processInput
 * function simple sets the output to the output of calling filter
 * on the contained ScalarLinearFilter.
 * @author thomas
 *
 */
public class FIRFilter extends ScalarLinearFilter {
	
	/**
	 * A FIRFilter is just a ScalarLinearFilter with
	 * an empty a
	 * @param b
	 */
	public FIRFilter(Double[] b){
		super( new Double[0], b);
	}

}
