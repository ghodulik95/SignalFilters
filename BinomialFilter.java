/**
 * BinomialFilter extends Filter, and expresses commonality with
 * FIRFilter through containment.  It simply initializes a FIRFIlter
 * with a b input of the binomial coefficients up to N.
 * The processInput function simple sets the output to the output
 * of calling filter on the contained FIRFilter.
 * @author gmh73
 *
 */
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
