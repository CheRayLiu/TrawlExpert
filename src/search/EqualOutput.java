package search;


public class EqualOutput {

	public static void graph(BST<Integer,Integer> record) {
		int max = 0;
		Iterable<Integer> results = record.keys();
		for (Integer year: results) {
			if (max < record.get(year)) max =record.get(year);

		}
		
		
		max = max /10;
		System.out.println("___________________________________________________________________");
		String format = "%1$-5d|%2$-40s\n";
		for (Integer year: results) {
			String s = "=";
			int loopc = record.get(year)/max;
			for (int j=0; j< loopc; j++) {
				s+="=";
			}
			System.out.format(format,year, s);

		}
	}
		
	
	public static void main(String[] args) {
		
	}
		
}
