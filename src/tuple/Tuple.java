package tuple;

public class Tuple<T1, T2> {
	T1 fst;
	T2 snd;
	
	public Tuple(T1 fst, T2 snd) {
		this.fst = fst;
		this.snd = snd;
	}
	
	public T1 fst() {
		return fst;
	}
	
	public T2 snd() {
		return snd;
	}
}
