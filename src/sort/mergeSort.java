package sort;

public class mergeSort implements GeneralCompare{

	private static GeneralCompare[] aux;
	
	public static void merge(GeneralCompare[] gc, int n){
		
		aux = new GeneralCompare[n];
		
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
				gc[k] = aux[j++];
			else if(j > n)
				gc[k] = aux[i++];
			else if(compare(aux[j+1],aux[1]) < 0)// ?
				gc[k] = aux[j++]; 
			else 
				gc[k] = aux[i++];
		}
		
	}


}
