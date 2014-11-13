
public class GainFilter extends Filter<Double> {
	private FIRFilter gainFilter;
	
	public GainFilter(double gain){
		double[] b = new double[1];
		b[0] = gain;
		gainFilter = new FIRFilter(b);
	}

	@Override
	protected void processInput(Double input) {
		setOutput(gainFilter.filter(input));
	}
}
