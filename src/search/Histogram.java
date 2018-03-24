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

	/**
	 * Prints a histogram based on a BST of records
	 * 
	 * @param record -An BST of records
	 */
	public static void printHistogram(BST<Integer,Integer> record) {
		int max = 0;
		int scale = 100;
		Iterable<Integer> results = record.keys();
		for (Integer year: results) {
			if (max < record.get(year)) max =record.get(year);

		}
		System.out.println("     |" + (new String(new char[scale]).replace('\0', '-')) + "|");
		String format = "%1$-5d|%2$-" + (scale + 1) + "s";
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
		
	
	public static int sum(Iterable<Record> records) {
		int sum = 0;
		for (Record r: records)
			sum += r.getCount();
		return sum;
	}
	
	public static void main(String[] args) {
		
	}
		
}
