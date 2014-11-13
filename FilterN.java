
public abstract class FilterN<T> extends Filter<T> {

	protected NBuffer<T> buf;
	protected int inputsSinceMax;
	public final int N;
	
	public FilterN(int n){
		checkN(n);
		buf = new NBuffer<T>(n);
		inputsSinceMax = 0;
		N = n;
	}
	
	private void checkN(int n) {
		if(n <= 0)
			throw new IllegalArgumentException("N must be at least 1.");
	}

	
}
