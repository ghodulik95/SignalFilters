
public class MinFilter<T extends Comparable<T>> extends ComparableFilter<T> {
	@Override
	protected void compare(T input) {
		if( input.compareTo(getPrevOutput()) <= 0){
			setOutput(input);
		}
	}
}
