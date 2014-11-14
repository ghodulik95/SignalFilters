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
	private LinkedList<T> buffer;
	public final int N;
	
	public NBuffer(int n){
		N = n;
		buffer = new LinkedList<T>();
	}
	
	public T push(T in){
		T out = null;
		buffer.add(in);
		if(buffer.size() == N + 1){
			out = buffer.removeFirst();
		}
			
		return (T)out;
	}
	
	public Iterator<T> iterator(){
		return buffer.iterator();
	}
	
	
}
