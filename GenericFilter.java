/**
 * GenericFilter is an interface with Generic T that specifies
 * the public functions of a filter, which is just the one function
 * filter(T input) which outputs a T.
 * @author gmh75
 *
 * @param <T>	is an object
 */
public interface GenericFilter<T> {
	/**
	 * A GenericFilter must have a filter function that takes
	 * an input T and returns a T.
	 * @param input		the input
	 * @return	the output
	 */
	public T filter(T input);
}
