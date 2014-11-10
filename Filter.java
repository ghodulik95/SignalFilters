
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
