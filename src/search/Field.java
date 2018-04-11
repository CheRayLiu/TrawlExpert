package search;

import java.io.Serializable;

/**
 * The interface for a function that returns one field (piece of data) from a record
 * @author Haley Glavina
 *
 * @param <Key> The key's type (extracted from the value) -- must implement Comparable
 * @param <Value> The value's type (from which to extract the key)
 */
public interface Field<Key extends Comparable<Key>, Value> extends Serializable {
	/**
	 * Given a value of type Value, extract a key of type Key from the Value.
	 * @param a The value to be worked upon.
	 * @return The key extracted from the value.
	 */
	public Key field(Value a);
}

