import java.util.Iterator;

/**
 * ComparableFilterN is an abstract class that extends FilterN.
 * ComparableFilterN works similarly to ComparableFilter, where
 * an abstract compare() function is made to decide if the current
 * input should be the next input, except
 * it must keep track of how many inputs there has been since the
 * most recent update of the output.
 * When deciding the next output, it must check to see
 * if there has been N inputs since the last update of the output,
 * and if there has, it must use the abstract compare() function
 * to find the next output.
 * @author gmh73
 *
 * @param <T>
 */
public abstract class ComparableFilterN<T extends Comparable<?>> extends FilterN<T> {

	public ComparableFilterN(int n) {
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
		buf.push(input);
		if(inputsSinceNewOutput == N ){
			setOutput(getFilteredValue());
			inputsSinceNewOutput = 0;
		}else{
			if(getPrevOutput() == null || compare(input, getPrevOutput())){
				setOutput(input);
				inputsSinceNewOutput = 0;
			}else{
				inputsSinceNewOutput++;
			}
		}
	}

	protected abstract boolean compare(T input, T prevFiltered);

	private T getFilteredValue(){
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
