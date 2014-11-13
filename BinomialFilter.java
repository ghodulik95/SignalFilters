
public class BinomialFilter extends Filter<Double>{
	private FIRFilter bin;
	
	public BinomialFilter(int n){
		double[] b = new double[n];
		setBinomial(b);
		bin = new FIRFilter(b);
	}
	
	private void setBinomial(double[] b) {
		int n = b.length;
		int[] factorial = factorial(n);
		for(int i = 0; i < n; i++){
			b[i] = factorial[n - 1] / (factorial[i] * factorial[ n - i - 1]);
		}
	}
	
	private int[] factorial(int max){
		int[] f = new int[max];
		f[0] = 1;
		if(f.length > 1)
			f[1] = 1;
		else
			return f;
		
		int currFactorial = 1;
		for(int i = 2; i <= f.length; i++){
			currFactorial = currFactorial * i;
			f[i] = currFactorial;
		}
		return f;
	}

	@Override
	protected void processInput(Double input) {
		setOutput(bin.filter(input));
	}

}
