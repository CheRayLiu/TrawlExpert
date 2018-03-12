package sort;

import biotree.Record;

public class RangeHelper {
	public static GeneralRange<Record> taxonID(String boundtype) {
		if (boundtype == "ANY") {
			GeneralRange<Record> range = p -> 0;
			return range;
		}
		else return null;
	}
	
	public static GeneralRange<Record> taxonID(String boundtype, int bound) {
		if (boundtype == "LOWER") {
			GeneralRange<Record> range = p -> p.getTaxonId() < bound ? 0 : 1;
			return range;
		}
		else if (boundtype == "HIGHER") {
			GeneralRange<Record> range = p -> p.getTaxonId() > bound ? 0 : 1;
			return range;
		}
		else if (boundtype == "EQUALS") {
			GeneralRange<Record> range = p -> p.getTaxonId() == bound ? 0 : 1;
			return range;
		}
		else return null;
	}

	public static GeneralRange<Record> taxonID(String boundtype, int leftbound, int rightbound) {
		if (boundtype == "LOWHIGH") {
			GeneralRange<Record> range = p -> p.getTaxonId() < leftbound ? -1 : (p.getTaxonId() > rightbound ? 1 : 0);
			return range;
		}
		else return null;
	}
}
