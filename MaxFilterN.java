/**
 * MaxFilterN extends ComparableFilterN, setting the compare function
 * such that if the current input is greater than the previous (via
 * compareTo() function), it returns true.
 * @author gmh73
 *
 * @param <T>
 */
public class MaxFilterN<T extends Comparable<T>> extends CompareFilterN<T> {

	public MaxFilterN(int n) {
		super(n);
	}

	@Override
	protected boolean compare(T input, T prevFiltered) {
		return input != null && 0 <= input.compareTo(prevFiltered);
	}
}
