import java.util.Iterator;


public abstract class ComparableNFilter<T extends Comparable<?>> extends NFilter<T> {

	public ComparableNFilter(int n) {
		super(n);
	}

	@Override
	protected void processInput(T input) {
		checkNull(input);
		setNextValue(input);
	}

	private void checkNull(T input) {
		if(input == null)
			throw new IllegalArgumentException("Input must not be null.");
	}

	private void setNextValue(T input) {
		if(inputsSinceMax == N){
			buf.push(input);
			setOutput(getFilteredValue());
			inputsSinceMax = 0;
		}else{
			if(compare(input, getPrevOutput())){
				setOutput(input);
				inputsSinceMax = 0;
			}else{
				inputsSinceMax++;
			}
		}
	}

	protected abstract boolean compare(T input, T prevFiltered);

	protected T getFilteredValue(){
		Iterator<T> it = buf.iterator();
		T val = it.next();
		while(it.hasNext()){
			T temp = it.next();
			if(compare(temp, val))
				val = temp;
		}
		return val;
	}

}
