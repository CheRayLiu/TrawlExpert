package graph;

import java.util.ArrayList;
import java.util.HashMap;

import data.Record;
import search.trawl.BasicSearch;
import search.trawl.BasicSearchResult;
import sort.Bound;
import sort.GeneralRange;
import sort.RangeHelper;

/**
 * Class for clustering TrawlExpert Records according to an area of similarity.
 * @author TrawlStars, Inc.
 *
 */
public class Cluster {
	
	public static void main(String[] args) {
		System.out.println(lngRange(5.48,43.38960));
		System.out.println(latRange(5.48));
	}
	
	/**
	 * Cluster the results of a basic search into groupings based on an area.
	 * @param area Kilometer-squared area of similarity. Records within a square area 
	 * @return List of Record clusters.
	 */
	public static ArrayList<RecordCluster> cluster(double area, BasicSearchResult basicSearch){
		Integer taxonId = basicSearch.taxonId();
		Integer yearLo = basicSearch.yearLo();
		Integer yearHi = basicSearch.yearHi();
		ArrayList<Record> results = basicSearch.results();
		
		//mapping from string occurrence id to integer node
		HashMap<String, Integer> nodeMap = new HashMap<String, Integer>();
		//mapping from string occurrence id to whether that node has been marked
		HashMap<String, Boolean> marked = new HashMap<String, Boolean>();
		
		int i = 0;
		for(Record r: results) {
			nodeMap.put(r.getOccurId(), i);
			i++;
		}
		
		Graph G = new Graph(results.size());
		
		double dist = Math.sqrt(area);
	
		//iterate through all unmarked nodes and attach 
		for (Record r: results) {
			if (marked.get(r.getOccurId()) != null) continue;
			double lat = r.getLatitude();
			double lon = r.getLongitude();
			//perform a range search around this current node
			BasicSearchResult res = BasicSearch.range(taxonId, yearLo, yearHi, lat - latRange(dist), lat + latRange(dist), lon - lngRange(dist,lat), lon + lngRange(dist,lat));
			//for all nodes around the current node, add an edge between the nodes
			for (Record r1: res.results()) {
				marked.put(r1.getOccurId(), true);
				G.addEdge(nodeMap.get(r.getOccurId()), nodeMap.get(r1.getOccurId()));
			}
		}
		
		CC cc = new CC(G);
		
		//add clusters for each component
		ArrayList<RecordCluster> clusters = new ArrayList<RecordCluster>();
		for(int j = 0; j < cc.count(); j++) {
			clusters.add(new RecordCluster());
		}
		
		//add all records to its proper cluster
		for (int j = 0; j < G.V(); j++) {
			Integer component = cc.id(j);
			clusters.get(component).addRecord(results.get(j));
		}
		
		return clusters;
	}
	
	/**
	 * Generate range functions for the search around a given node.
	 * @param lat The latitude about which to search.
	 * @param lon The longitude about which to search.
	 * @param area The area of searching.
	 * @return The GeneralRange functions for performing the range search.
	 */
	private static ArrayList<GeneralRange<Record>> ranges(double lat, double lon, double area){
		double dist = Math.sqrt(area);
		
		ArrayList<GeneralRange<Record>> range = new ArrayList<GeneralRange<Record>>();
		
		GeneralRange<Record> latRange = RangeHelper.latitude(Bound.LOWHIGH, lat - latRange(dist), lat + latRange(dist));
		GeneralRange<Record> lonRange = RangeHelper.longitude(Bound.LOWHIGH, lon - lngRange(dist,lat), lon + lngRange(dist,lat));
		
		return range;
	}

	/**
	 * Convert a distance to a range in terms of longitude (depends on latitude).
	 * @param dist The distance
	 * @param lat The current latitude.
	 * @return The amount of longitude degrees in half that distance.
	 */
	private static double lngRange(double dist, double lat){
		return dist/(Math.cos(Math.toRadians(lat))*222);
	}
	
	/**
	 * Convert a distance to a range in terms of latitude (not dependant on longitude).
	 * @param dist The distance
	 * @return The amount of latitude degrees in half that distance.
	 */
	private static double latRange(double dist){
		return (dist/222);
	}
}
