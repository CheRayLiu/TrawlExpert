package data;

import java.util.ArrayList;

import search.kdt.KDT;
import sort.GeneralCompare;
import utils.Stopwatch;

/**
 * Singleton module for storing Record objects in a central place.
 * @author Christopher W. Schankula
 *
 */
public class DataStore {
	/**
	 * The records in the current instance of the program.
	 */
	public static KDT<Record> records;
	
	/**
	 * Initialize the DataStore with the given array of records.
	 * @param recs An array of records to be inserted into the kd-tree-based database
	 * of record objects for future querying.
	 */
	public static void init(Record[] recs) {
		GeneralCompare<Record> ax0 = (r0,r1) -> ((Record) r0).getDate().compareTo(((Record) r1).getDate());
		GeneralCompare<Record> ax1 = (r0,r1) -> ((Record) r0).getTaxonId() - ((Record) r1).getTaxonId();
		GeneralCompare<Record> ax2 = (r0,r1) -> Float.compare(((Record) r0).getLatitude(), ((Record) r1).getLatitude());
		GeneralCompare<Record> ax3 = (r0,r1) -> Float.compare(((Record) r0).getLongitude(), ((Record) r1).getLongitude());
		ArrayList<GeneralCompare<Record>> axes = new ArrayList<GeneralCompare<Record>>(4);
		axes.add(ax0);axes.add(ax1);axes.add(ax2);axes.add(ax3);
		
		records = new KDT<Record>(axes, recs);
		
		System.out.println("K:" + records.getK());
		System.out.println("H:" + records.height());
		System.out.println("S:" + records.size());
	}
}
