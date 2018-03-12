package sort;

import data.Record;
import data.Date;
public class RangeHelper {
	public static GeneralRange<Record> taxonID(Bound boundtype) {
		if (boundtype == Bound.ANY) {
			GeneralRange<Record> range = p -> 0;
			return range;
		}
		else return null;
	}
	
	public static GeneralRange<Record> taxonID(Bound boundtype, int bound) {
		if (boundtype == Bound.LOWER) {
			GeneralRange<Record> range = p -> p.getTaxonId() < bound ? 0 : 1;
			return range;
		}
		else if (boundtype == Bound.UPPER) {
			GeneralRange<Record> range = p -> p.getTaxonId() > bound ? 0 : -1;
			return range;
		}
		else if (boundtype == Bound.EQUALS) {
			GeneralRange<Record> range = p -> p.getTaxonId() - bound ;
			return range;
		}
		else return null;
	}

	public static GeneralRange<Record> taxonID(Bound boundtype, int leftbound, int rightbound) {
		if (boundtype == Bound.LOWHIGH) {
			GeneralRange<Record> range = p -> p.getTaxonId() < leftbound ? -1 : (p.getTaxonId() > rightbound ? 1 : 0);
			return range;
		}
		else return null;
	}
	
	public static GeneralRange<Record> longitude(Bound boundtype) {
		if (boundtype == Bound.ANY) {
			GeneralRange<Record> range = p -> 0;
			return range;
		}
		else return null;
	}
	
	public static GeneralRange<Record> longitude(Bound boundtype, float bound) {
		if (boundtype == Bound.LOWER) {
			GeneralRange<Record> range = p -> p.getLongitude() < bound ? 0 : 1;
			return range;
		}
		else if (boundtype == Bound.UPPER) {
			GeneralRange<Record> range = p -> p.getLongitude() > bound ? 0 : -1;
			return range;
		}
		else if (boundtype == Bound.EQUALS) {
			GeneralRange<Record> range = p -> Float.compare(p.getLongitude(), bound) ;
			return range;
		}
		else return null;
	}

	public static GeneralRange<Record> longitude(Bound boundtype, float leftbound, float rightbound) {
		if (boundtype == Bound.LOWHIGH) {
			GeneralRange<Record> range = p -> p.getLongitude() < leftbound ? -1 : (p.getLongitude() > rightbound ? 1 : 0);
			return range;
		}
		else return null;
	}
	
	public static GeneralRange<Record> latitude(Bound boundtype) {
		if (boundtype == Bound.ANY) {
			GeneralRange<Record> range = p -> 0;
			return range;
		}
		else return null;
	}
	
	public static GeneralRange<Record> latitude(Bound boundtype, float bound) {
		if (boundtype == Bound.LOWER) {
			GeneralRange<Record> range = p -> p.getLatitude() < bound ? 0 : 1;
			return range;
		}
		else if (boundtype == Bound.UPPER) {
			GeneralRange<Record> range = p -> p.getLatitude() > bound ? 0 : -1;
			return range;
		}
		else if (boundtype == Bound.EQUALS) {
			GeneralRange<Record> range = p -> Float.compare(p.getLatitude(), bound);
			return range;
		}
		else return null;
	}

	public static GeneralRange<Record> latitude(Bound boundtype, int leftbound, int rightbound) {
		if (boundtype == Bound.LOWHIGH) {
			GeneralRange<Record> range = p -> p.getLatitude() < leftbound ? -1 : (p.getLatitude() > rightbound ? 1 : 0);
			return range;
		}
		else return null;
	}
	
	public static GeneralRange<Record> date(Bound boundtype) {
		if (boundtype == Bound.ANY) {
			GeneralRange<Record> range = p -> 0;
			return range;
		}
		else return null;
	}
	
	public static GeneralRange<Record> date(Bound boundtype, Date bound) {
		if (boundtype == Bound.LOWER) {
			GeneralRange<Record> range = p -> p.getDate().compareTo(bound) == -1 ? 0 : 1;
			return range;
		}
		else if (boundtype == Bound.UPPER) {
			GeneralRange<Record> range = p -> p.getDate().compareTo(bound) == 1 ? 0 : -1;
			return range;
		}
		else if (boundtype == Bound.EQUALS) {
			GeneralRange<Record> range = p -> p.getDate().compareTo(bound) ;
			return range;
		}
		else return null;
	}

	public static GeneralRange<Record> date(Bound boundtype, Date leftbound, Date rightbound) {
		if (boundtype == Bound.LOWHIGH) {
			GeneralRange<Record> range = p -> p.getDate().compareTo(leftbound) == -1 ? -1 : (p.getDate().compareTo(rightbound) == 1 ? 1 : 0);
			return range;
		}
		else return null;
	}
}
