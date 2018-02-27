package sort;

import java.lang.reflect.Array;

public class MergeSort{

	private static Comparable[] aux;
	
	public static void main(String[] args) {
		GeneralCompare b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		Integer[] test = {3, 4, 2, 1, 5, 7, 9, 10};
		sort(test, 0, Array.getLength(test) - 1, b1);
		
		for (int i = 0 ; i < (Array.getLength(test) - 1) ; i++) {
			System.out.println(test[i]);
		}
	}

	public static void sort(Comparable[] x, int lo, int hi, GeneralCompare gc) {
		aux = new Comparable[Array.getLength(x)];
		sortWrapped(x, 0, Array.getLength(x) - 1, gc);
	}
	
	private static void sortWrapped(Comparable[] x, int lo, int hi, GeneralCompare gc) {
		int n = hi - lo; 
		if(n <= 1)
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
		System.out.println(n);
		for(int k = lo; k <= hi; k++){
			aux[k] = x[k];
		}

		int i = 0;
		// Merging two sorted arrays
		while ((lo <= mid) & (mid <= hi)) {
			if (gc.compare(aux[lo], aux[mid]) > 0)
				x[i++] = aux[mid++]; 
			else
				x[i++] = aux[lo++];
		}

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
