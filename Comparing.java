
/**
 * Classes that implement the Comparing interface
 * must have a compare boolean that compares two inputs
 * against each other, and returns true or false based off the
 * comparison.
 * @author gmh73
 *
 */
public interface Comparing<T> {
	/**
	 * An compare function
	 * @param left	The left operand of the compare function
	 * @param right	 The right oprand of a compare function
	 * @return	True if the comparison is true
	 */
	public boolean compare(T left, T right);
}
