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
	
	/**
	 * A BinomialFilter is just a FIRFilter
	 * with a b that is binomial coefficients
	 * @param n
	 */
	public BinomialFilter(int n){
		double[] b = new double[n+1];
		setBinomial(b);
		bin = new FIRFilter(b);
	}
	
	/**
	 * Set each element of b to its corresponding
	 * binomial coefficient
	 * @param b
	 */
	private void setBinomial(double[] b) {
		int n = b.length - 1;
		//Get all the factorials from 0 to n
		int[] factorial = factorial(n);
		//Set the binomial coefficients
		for(int i = 0; i <= n; i++){
			b[i] = factorial[n] / (factorial[i] * factorial[ n - i]);
		}
	}
	
	/**
	 * Calculates factorials up to and including max.
	 * @param max	a number
	 * @return	an array of the factorial from 0 to max
	 */
	private int[] factorial(int max){
		int[] f = new int[max+1];
		//factorial of 0 is 1
		f[0] = 1;
		
		//Calculate each next factorial
		for(int i = 1; i <= max; i++){
			f[i] = f[i-1]*i;
		}
		return f;
	}

	@Override
	protected void processInput(Double input) {
		setOutput(bin.filter(input));
	}

	@Override
	public void reset(Double r) {
		bin.reset(r);
	}

}
