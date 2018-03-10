package sort;

public interface GeneralCompare<T> {
	/**
	 * Compare two Comparable elements based on an arbitrary ordering definition.
	 * @param a1 The first value to be compared.
	 * @param a2 The second value to be compared
	 * @return Integer < 0 if a1 is less than a2, 0 if equal and > 0 if a1 is bigger than a2
	 */
	public int compare(Comparable<T> a1, Comparable<T> a2);
}