import java.util.Iterator;


public class ScalarFilter extends Filter<Double> {
	private final double[] a;
	private final double[] b;
	private NBuffer<Double> aBuf;
	private NBuffer<Double> bBuf;
	
	public ScalarFilter(double[] a, double[] b){
		this.a = a;
		this.b = b;
		aBuf = new NBuffer<Double>(a.length);
		bBuf = new NBuffer<Double>(b.length);
		setBuffers(0.0, 0.0);
	}
	
	private void setBuffers(double prevInputs, double prevOutputs) {
		for(int i = 0; i < a.length; i++){
			aBuf.push(prevInputs);
		}
		for(int i = 0; i < b.length; i++){
			bBuf.push(prevOutputs);
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
		bBuf.push(input);
		Iterator<Double> itB = bBuf.iterator();
		double nextOutput = 0.0;
		int index = 0;
		while(itB.hasNext()){
			nextOutput += b[index]*itB.next();
			index++;
		}
		Iterator<Double> itA = aBuf.iterator();
		index = 0;
		while(itA.hasNext()){
			nextOutput -= a[index] * itA.next();
			index++;
		}
		setOutput(nextOutput);
		aBuf.push(nextOutput);
	}

}
