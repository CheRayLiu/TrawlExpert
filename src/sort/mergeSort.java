package sort;

public class mergeSort{
	
	private static Comparable[] aux;
	
	public static void merge(Comparable x, int n, GeneralCompare gc){
		
		aux = new Comparable[n];
		
		if(n <= 1)
			return;
		int j = (n/2) + 1, i = 0;
		merge(gc, j);
		merge(gc, j+1);
		
		for(int k = 1; k <= n; k++){
			aux[i] = gc[i];
		}
		
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
