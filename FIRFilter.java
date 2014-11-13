import java.util.Arrays;


public class FIRFilter extends Filter<Double> {
	private ScalarFilter fir;
	
	public FIRFilter(double[] b){
		double[] a = new double[1];
		a[0] = 0.0;
		fir = new ScalarFilter(a,b);
	}
	
	@Override
	protected void processInput(Double input) {
		setOutput(fir.filter(input));
	}

}
