
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
