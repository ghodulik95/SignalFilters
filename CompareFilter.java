/**
 * ComparableFilter is an abstract class that extends Filter.
 * ComparableFilter
 * calls an abstract function compare() when processing the input.
 * This function will decide whether the current input should become
 * the output based on some condition in compare(), ie maximum or min.
 * @author gmh73
 *
 * @param <T>
 */
public abstract class CompareFilter<T> extends Filter<T> {
	
	@Override
	public void processInput(T input) {
		checkNull(input);
		//If the comparison test is passed, set the output
		if(compare(input))
			setOutput(input);
	}
	
	/**
	 * An abstract compare function that will determine
	 * if the input passes a comparison test over
	 * the previous output.
	 * @param input
	 */
	protected abstract boolean compare(T input);
	
	/**
	 * Checks that an input is not null.
	 * @param input
	 */
	private void checkNull(T input) {
		if(input == null)
			throw new NullPointerException("Given null input.");
	}
	

	@Override
	public void reset(T r) {
		setOutput(r);
	}

}
