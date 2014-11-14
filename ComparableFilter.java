/**
 * ComparableFilter is an abstract class that extends Filter.
 * It's generic object must extend Comparable.  ComparableFilter
 * calls an abstract function compare() when processing the input.
 * This function will decide whether the current input should become
 * the output based on some condition in compare(), ie maximum or min.
 * @author gmh73
 *
 * @param <T>
 */
public abstract class ComparableFilter<T extends Comparable<?>> extends Filter<T> {
	
	@Override
	public void processInput(T input) {
		checkNull(input);
		compare(input);
	}

	protected abstract void compare(T input);

	private void checkNull(T input) {
		if(input == null)
			throw new NullPointerException("Given null input.");
	}

}
