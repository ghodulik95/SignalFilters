import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NBuffer<T> {
	private List<T> buffer;
	public final int N;
	
	public NBuffer(int n){
		N = n;
		buffer = new LinkedList<T>();
	}
	
	public T push(T in){
		T out = null;
		if(buffer.size() == N){
			buffer.add(in);
			out = buffer.remove(buffer.size() - 1);
		}
		
		buffer.add(in);
		return (T)out;
	}
	
	public Iterator<T> iterator(){
		return buffer.iterator();
	}
	
	
}
