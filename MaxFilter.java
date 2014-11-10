
public class MaxFilter<T extends Comparable<T>> extends ComparableFilter<T>{
	
	@Override
	protected void compare(T input) {
		if( 0 <= input.compareTo(getPrevOutput())){
			setOutput(input);
		}
	}

}
