
public class BinomialFilter extends Filter<Double>{
	private FIRFilter bin;
	
	public BinomialFilter(int n){
		double[] b = new double[n+1];
		setBinomial(b);
		bin = new FIRFilter(b);
	}
	
	private void setBinomial(double[] b) {
		int n = b.length - 1;
		int[] factorial = factorial(n);
		for(int i = 0; i <= n; i++){
			b[i] = factorial[n] / (factorial[i] * factorial[ n - i]);
		}
	}
	
	private int[] factorial(int max){
		int[] f = new int[max+1];
		f[0] = 1;
	
		for(int i = 1; i <= max; i++){
			f[i] = f[i-1]*i;
		}
		return f;
	}

	@Override
	protected void processInput(Double input) {
		setOutput(bin.filter(input));
	}

}
