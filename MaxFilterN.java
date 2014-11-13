
public class MaxFilterN<T extends Comparable<T>> extends ComparableFilterN<T> {

	public MaxFilterN(int n) {
		super(n);
	}

	@Override
	protected boolean compare(T input, T prevFiltered) {
		return 0 <= input.compareTo(prevFiltered);
	}
}
