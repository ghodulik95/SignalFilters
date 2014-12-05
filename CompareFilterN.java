import java.util.Iterator;

/**
 * ComparableFilterN is an abstract class that extends FilterN.
 * ComparableFilterN works similarly to ComparableFilter, where
 * an abstract compare() function is made to decide if the current
 * input should be the next input, except
 * it must keep track of how many inputs there has been since the
 * most recent update of the output.
 * When deciding the next output, it must check to see
 * if there has been N inputs since the last update of the output,
 * and if there has, it must use the abstract compare() function
 * to find the next output.
 * @author gmh73
 *
 * @param <T>
 */
public abstract class CompareFilterN<T> extends FilterN<T> implements Comparing<T>{
	
	/**
	 * Makes a new CompareFilterN that remembers n values. 
	 * @param n		The number of outputs the filter remembers.
	 */
	public CompareFilterN(int n) {
		super(n);
	}
	
	/**
	 * If the previous output is from an input for which compare returned true
	 * was < N inputs ago, the input is simply compared against the previous
	 * output.  If the previous output was updated >= N inputs ago, the new
	 * output is found by checking each remembered input. 
	 */
	@Override
	protected void processInput(T input) {
		//push the input into our buffer
		buf.push(input);
		//if its been N inputs since the last update of the output
		if(inputsSinceNewOutput == N - 1){
			//set the output based on our remembered N inputs
			setOutput(getFilteredValue());
			//reset the count
			//inputsSinceNewOutput = 0;
		}else{
			//if the last output was null (this is the fist input)
			//or the comparison test is passed for this input and the previous
			//output
			if(getPrevOutput() == null || compare(input, getPrevOutput())){
				//set the output to this input and reset the count
				setOutput(input);
				inputsSinceNewOutput = 0;
			}else{
				//else increment the count since the last output update
				inputsSinceNewOutput++;
			}
		}
	}

	/**
	 * An abstract compare function that will determine
	 * if the input passes a comparison test over
	 * the previous output.
	 * Whether or not left and right matters is determined
	 * when overridden.  Ex. left compare right =?= right compare left
	 * Example: the compare may return left > right, or it might
	 * return left + right == 10
	 * @param left	The left operand of the comparison
	 * @param right		The right operand of the comparison
	 */
	public abstract boolean compare(T left, T right);
	
	/**
	 * Iterates through the buffer of previous inputs
	 * to find the one/ oldest one that satisfies the comparison test
	 * @return	returns the best candidate in memory.
	 * 			Note that "best" is abstract, determined by the compare function.
	 */
	private T getFilteredValue(){
		Iterator<T> it = buf.iterator();
		T val = it.next();
		int count = N - 2;
		inputsSinceNewOutput = N - 1;
		//Check each stored input
		while(it.hasNext()){
			T temp = it.next();
			//Test the current object against the last one to pass
			//the test
			if(compare(temp, val)){
				//Set the last to pass the test
				val = temp;
				inputsSinceNewOutput = count;
			}
			count--;
		}
		return val;
	}

}
