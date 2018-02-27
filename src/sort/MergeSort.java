package sort;

public class MergeSort{
	
	private static Comparable[] aux;
	
	public static void merge(Comparable[] x, int lo, int hi, GeneralCompare gc){
		
		int n = hi - lo; 
		aux = new Comparable[n];

		if(n <= 1)
			return;
		// Recursively merge each half of the array
		int mid = (n/2) + 1;
		merge(x, lo, mid, gc);
		merge(x, mid+1, hi, gc);
		
		// Fill auxiliary array
		for(int k = 1; k <= n; k++){
			aux[k] = x[k];
		}
		
		//
		while ((mid < hi) | (lo < mid)) {
			if (compare(aux[lo], aux[mid]))
				x[lo++] = mid; 
				
		}
		
		
		
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
		}
		
	}


}
