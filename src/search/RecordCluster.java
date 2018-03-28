package search;

import java.util.ArrayList;

import data.Record;
import sandbox.Point;

public class RecordCluster {
	private ArrayList<Record> records;
	private Point centroid;
	
	public void addRecord(Record r) {
		records.add(r);
	}
	
	public Iterable<Record> records(){
		return records;
	}
	
	public int N() {
		return records.size();
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
