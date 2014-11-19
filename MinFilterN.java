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
	public boolean compare(T left, T right) {
		return left != null && left.compareTo(right) <= 0;
	}
}
