import java.util.Iterator;
import java.util.List;

/**
 * FilterCascade extends Filter.  It's constructor
 * takes a list of filters, and then when processing the input
 * it puts the input through each filter in the list.
 * @author gmh73
 *
 * @param <T>
 */
public class FilterCascade<T> extends Filter<T>{
	//The list of filters
	private List<Filter<T>> cascade;
	
	/**
	 * A constructor that takes a list of filters for cascading
	 * @param filters	a list of filters
	 */
	public FilterCascade(List<Filter<T>> filters){
		if(filters.size() <= 0)
			throw new IllegalArgumentException("There must be at least one filter in the cascade.");
		cascade = filters;
	}
	
	@Override
	protected void processInput(T input) {
		//Go through each filter then set the output to
		//the final result
		Iterator<Filter<T>> it = cascade.iterator();
		T prev = input;
		while(it.hasNext()){
			prev = it.next().filter(prev);
		}
		setOutput(prev);
	}

	@Override
	public void reset(T r) {
		Iterator<Filter<T>> it = cascade.iterator();
		while(it.hasNext()){
			it.next().reset(r);
		}
	}

}
