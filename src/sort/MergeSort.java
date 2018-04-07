package sort;

public class MergeSort{
	/*// Main Function for Testing Purposes Only
	public static void main(String[] args) {
		GeneralCompare<Integer> b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		Integer[] test = {3, 4, 2, 1, 5, 7, 9, 10, 11};
		//Integer[] test = {2, 1};
		sort(test, 0, test.length - 1, b1);
		
		for (int i = 0 ; i < (test.length) ; i++) {
			System.out.println(test[i]);
		}
	}*/

	/**
	 * Wrapper function for the MergeSort implementation
	 * @param x Array of comparable items to be sorted
	 * @param lo Lower bound of a sub-array to be sorted
	 * @param hi Upper bound of a  sub-array to be sorted
	 * @param gc A lambda function that compares two comparable items
	 */
	public static <T extends Comparable<T>> void sort(T[] x, int lo, int hi, GeneralCompare<T> gc) {
		T[] aux;
		aux = (T[]) new Comparable[x.length];
		sortWrapped(x, lo, hi, gc, aux);
	}
	
	/**
	 * Recursively sort each half of a sub-array
	 * @param lo Lower bound of a sub-array to be sorted
	 * @param hi Upper bound of a  sub-array to be sorted
	 * @param gc A lambda function that compares two comparable items
	 * @param aux Auxiliary array to accommodate temporary memory use in the algorithm
	 */
	private static <T extends Comparable<T>> void sortWrapped(T[] x, int lo, int hi, GeneralCompare<T> gc, T[] aux) {
		int n = hi - lo;
		if(n < 1)
			return;
		// Recursively sort each half of the array
		int mid = lo + (n/2);
		sortWrapped(x, lo, mid, gc, aux);
		sortWrapped(x, mid+1, hi, gc, aux);
		merge(x, lo, hi, gc, aux);
	}

	/**
	 * Merges two sorted sub-arrays into a single sorted array
	 * @param x Array of comparable items to be sorted
	 * @param lo Lower bound of a sub-array to be sorted
	 * @param hi Upper bound of a  sub-array to be sorted
	 * @param gc A lambda function that compares two comparable items
	 * @param aux Auxiliary array to accommodate temporary memory use in the algorithm
	 */
	private static <T extends Comparable<T>> void merge(T[] x, int lo, int hi, GeneralCompare<T> gc, T[] aux){

		int n = hi - lo;
		int mid = lo + (n/2);
		
		for(int k = lo; k <= hi; k++){
			aux[k] = x[k];
		}

		int i = lo; 
		int j = mid + 1; 
		for (int k = lo; k <= hi ; k++) {
			if (i > mid)
				x[k] = aux[j++]; //All elems in first half already added to x
			else if (j > hi)
				x[k] = aux[i++]; //All elems in second half already added to x
			else if (gc.compare(aux[i], aux[j]) > 0)
				x[k] = aux[j++]; 
			else
				x[k] = aux[i++];
		}
	}
}
