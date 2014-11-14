/**
 * MinFilterN extends ComparableFilterN, setting the compare function
 * such that if the current input is less than the previous (via
 * compareTo() function), it returns true.
 * @author gmh73
 *
 * @param <T>
 */
public class MinFilterN<T extends Comparable<T>> extends CompareFilterN<T> {
	public MinFilterN(int n) {
		super(n);
	}

	@Override
	protected boolean compare(T input, T prevFiltered) {
		return input != null && input.compareTo(prevFiltered) <= 0;
	}
}
