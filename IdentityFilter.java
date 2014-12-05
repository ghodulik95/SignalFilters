/**
 * IdentityFilter extends Filter, and overrides
 * the processInput function to just set the output to the
 * current input.
 * @author gmh73
 *
 * @param <T>
 */
public class IdentityFilter<T> extends Filter<T>{
	
	/**
	 * Simply sets the output to input
	 */
	@Override
	protected void processInput(T input){
		setOutput(input);
	}
	
	/**
	 * Resets the previous output to r
	 */
	@Override
	public void reset(T r) {
		setOutput(r);
	}

}
