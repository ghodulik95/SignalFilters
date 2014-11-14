/**
 * GenericFilter is an interface with Generic T that specifies
 * the public functions of a filter, which is just the one function
 * filter(T input) which outputs a T.
 * @author gmh75
 *
 * @param <T>	is an object
 */
public interface GenericFilter<T> {
	public T filter(T input);
}
