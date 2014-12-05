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
	
	/**
	 * Sets the output to the given input if the 
	 * compare test returns true for (input, prevOutput)
	 */
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
	 * Whether or not left and right matters is determined
	 * when overridden.  Ex. left compare right =?= right compare left
	 * Example: the compare may return left > right, or it might
	 * return left + right == 10
	 * @param left	The left operand of the comparison
	 * @param right		The right operand of the comparison
	 */
	public abstract boolean compare(T left, T right);
	
	/**
	 * Reset sets the previous output to r, so that new inputs
	 * will be compared against r, with r being on the right.
	 */
	@Override
	public void reset(T r) {
		setOutput(r);
	}

}
