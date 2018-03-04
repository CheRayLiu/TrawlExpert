package sort;

public class MergeSort{
	
	public static void main(String[] args) {
		GeneralCompare<Integer> b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		Integer[] test = {3, 4, 2, 1, 5, 7, 9, 10, 11};
		//Integer[] test = {2, 1};
		sort(test, 0, test.length - 1, b1);
		
		for (int i = 0 ; i < (test.length) ; i++) {
			System.out.println(test[i]);
		}
	}

	public static <T> void sort(Comparable<T>[] x, int lo, int hi, GeneralCompare<T> gc) {
		Comparable<T>[] aux;
		aux = (Comparable<T>[]) new Comparable[x.length];
		sortWrapped(x, lo, hi, gc, aux);
	}
	
	private static <T> void sortWrapped(Comparable<T>[] x, int lo, int hi, GeneralCompare<T> gc, Comparable<T>[] aux) {
		int n = hi - lo; 
		if(n < 1)
			return;
		// Recursively sort each half of the array
		int mid = lo + (n/2);
		sortWrapped(x, lo, mid, gc, aux);
		sortWrapped(x, mid+1, hi, gc, aux);
		merge(x, lo, hi, gc, aux);
	}

	private static <T> void merge(Comparable<T>[] x, int lo, int hi, GeneralCompare<T> gc, Comparable<T>[] aux){

		int n = hi - lo;
		int mid = lo + (n/2);

		// Fill auxiliary array
		//System.out.println("lo, mid, hi: " + lo + ", " + mid + ", " + hi);
		
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
