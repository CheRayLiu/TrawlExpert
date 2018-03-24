package search;

import com.sun.org.apache.bcel.internal.generic.NEW;

import data.Record;

public class Histogram {

	/**
	 * Generates a BST where each node contains the number of occurrences (value) in
	 * a year (key)
	 * 
	 * @param record
	 *            - An iterable of records
	 * @return - The BST
	 */
	public static BST<Integer, Integer> histogram(Iterable<Record> record) {
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		for (Record rec : record) {
			int year = rec.getDate().getYear();
			int count = 0;
			if (tree.get(year) != null) {
				count = tree.get(year);
				count += rec.getCount();
				tree.put(year, count);
			} else
				tree.put(year, rec.getCount());
		}
		return tree;
	}		
	
	public static int sum(Iterable<Record> records) {
		int sum = 0;
		for (Record r: records)
			sum += r.getCount();
		return sum;
	}
	
	public static void main(String[] args) {
		
	}
		
}
