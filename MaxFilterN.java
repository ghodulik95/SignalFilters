import java.util.Iterator;


public class MaxFilterN<T extends Comparable<T>> extends ComparableNFilter<T> {

	public MaxFilterN(int n) {
		super(n);
	}

	@Override
	protected boolean isFilteredValue(T input, T prevFiltered) {
		return 0 <= input.compareTo(prevFiltered);
	}
}
