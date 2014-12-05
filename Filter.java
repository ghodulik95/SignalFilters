/**
 * Filter is an abstract class that implements GenericFilter.
 * It sets up the general outline for how a filter works:
 * it processes an input, and returns the local output.
 * @author gmh73
 *
 * @param <T>	a type of object
 */
public abstract class Filter<T> implements GenericFilter<T>{
	//The most recent/next output
	private T output;
	
	/**
	 * Returns the previous output.
	 * @return	the previous output
	 */
	public final T getPrevOutput() {
		return output;
	}
	
	/**
	 * Resets the output to r.
	 * Will have a different effect for different filters.
	 * @param r
	 */
	public abstract void reset(T r);
	
	/**
	 * Set the output to the given parameter.
	 * @param prevOutput	set the output to this value.
	 */
	protected final void setOutput(T prevOutput) {
		this.output = prevOutput;
	}
	
	/**
	 * The filter function calls the abstract function
	 * processInput(), then returns the local output variable.
	 */
	public final T filter(T input){
		processInput(input);
		return output;
	}
	
	/**
	 * An abstract function that will alter the local output
	 * to control the next output.
	 * @param input		the current input
	 */
	protected abstract void processInput(T input);
}
