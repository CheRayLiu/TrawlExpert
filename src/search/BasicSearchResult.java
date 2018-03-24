package search;

import java.util.ArrayList;

import data.Record;

public class BasicSearchResult {
	private ArrayList<Record> results;
	private final double time;
	private BST<Integer, Integer> histogram;
	private Integer sum;
	
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
}
