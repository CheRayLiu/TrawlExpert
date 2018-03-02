package sort;

import java.lang.reflect.Array;

public class MergeSort{

	private static Comparable[] aux;
	
	public static void main(String[] args) {
		GeneralCompare b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		Integer[] test = {3, 4, 2, 1, 5, 7, 9, 10, 11};
		//Integer[] test = {2, 1};
		sort(test, 0, test.length - 1, b1);
		
		for (int i = 0 ; i < (test.length) ; i++) {
			System.out.println(test[i]);
		}
	}

	public static void sort(Comparable[] x, int lo, int hi, GeneralCompare gc) {
		aux = new Comparable[x.length];
		sortWrapped(x, lo, hi, gc);
	}
	
	private static void sortWrapped(Comparable[] x, int lo, int hi, GeneralCompare gc) {
		int n = hi - lo; 
		if(n < 1)
			return;
		// Recursively sort each half of the array
		int mid = lo + (n/2);
		sortWrapped(x, lo, mid, gc);
		sortWrapped(x, mid+1, hi, gc);
		merge(x, lo, hi, gc);
	}

	private static void merge(Comparable[] x, int lo, int hi, GeneralCompare gc){

		int n = hi - lo;
		int mid = lo + (n/2);

		// Fill auxiliary array
		System.out.println("lo, mid, hi: " + lo + ", " + mid + ", " + hi);
		
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
		
		/*
		int i = 0;
		// Merging two sorted arrays
		while (i <= n) {
			if (gc.compare(aux[lo], aux[mid]) > 0)
				x[i++] = aux[mid++]; 
			else
				x[i++] = aux[lo++];
		}
		*/


		/*
		// Is this portion sorting the aux array? Check
		for(int k = 1; k <= n; k++){
			if(i > j-1)
				x[k] = aux[j++];
			else if(j > n)
				x[k] = aux[i++];
			else if(gc.compare(aux[j+1],aux[1]) < 0)// ?
				x[k] = aux[j++]; 
			else 
				x[k] = aux[i++];
		}*/

	}
}
