package search;

public interface GeneralCompare<T extends Comparable<T>> {
	public int compare(T a1, T a2);
}
