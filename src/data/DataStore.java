package data;

import java.util.ArrayList;

import sort.GeneralCompare;
import sort.KDT;

/**
 * Module for storing Record objects in a central place.
 * @author Christopher W. Schankula
 *
 */
public class DataStore {
	public static KDT<Record> records;
	
	public static void init(Record[] recs) {
		GeneralCompare<Record> ax0 = (r0,r1) -> {
			Record rec0 = (Record) r0;
			Record rec1 = (Record) r1;
			if (rec0.getYear() != rec1.getYear())
				return rec0.getYear() - rec1.getYear();
			else if (rec0.getMonth() != rec1.getMonth())
				return rec0.getMonth() - rec1.getMonth();
			else if (rec0.getDay() != rec1.getDay())
				return rec0.getDay() - rec1.getDay();
			else
				return 0;
		};
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
