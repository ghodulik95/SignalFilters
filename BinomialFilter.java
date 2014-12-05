/**
 * BinomialFilter extends Filter, and expresses commonality with
 * FIRFilter through containment.  It simply initializes a FIRFIlter
 * with a b input of the binomial coefficients up to N.
 * The processInput function simple sets the output to the output
 * of calling filter on the contained FIRFilter.
 * @author gmh73
 *
 */
public class BinomialFilter extends FIRFilter {
	
	/**
	 * A BinomialFilter is just a FIRFilter
	 * with a b that is binomial coefficients
	 * @param n
	 */
	private BinomialFilter(Double[] b){
		super(b);
	}
	
	/**
	 * Set each element of b to its corresponding
	 * binomial coefficient
	 * @param b
	 */
	private static void setBinomial(Double[] b) {
		int n = b.length - 1;
		//Get all the factorials from 0 to n
		int[] factorial = factorial(n);
		//Set the binomial coefficients
		for(int i = 0; i <= n; i++){
			b[i] = 1.0*factorial[n] / (factorial[i] * factorial[ n - i]);
		}
	}
	
	/**
	 * Calculates factorials up to and including max.
	 * @param max	a number
	 * @return	an array of the factorial from 0 to max
	 */
	private static int[] factorial(int max){
		int[] f = new int[max+1];
		//factorial of 0 is 1
		f[0] = 1;
		
		//Calculate each next factorial
		for(int i = 1; i <= max; i++){
			f[i] = f[i-1]*i;
		}
		return f;
	}
	
	/**
	 * Factory method for BinomialFilter
	 * Makes a BinomialFilter with inut n
	 * @param n		the n in the expression (n choose k) for a binomial filter.
	 * 				Note that with n = n1, the number of values in b is actually
	 * 				n1 + 1, not n1
	 * @return		returns a new BinomialFilter
	 */
	public static BinomialFilter getInstance(int n){
		if(n < 0)
			throw new IllegalArgumentException("N cannot be negative");
		//Set up the factorial array for b
		Double[] b = new Double[n+1];
		setBinomial(b);
		return new BinomialFilter(b);
	}

}
