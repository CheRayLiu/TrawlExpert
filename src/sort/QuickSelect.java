package sort;

//import search.GeneralCompare;

// Code from "Algorithms: 4th Edition" by Robert Sedgewick
// Adapted from the Sedgewick Quicksort implementation

/**
 * Implementation of the QuickSelect algorithm for selecting the kth largest data point in
 * a given array according to some GeneralCompare function.
 * @author TrawlStars, Inc.
 *
 */
public class QuickSelect {
	/**
	 * Partially sorts a comparable array such that elements smaller than the median occur in
	 * the first half of the array, and elements larger than the median occur in the second half
	 * @param a Array of comparable items
	 * @param gc Lambda function to compare items
	 */
	public static <T extends Comparable<T>> void median(T[] a, GeneralCompare<T> gc) {
		sort(a, 0, a.length - 1, a.length/2, gc);
	}
	
	/**
	 * Partially sorts a comparable array such that elements smaller than the median of a 
	 * range occur in the first half of the range, and elements larger than the median occur
	 * in the second half
	 * @param a Array of comparable items
	 * @param lo Lower bound of the range
	 * @param hi Upper bound of the range
	 * @param gc Lambda function to compare items
	 */
	public static <T extends Comparable<T>> void median(T[] a, int lo, int hi, GeneralCompare<T> gc) {
		sort(a, lo, hi, (hi + lo) / 2, gc);
	}
		
	/**
	 * Partially sorts a comparable array such that elements smaller than the kth largest element
	 * occur in the first half of the array, and larger elements occur in the second half
	 * @param a Array of comparable items
	 * @param k Pivot element that will be in its sorted place in the array
	 * @param gc Lambda function to compare items
	 */
	public static <T extends Comparable<T>> void partialSort(T[] a, int k, GeneralCompare<T> gc) {
		sort(a, 0, a.length - 1, k, gc);
	}

	/**
	 * Sorts the half of the array containing the kth (or median) index
	 * @param a Array of comparable items
	 * @param lo Lower bound index of the subarray to be sorted
	 * @param hi Upper bound index of the subarray to be sorted
	 * @param k Pivot element that will be in its sorted place in the array
	 * @param gc Lambda function to compare items
	 */
	public static <T extends Comparable<T>> void sort(T[] a, int lo, int hi, int k, GeneralCompare<T> gc) {
		if (hi <= lo)
			return;
		int j = partition(a, lo, hi, gc);
		if (j < k)
			sort(a, j + 1, hi, k, gc); // Sort right part a[j+1 .. hi].
		else if (j > k)
			sort(a, lo, j - 1, k, gc); // Sort left part a[lo .. j-1].
		return;
	}

	/**
	 * Places elements smaller than a partitioning element at smaller indices than the partitioning element
	 * Same algorithm for partitioning as standard QuickSort from 
	 * @param a Array of comparable items
	 * @param lo Index that scans from the left side of the array, points to an item to be compared
	 * @param hi Index that scans from the right side of the array, points to an item to be compared
	 * @param gc Lambda function to compare items
	 * @return
	 */
	private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi, GeneralCompare<T> gc) {
		// Partition into a[lo..i-1], a[i], a[i+1..hi].
		int i = lo, j = hi + 1; // left and right scan indices
		T v = a[lo]; // partitioning item

		while (true) { // Scan right, scan left, check for scan complete, and exchange.
			while (gc.compare(a[++i], v) < 0)
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

	/**
	 * Exchanges the values at two indices of an array
	 * @param a Array of comparable items
	 * @param b Index of an item to be swapped
	 * @param c Index of an item to be swapped
	 */
	private static <T extends Comparable<T>> void exch(Comparable<T>[] a, int b, int c) {
		Comparable<T> temp = a[c];
		a[c] = a[b];
		a[b] = temp;
	}
	
}
