package search;

// The interface for a function that returns one field (piece of data) from a record
public interface Field<Key extends Comparable<Key>, Value> {
	public Key field(Value a1);
}

