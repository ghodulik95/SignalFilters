import java.util.Iterator;

/**
 * ScalarLinearFilter extends Filter, and implements the scalar filter
 * described in the assignment. It uses NBuffer to remember previous
 * inputs and outputs.
 * @author gmh73
 *
 */
public class ScalarLinearFilter extends ScalarFilter {
	//The a values
	private final Double[] a;
	//The b values
	private final Double[] b;
	//The recorded inputs
	private NBuffer<Double> inputs;
	//The recorded outputs
	private NBuffer<Double> outputs;
	
	/**
	 * Sets initial values and sets the buffers to all zeroes
	 * @param a
	 * @param b
	 */
	public ScalarLinearFilter(Double[] a, Double[] b){
		this.a = a;
		this.b = b;
		inputs = new NBuffer<Double>(a.length);
		outputs = new NBuffer<Double>(b.length);
		resetBuffers(0.0, 0.0);
	}
	
	/**
	 * Sets all items in the buffers to the given values
	 * @param prevInputs	all the previous inputs
	 * @param prevOutputs	all the previous outputs
	 */
	private void resetBuffers(Double prevInputs, Double prevOutputs) {
		inputs.reset(prevInputs);
		outputs.reset(prevOutputs);
	}
	
	@Override
	protected void processInput(Double input) {
		outputs.push(input);
		//The next output will be calculated by adding
		//all the b_i*x_i terms then subtracting the 
		//a_i*y_i terms
		Iterator<Double> itB = outputs.iterator();
		Double nextOutput = 0.0;
		int index = 0;
		//Sum all the b terms
		while(itB.hasNext()){
			nextOutput += b[b.length - 1 - index]*itB.next();
			index++;
		}
		Iterator<Double> itA = inputs.iterator();
		index = 0;
		//subtract all the a terms
		while(itA.hasNext()){
			nextOutput -= a[a.length - 1 - index] * itA.next();
			index++;
		}
		//set and push the next output
		setOutput(nextOutput);
		inputs.push(nextOutput);
	}

	@Override
	public void reset(Double r) {
		//The previous inputs are set to r
		Double prevInputs = r;
		//We must calculate the previous outputs
		Double prevOutputsNumerator = 0.0;
		Double prevOutputsDenominator = 1.0;
		//The numerator is a summation of b
		for(int i = 0; i < b.length; i++){
			prevOutputsNumerator += b[i];
		}
		//The denominator is 1 + a summation of a
		for(int i = 0; i < a.length; i++){
			prevOutputsDenominator += a[i];
		}
		//The output is also multiplied by r
		Double prevOutputs = (r * prevOutputsNumerator) / prevOutputsDenominator;
		//set the previous values
		resetBuffers(prevInputs, prevOutputs);
	}

}
