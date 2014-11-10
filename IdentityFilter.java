
public class IdentityFilter<T> extends Filter<T>{
	
	@Override
	protected void processInput(T input){
		setOutput(input);
	}

}
