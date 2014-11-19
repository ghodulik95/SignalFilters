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
public abstract class CompareFilter<T> extends Filter<T> implements Comparing<T>{
	
	@Override
	public void processInput(T input) {
		//If the comparison test is passed, set the output
		if(compare(input, getPrevOutput()))
			setOutput(input);
	}
	
	/**
	 * An abstract compare function that will determine
	 * if the input passes a comparison test over
	 * the previous output.
	 * @param input
	 */
	public abstract boolean compare(T left, T right);
	

	@Override
	public void reset(T r) {
		setOutput(r);
	}

}
