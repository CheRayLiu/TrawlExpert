package search;

import java.util.ArrayList;

import data.Record;
import sort.GeneralRange;

public class BasicSearchResult {
	private ArrayList<Record> results;
	private final double time;
	private BST<Integer, Integer> histogram;
	private Integer sum;
	private 
	
	public BasicSearchResult(ArrayList<Record> results, double time) {
		this.results = results;
		this.time = time;
	}

	public Iterable<Record> results() {
		return (Iterable<Record>) results;
	}
	
	public int n() {
		return results.size();
	}
	
	public double time() {
		return time;
	}
	
	public BST<Integer, Integer> histogram() {
		if (this.histogram == null)
			this.histogram = Histogram.histogram(results());
		return histogram;
	}
	
	public int sum() {
		if (sum == null) {
			sum = 0;
			for (Record r: results())
				sum += r.getCount();
		}
		return this.sum;
	}
	
	/**
	 * 
	 * @param area
	 */
	public Iterable<RecordCluster> cluster(double area){
		
	}
	
	private ArrayList<GeneralRange> ranges(double lat, double lon, double area){
		ArrayList<GeneralRange>()
	}
}
