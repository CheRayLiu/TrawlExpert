package tuple;

public class Tuple<T1, T2, T3> {
	T1 fst;
	T2 snd;
	T3 trd;
	
	public Tuple(T1 fst, T2 snd,T3 trd) {
		this.fst = fst;
		this.snd = snd;
		this.trd= trd;
	}
	
	public T1 fst() {
		return fst;
	}
	
	public T2 snd() {
		return snd;
	}
	public T3 trd() {
		return trd;
	}
}
