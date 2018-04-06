package search.trawl;

import java.util.ArrayList;
import java.util.HashMap;

import data.Record;
import graph.CC;
import graph.Cluster;
import graph.RecordCluster;
import search.BST;
import search.Graph;
import sort.Bound;
import sort.GeneralRange;
import sort.RangeHelper;

import java.lang.Math;

/**
 * Class for storing the results of a Basic Search, including the 
 * search results themselves, as well as some metadata such as the
 * search time in seconds. Also provides hooks to other functionality
 * such as histogram, sum and clustering.
 *
 */
public class BasicSearchResult {
	private final Integer taxonId;
	private final Integer yearLo;
	private final Integer yearHi;
	private ArrayList<Record> results;
	private final double time;
	private BST<Integer, Integer> histogram;
	private Integer sum;
	
	/**
	 * Constructor for BasicSearchResult
	 * @param taxonId The taxon id of the search.
	 * @param yearLo The lower year bound on the search.
	 * @param yearHi The upper year bound on the search.
	 * @param results A list of results from the search.
	 * @param time The time in seconds of the search query.
	 */
	public BasicSearchResult(Integer taxonId, Integer yearLo, Integer yearHi, ArrayList<Record> results, double time) {
		this.taxonId = taxonId;
		this.yearLo = yearLo;
		this.yearHi = yearHi;
		this.results = results;
		this.time = time;
	}
	
	public Integer taxonId() {
		return taxonId;
	}
	
	public Integer yearLo() {
		return yearLo;
	}	
	
	public Integer yearHi() {
		return yearHi;
	}

	public ArrayList<Record> results() {
		return results; 
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
	public ArrayList<RecordCluster> cluster(double area){
		return Cluster.cluster(area, this);
	}
}
