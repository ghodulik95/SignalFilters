import java.util.Iterator;
import java.util.List;


public class FilterCascade<T> extends Filter<T>{
	private List<Filter<T>> cascade;
	public FilterCascade(List<Filter<T>> filters){
		if(filters.size() <= 0)
			throw new IllegalArgumentException("There must be at least one filter in the cascade.");
		cascade = filters;
	}
	
	@Override
	protected void processInput(T input) {
		Iterator<Filter<T>> it = cascade.iterator();
		T prev = input;
		while(it.hasNext()){
			prev = it.next().filter(prev);
		}
		setOutput(prev);
	}

}
