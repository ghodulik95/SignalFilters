import java.util.Iterator;
import java.util.LinkedList;

/**
 * NBuffer is an auxiliary class I made to store N generic
 * objects.  It is essentially a FIFO queue, except it only
 * has a push function, which will return the oldest object
 * in the list if and only if the list is full.
 * It also has an iterator function that returns an iterator of
 * the list of objects.
 * @author gmh73
 *
 * @param <T>
 */
public class NBuffer<T> {
	//The object of the buffer are stored in a LinkedList
	private LinkedList<T> buffer;
	//The max number of items in the buffer
	public final int N;
	
	/**
	 * Set N and construct the buffer
	 * @param n		The max number of values the buffer holds
	 */
	public NBuffer(int n){
		N = n;
		buffer = new LinkedList<T>();
	}
	
	/**
	 * Push an object into the buffer
	 * This will return and remove the last item in the list
	 * if there were N items in the buffer before calling push
	 * @param in	The value we are pushing into the buffer
	 * @return	Returns the last element remembered in the buffer if
	 * 			it is full (the last element is pushed out), otherwise
	 * 			returns null if the buffer was not full.
	 */
	public T push(T in){
		T out = null;
		buffer.add(in);
		//If this new input makes the list too big
		if(buffer.size() == N + 1){
			//remove the oldest item and set it to the output
			out = buffer.removeFirst();
		}
			
		return (T)out;
	}
	
	/**
	 * Fills the buffer with newVal
	 * @param newVal	The value the buffer is filled with
	 */
	public void reset(T newVal){
		for(int i = 0; i < N; i++){
			push(newVal);
		}
	}
	
	/**
	 * Simply returns the iterator of the buffer.
	 * This function breaks encapsulation!
	 * Please use cautiously: it is only intended
	 * for READ-ONLY operations.  There is no way to
	 * enforce this convention, so please keep it in mind
	 * when coding.
	 * @return	returns the iterator of the buffer
	 */
	public Iterator<T> iterator(){
		return buffer.iterator();
	}
	
	
}
