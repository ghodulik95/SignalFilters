/**
 * Filter is an abstract class that implements GenericFilter.
 * It sets up the general outline for how a filter works:
 * it processes an input, and returns the local output.
 * @author gmh73
 *
 * @param <T>	a type of object
 */
public abstract class Filter<T> implements GenericFilter<T>{
	private T output;

	public T getPrevOutput() {
		return output;
	}

	protected void setOutput(T prevOutput) {
		this.output = prevOutput;
	}
	
	public T filter(T input){
		processInput(input);
		return output;
	}

	protected abstract void processInput(T input);
}
