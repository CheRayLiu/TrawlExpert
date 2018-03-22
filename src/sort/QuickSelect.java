package sort;

import search.GeneralCompare;

// Code from "Algorithms: 4th Edition" by Robert Sedgewick
// Adapted from the Sedgewick Quicksort implementation

public class QuickSelect<Key, Record> {

	private static int median;
	private static int count;
	private GeneralCompare<Key> gc;

	//
	public static void sort(Comparable[] a) {
		//StdRandom.shuffle(a); // Eliminate dependence on input
		median = a.length/2; 
		sort(a, 0, a.length - 1);
	}

	public static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi);
		if (j < median)
			sort(a, j + 1, hi); // Sort right part a[j+1 .. hi].
		else if (j > median)
			sort(a, lo, j - 1); // Sort left part a[lo .. j-1].
		return;
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		// Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		Comparable v = a[lo]; // partitioning item

		while (true) { // Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++i], v))
				if (i == hi)
					break;
			while (less(v, a[--j]))
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
