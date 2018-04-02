package search;

import java.util.ArrayList;
import java.util.HashMap;

import data.Record;
import sort.Bound;
import sort.GeneralRange;
import sort.RangeHelper;

import java.lang.Math;

public class BasicSearchResult {
	private final Integer taxonId;
	private final Integer yearLo;
	private final Integer yearHi;
	private ArrayList<Record> results;
	private final double time;
	private BST<Integer, Integer> histogram;
	private Integer sum;
	
	public BasicSearchResult(Integer taxonId, Integer yearLo, Integer yearHi, ArrayList<Record> results, double time) {
		this.taxonId = taxonId;
		this.yearLo = yearLo;
		this.yearHi = yearHi;
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
	
	private ArrayList<GeneralRange<Record>> ranges(double lat, double lon, double area){
		double dist = Math.sqrt(area);
		
		ArrayList<GeneralRange<Record>> range = new ArrayList<GeneralRange<Record>>();
		
		GeneralRange<Record> latRange = RangeHelper.latitude(Bound.LOWHIGH, lat - latRange(dist), lat + latRange(dist));
		GeneralRange<Record> lonRange = RangeHelper.longitude(Bound.LOWHIGH, lon - lngRange(dist,lat), lon + lngRange(dist,lat));
		
		return range;
	}

	private double lngRange(double dist, double lat){
		return dist/(Math.cos(lat)*222);
	}
	
	private double latRange(double dist){
		return (dist/111)/2;
	}
}
