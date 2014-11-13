
public class AveragingFilterN extends FilterN<Double>{
	private int size;
	private double average;
	
	public AveragingFilterN(int n) {
		super(n);
		size = 0;
		average = 0.0;
	}

	@Override
	protected void processInput(Double input) {
		assert size <= N : "The size should never increase to be greater than N.";
		if(size == N){
			double last = buf.push(input);
			average = average - (last / size) + (input / size);
		}else{
			size++;
			average = (((average * (size - 1)) + input) / size);
		}
		setOutput(average);
	}

}
