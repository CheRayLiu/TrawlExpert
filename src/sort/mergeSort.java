package sort;

public class mergeSort {

	private static GeneralCompare[] aux;
	
	public static void merge(GeneralCompare[] gc, int n){
		
		aux = new GeneralCompare[n];
		
		if(n <= 1)
			return;
		int mid = n/2;
		merge(gc, mid);
		merge(gc, mid+1);
		
		for(int i = 1; i <= n; i++){
			aux[i] = gc[i];
		}
		
		
	}
	
	
}
