package graph;

import java.util.ArrayList;

import data.Record;

/**
 * A cluster of records clustered by the Cluster class.
 * @author TrawlStars, Inc.
 *
 */
public class RecordCluster {
	/**
	 * List of records in cluster.
	 */
	private ArrayList<Record> records;
	/**
	 * Average 2D position of records in cluster.
	 */
	private Point centroid;
	/**
	 * Total number of individual fish in the cluster.
	 */
	private int individualCount;
	
	/**
	 * Constructor for a cluster of fish data points
	 */
	public RecordCluster() {
		records = new ArrayList<Record>();
		centroid = new Point(0,0);
		individualCount = 0;
	}
	
	/**
	 * Add a record to the dataset.
	 * @param r Record for a species of fish
	 */
	public void addRecord(Record r) {
		records.add(r);
		individualCount+=r.getCount();
	}
	
	/**
	 * @return An iterable of records in the cluster
	 */
	public Iterable<Record> records(){
		return records;
	}
	
	/**
	 * @return Amount of records in the current cluster
	 */
	public int N() {
		return records.size();
	}
	
	/**
	 * @return Number of individuals in the records in the cluster
	 */
	public int getCount() {
		return individualCount;
	}
	
	/**
	 * Calculates the centroid of the records in the cluster.
	 * @return The average point (centroid) of the current cluster.
	 */
	public Point centroid() {
		double x = 0;
		double y = 0;
		
		for (Record r: records()) {
			x += r.getLongitude();
			y += r.getLatitude();
		}
		
		x /= N();
		y /= N();
			
		return new Point(x, y);
	}
}
