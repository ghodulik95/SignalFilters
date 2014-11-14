/**
 * MinFilter extends ComparableFilter, setting the compare function
 * such that if the current input is less than the previous (via
 * compareTo() function), it sets the output to the current input.
 * @author gmh73
 *
 * @param <T>
 */
public class MinFilter<T extends Comparable<T>> extends ComparableFilter<T> {
	@Override
	protected void compare(T input) {
		if( input.compareTo(getPrevOutput()) <= 0){
			setOutput(input);
		}
	}
}
