/**
 * FilterN is an abstract class that extends Filter.  It
 * initializes an NBuffer which will store the last N inputs.
 * @author gmh73
 *
 * @param <T>
 */
public abstract class FilterN<T> extends Filter<T> {
	//The last N inputs
	protected NBuffer<T> buf;
	//The number of inputs since the output updated
	protected int inputsSinceNewOutput;
	//The number of inputs we are retaining
	public final int N;
	
	/**
	 * Sets the FilterN to size n
	 * @param n
	 */
	public FilterN(int n){
		checkN(n);
		buf = new NBuffer<T>(n);
		inputsSinceNewOutput = 0;
		N = n;
	}
	
	/**
	 * Checks if n is valid, ie >= 1
	 * @param n
	 */
	protected final void checkN(int n) {
		if(n <= 0)
			throw new IllegalArgumentException("N must be at least 1.");
	}
	

	@Override
	public void reset(T r) {
		setOutput(r);
		inputsSinceNewOutput = 0;
	}
}
