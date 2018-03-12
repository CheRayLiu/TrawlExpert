package search;

public class Field  {
	// Access desired field for comparison (the field to be used as a key for each record)
	public static Comparable field(Comparable fieldT, Comparable[] record) {
		
		String type = fieldT.getClass().getSimpleName();
		if (type == "Integer")
			return record[0];
		if (type == "String")
			return record[1];
		return null;
		
	}
}
