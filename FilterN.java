/**
 * FilterN is an abstract class that extends Filter.  It
 * initializes an NBuffer which will store the last N inputs.
 * @author gmh73
 *
 * @param <T>
 */
public abstract class FilterN<T> extends Filter<T> {

	protected NBuffer<T> buf;
	protected int inputsSinceNewOutput;
	public final int N;
	
	public FilterN(int n){
		checkN(n);
		buf = new NBuffer<T>(n);
		inputsSinceNewOutput = 0;
		N = n;
	}
	
	private void checkN(int n) {
		if(n <= 0)
			throw new IllegalArgumentException("N must be at least 1.");
	}

	
}
