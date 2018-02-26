package sort;

public class mergeSort implements GeneralCompare{

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
		
		for(int i = 1; i <= n; i++){
			if(i > mid)
				gc[i] = aux[mid+2];
			else if(mid+1 > n)
				gc[i] = aux[2];
			else if(compare(aux[mid+1],aux[1]) < 0)
				gc[i] = aux[i]; //fill statement (incorrect)
		}
		
	}


}
