
public class AveragingFilter extends Filter<Double>{
	private int size;
	private double average;
	
	public AveragingFilter(){
		size = 0;
		average = 0.0;
	}
	
	@Override
	protected void processInput(Double input) {
		size++;
		average = (((average * (size - 1)) + input) / size);
		setOutput(average);
	}

}
