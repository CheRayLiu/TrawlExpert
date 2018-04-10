package search.trawl;

import data.Record;
import search.RedBlackTree;

public class Histogram {

	/**
	 * Generates a BST where each node contains the number of occurrences (value) in
	 * a year (key)
	 * 
	 * @param record
	 *            - An iterable of records
	 * @return - The BST
	 */
	public static RedBlackTree<Integer, Integer> histogram(Iterable<Record> record) {
		RedBlackTree<Integer, Integer> tree = new RedBlackTree<Integer, Integer>(a -> 0, (n0, n1) -> n0.compareTo(n1));
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

	/**
	 * Prints a histogram based on a BST of records
	 * 
	 * @param record -An BST of records
	 */
	public static void printHistogram(RedBlackTree<Integer,Integer> record) {
		int max = 0;
		int scale = 100;
		Iterable<Integer> results = record.keys();
		
		//Finds the max individual count
		for (Integer year: results) {
			if (max < record.get(year)) max =record.get(year);

		}
		
		//Based the scale on the max
		System.out.println("     |" + (new String(new char[scale]).replace('\0', '-')) + "|");
		String format = "%1$-5d|%2$-" + (scale + 1) + "s";
		
		//Print out histogram based on scale
		for (Integer year: results) {
			String s = "=";
			int loopc = (int) ((float)(record.get(year)/ (float) max) * scale);
			for (int j=0; j< loopc; j++) {
				s+="=";
			}
			System.out.format(format, year, s);
			System.out.println("| " + record.get(year));
		}
		System.out.format("Scale: one = is %d individuals.\n", max / scale);
	}
		
	/**
	 * Gets the count of the record 
	 * 
	 * @param sum count of the record
	 */
	public static int sum(Iterable<Record> records) {
		int sum = 0;
		for (Record r: records)
			sum += r.getCount();
		return sum;
		
	}
}
	