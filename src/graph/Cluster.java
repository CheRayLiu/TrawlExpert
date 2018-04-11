package graph;

import java.util.ArrayList;
import java.util.HashMap;

import data.Record;
import search.trawl.BasicSearch;
import search.trawl.BasicSearchResult;
import sort.Bound;
import sort.GeneralRange;
import sort.RangeHelper;

public class Cluster {
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
		
		HashMap<String, Integer> nodeMap = new HashMap<String, Integer>();
		HashMap<String, Boolean> marked = new HashMap<String, Boolean>();
		
		int i = 0;
		for(Record r: results) {
			nodeMap.put(r.getOccurId(), i);
			i++;
		}
		
		Graph G = new Graph(results.size());
		
		double dist = Math.sqrt(area);
	
		
		for (Record r: results) {
			if (marked.get(r.getOccurId()) != null) continue;
			double lat = r.getLatitude();
			double lon = r.getLongitude();
			BasicSearchResult res = BasicSearch.range(taxonId, yearLo, yearHi, lat - latRange(dist), lat + latRange(dist), lon - lngRange(dist,lat), lon + lngRange(dist,lat));
			for (Record r1: res.results()) {
				marked.put(r1.getOccurId(), true);
				G.addEdge(nodeMap.get(r.getOccurId()), nodeMap.get(r1.getOccurId()));
			}
		}
		
		System.out.println("Edges found");
		
		CC cc = new CC(G);
		
		ArrayList<RecordCluster> clusters = new ArrayList<RecordCluster>();
		for(int j = 0; j < cc.count(); j++) {
			clusters.add(new RecordCluster());
		}
		
		for (int j = 0; j < G.V(); j++) {
			Integer component = cc.id(j);
			clusters.get(component).addRecord(results.get(j));
		}
		
		return clusters;
	}
	
	private static ArrayList<GeneralRange<Record>> ranges(double lat, double lon, double area){
		double dist = Math.sqrt(area);
		
		ArrayList<GeneralRange<Record>> range = new ArrayList<GeneralRange<Record>>();
		
		GeneralRange<Record> latRange = RangeHelper.latitude(Bound.LOWHIGH, lat - latRange(dist), lat + latRange(dist));
		GeneralRange<Record> lonRange = RangeHelper.longitude(Bound.LOWHIGH, lon - lngRange(dist,lat), lon + lngRange(dist,lat));
		
		return range;
	}

	private static double lngRange(double dist, double lat){
		return dist/(Math.cos(Math.toRadians(lat))*222);
	}
	
	private static double latRange(double dist){
		return (dist/222);
	}
}
