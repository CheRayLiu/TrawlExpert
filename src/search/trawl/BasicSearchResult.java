package search.trawl;

import java.util.ArrayList;
import data.Record;
import graph.Cluster;
import graph.RecordCluster;
import search.RedBlackTree;

/**
 * Class for storing the results of a Basic Search, including the 
 * search results themselves, as well as some metadata such as the
 * search time in seconds. Also provides hooks to other functionality
 * such as histogram, sum and clustering.
 *
 */
public class BasicSearchResult {
	/**
	 * The taxonId of the search.
	 */
	private final Integer taxonId;
	/**
	 * The starting year of the search.
	 */
	private final Integer yearLo;
	/**
	 * The ending year of the search.
	 */
	private final Integer yearHi;
	/**
	 * The records found in the search.
	 */
	private ArrayList<Record> results;
	/**
	 * The time that the search took.
	 */
	private final double time;
	/**
	 * The histogram of the search. Caches after first time the histogram() method is called.
	 */
	private RedBlackTree<Integer, Integer> histogram;
	/**
	 * The total number of fish in the search. Caches after the first time the sum() method is called.
	 */
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
	
	/**
	 * @return The taxon id of the search
	 */
	public Integer taxonId() {
		return taxonId;
	}
	
	/**
	 * @return The starting year of the search
	 */	
	public Integer yearLo() {
		return yearLo;
	}	
	
	/**
	 * @return The ending year of the search
	 */
	public Integer yearHi() {
		return yearHi;
	}

	/**
	 * @return The results of the search
	 */
	public ArrayList<Record> results() {
		return results; 
	}
	
	/**
	 * @return The number of records returned by the search.
	 */
	public int n() {
		return results.size();
	}
	
	/**
	 * @return The time the search took.
	 */
	public double time() {
		return time;
	}
	
	/**
	 * Generates a histogram of the search results.
	 * @return A red-black tree where the key is a year and the value is the corresponding number of individuals.
	 */
	public RedBlackTree<Integer, Integer> histogram() {
		if (this.histogram == null)
			this.histogram = Histogram.histogram(results());
		return histogram;
	}
	
	/**
	 * The total number of individuals in this search.
	 * @return
	 */
	public int sum() {
		if (sum == null) {
			sum = 0;
			for (Record r: results())
				sum += r.getCount();
		}
		return this.sum;
	}
	
	/**
	 * Cluster the current search into clusters based on area of similarity.
	 * @param area The area of similarity. Two records are considered to be in the same cluster
	 * if the square area around the records overlaps.
	 */
	public ArrayList<RecordCluster> cluster(double area){
		return Cluster.cluster(area, this);
	}
}
