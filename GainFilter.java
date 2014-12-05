/**
 * GainFilter extends Filter, and expresses commonality with
 * FIRFilter through containment.  It simply initializes a
 * FIRFilter with an b input of only one element, where b1 = the gain.
 * The processInput function simple sets the output to the output
 * of calling filter on the contained FIRFilter.
 * @author gmh73
 *
 */
public class GainFilter extends FIRFilter {
	
	/**
	 * A gain filter is just a FIRFilter
	 * with one b element
	 * @param gain
	 */
	public GainFilter(Double gain){
		super(new Double[]{gain});
	}
}
