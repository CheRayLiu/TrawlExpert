package search;

import java.util.ArrayList;

import data.Record;
import sandbox.Point;

public class RecordCluster {
	private ArrayList<Record> records;
	private Point centroid;
	private int individualCount;
	
	public RecordCluster() {
		records = new ArrayList<Record>();
		centroid = new Point(0,0);
		individualCount = 0;
	}
	
	public void addRecord(Record r) {
		records.add(r);
		individualCount+=r.getCount();
	}
	
	public Iterable<Record> records(){
		return records;
	}
	
	public int N() {
		return records.size();
	}
	
	public int getCount() {
		return individualCount;
	}
	
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
