package search;

public interface Field<Key, Value> {
	public Comparable<Key> field(Value a1);
}

