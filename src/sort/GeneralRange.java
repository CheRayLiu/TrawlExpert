package sort;

/**
 * Interface describing a lambda function that takes one object of a type T and returns whether some aspect of it
 * is in some range defined by the function.
 * Should return < 0 if it is less than the range, = 0 if it is in the range and > 0 if it is bigger than the range.
 * @author Christopher W. Schankula
 *
 * @param <T> The type parameter, can be any Comparable object.
 */
public interface GeneralRange<T extends Comparable<T>> {
	/**
	 * Return whether a given comparable element is in a bound according to some field in that object.
	 * @param a The value to be compared to the range.
	 * @return Integer < 0 if it is less than the range, = 0 if it is in the range and > 0 if it is bigger than the range.
	 */
	public int isInBounds(T a);
}
