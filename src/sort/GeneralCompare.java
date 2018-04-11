package sort;

import java.io.Serializable;
/**
 * Interface describing a lambda function that takes two objects of a type T and compares them in some way.
 * Should return < 0 if the first is less, = 0 if they are equal and > 0 if the first is larger.
 * @author Christopher W. Schankula
 *
 * @param <T> The type parameter, can be any Comparable object.
 */
public interface GeneralCompare<T extends Comparable<T>> extends Serializable {
	/**
	 * Compare two Comparable elements based on an arbitrary ordering definition.
	 * @param a1 The first value to be compared.
	 * @param a2 The second value to be compared
	 * @return Integer < 0 if a1 is less than a2, 0 if equal and > 0 if a1 is bigger than a2
	 */
	public int compare(T a1, T a2);
}
