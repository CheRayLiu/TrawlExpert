package sort;

public class Range<Key extends Comparable<Key>> {
	private final Bound boundType;
	private final Key lower;
	private final Key upper;
	
	public Range(Bound bt) throws Exception {
		if (bt == Bound.ANY) {
			boundType = bt;
			lower = null;
			upper = null;
		}
		else
			throw new Exception("Must provide ANY bound.");
	}
	
	public Range(Bound bt, Key upperOrLower) throws Exception {
		if (bt == Bound.UPPER) {
			upper = upperOrLower;
			lower = null;
		} else if (bt == Bound.LOWER) {
			upper = upperOrLower;
			lower = null;
		}
		else 
			throw new Exception("Must provide UPPER OR LOWER bound.");
		boundType = bt;
	}
	
	public Range(Bound bt, Key lower, Key upper) throws Exception {
		if (bt == Bound.LOWHIGH) {
			this.lower = lower;
			this.upper = upper;
		} else
			throw new Exception("Must provide LOWHIGH bound.");
		this.boundType = bt;
	}
	
<<<<<<< HEAD
	public <T> boolean inBounds(T key) {
=======
<<<<<<< HEAD
	public <T> boolean inBounds(T key) {
=======
	public boolean inBounds(Key key, GeneralCompare<Key> gc) {
>>>>>>> fd3aa70... add Bound and Range
>>>>>>> refs/remotes/origin/master
		if (boundType == Bound.ANY) return true;
		else if (boundType == Bound.LOWER) 	return gc.compare(lower, key) <= 0;
		else if (boundType == Bound.UPPER) 	return gc.compare(upper, key) >= 0;
		else									return gc.compare(lower, key) <= 0 && gc.compare(upper, key) >= 0;
	}
}
