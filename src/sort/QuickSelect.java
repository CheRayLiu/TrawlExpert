package sort;

import search.GeneralCompare;

// Code from "Algorithms: 4th Edition" by Robert Sedgewick
// Adapted from the Sedgewick Quicksort implementation

public class QuickSelect<Key, Record> {

	private int median;
	private int count;
	private GeneralCompare<Key> gc;

	public static void main(String[] args) {
		//{1, 2, 3, 4, 5, 6, 7, 8, 9} 5 is median. 
		int[] test = {4, 6, 7, 2, 3, 9, 8, 1, 5};
		GeneralCompare<Integer> b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		
		sort<Integer, Integer>(test, b1);
	}
	
	public void sort(Comparable<Record>[] a, GeneralCompare<Key> genComp) {
		//StdRandom.shuffle(a); // Eliminate dependence on input
		gc = genComp;
		median = a.length/2; 
		sort(a, 0, a.length - 1);
	}

	public void sort(Comparable<Record>[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		if (j < median)
			sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
		else if (j > median)
			sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
		return;
	}

	private int partition(Comparable<Record>[] a, int lo, int hi) {
		// Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable v = a[lo]; // partitioning item

		while (true) { // Scan right, scan left, check for scan complete, and exchange.
			while (gc.compare((Value) a[++i], (Value) v) < 0)
				if (i == hi)
					break;
			while (gc.compare(v, a[--j]) < 0)
				if (j == lo)
					break;
			if (i >= j)
				break;
			exch(a, i, j);
		}

		exch(a, lo, j);
		return j;
	}

	private static void exch(Comparable[] a, int b, int c) {
		Comparable temp = a[c];
		a[c] = a[b];
		a[b] = temp;
	}
	
}
