import java.util.Iterator;


public class ScalarFilter extends Filter<Double> {
	private final double[] a;
	private final double[] b;
	private NBuffer<Double> inputs;
	private NBuffer<Double> outputs;
	
	public ScalarFilter(double[] a, double[] b){
		this.a = a;
		this.b = b;
		inputs = new NBuffer<Double>(a.length);
		outputs = new NBuffer<Double>(b.length);
		setBuffers(0.0, 0.0);
	}
	
	private void setBuffers(double prevInputs, double prevOutputs) {
		for(int i = 0; i < a.length; i++){
			inputs.push(prevInputs);
		}
		for(int i = 0; i < b.length; i++){
			outputs.push(prevOutputs);
		}
	}

	public void reset(double r){
		double prevInputs = r;
		double prevOutputsNumerator = 0.0;
		double prevOutputsDenominator = 1;
		for(int i = 0; i < b.length; i++){
			prevOutputsNumerator += b[i];
		}
		for(int i = 0; i < a.length; i++){
			prevOutputsDenominator += a[i];
		}
		double prevOutputs = (r * prevOutputsNumerator) / prevOutputsDenominator;
		setBuffers(prevInputs, prevOutputs);
	}
	
	@Override
	protected void processInput(Double input) {
		outputs.push(input);
		Iterator<Double> itB = outputs.iterator();
		double nextOutput = 0.0;
		int index = 0;
		while(itB.hasNext()){
			nextOutput += b[index]*itB.next();
			index++;
		}
		Iterator<Double> itA = inputs.iterator();
		index = 0;
		while(itA.hasNext()){
			nextOutput -= a[index] * itA.next();
			index++;
		}
		setOutput(nextOutput);
		inputs.push(nextOutput);
	}

}
