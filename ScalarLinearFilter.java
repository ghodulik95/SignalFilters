import java.util.Iterator;

/**
 * ScalarLinearFilter extends Filter, and implements the scalar filter
 * described in the assignment. It uses NBuffer to remember previous
 * inputs and outputs.
 * @author gmh73
 *
 */
public class ScalarLinearFilter extends Filter<Double> {
	//The a values
	private final double[] a;
	//The b values
	private final double[] b;
	//The recorded inputs
	private NBuffer<Double> inputs;
	//The recorded outputs
	private NBuffer<Double> outputs;
	
	/**
	 * Sets initial values and sets the buffers to all zeroes
	 * @param a
	 * @param b
	 */
	public ScalarLinearFilter(double[] a, double[] b){
		this.a = a;
		this.b = b;
		inputs = new NBuffer<Double>(a.length);
		outputs = new NBuffer<Double>(b.length);
		setBuffers(0.0, 0.0);
	}
	
	/**
	 * Sets all items in the buffers to the given values
	 * @param prevInputs	all the previous inputs
	 * @param prevOutputs	all the previous outputs
	 */
	private void setBuffers(double prevInputs, double prevOutputs) {
		for(int i = 0; i < a.length; i++){
			inputs.push(prevInputs);
		}
		for(int i = 0; i < b.length; i++){
			outputs.push(prevOutputs);
		}
	}
	
	/**
	 * Reset the filter
	 * @param r
	 */
	public void reset(double r){
		//The previous inputs are set to r
		double prevInputs = r;
		//We must calculate the previous outputs
		double prevOutputsNumerator = 0.0;
		double prevOutputsDenominator = 1;
		//The numerator is a summation of b
		for(int i = 0; i < b.length; i++){
			prevOutputsNumerator += b[i];
		}
		//The denominator is 1 + a summation of a
		for(int i = 0; i < a.length; i++){
			prevOutputsDenominator += a[i];
		}
		//The output is also multiplied by r
		double prevOutputs = (r * prevOutputsNumerator) / prevOutputsDenominator;
		//set the previous values
		setBuffers(prevInputs, prevOutputs);
	}
	
	@Override
	protected void processInput(Double input) {
		outputs.push(input);
		//The next output will be calculated by adding
		//all the b_i*x_i terms then subtracting the 
		//a_i*y_i terms
		Iterator<Double> itB = outputs.iterator();
		double nextOutput = 0.0;
		int index = 0;
		//Sum all the b terms
		while(itB.hasNext()){
			nextOutput += b[index]*itB.next();
			index++;
		}
		Iterator<Double> itA = inputs.iterator();
		index = 0;
		//subtract all the a terms
		while(itA.hasNext()){
			nextOutput -= a[index] * itA.next();
			index++;
		}
		//set and push the next output
		setOutput(nextOutput);
		inputs.push(nextOutput);
	}

}
