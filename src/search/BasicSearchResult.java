package search;

import java.util.ArrayList;

import data.Record;
import java.lang.Math;

public class BasicSearchResult {
	private ArrayList<Record> results;
	private final double time;
	private BST<Integer, Integer> histogram;
	private Integer sum;
	
	public static void main(String[] args) {
		
		//Graph g = new Graph();
	}
	
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
	
	private double lngRange(double dist, double lat){
		return dist/(Math.cos(lat)*222);
	}
	
	private double latRange(double dist){
		return (dist/111)/2;
	}
	
}
