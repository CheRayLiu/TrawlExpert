package search;

import java.util.ArrayList;
import java.util.Scanner;

import utils.Stopwatch;
import data.DataStore;
import data.Date;
import data.Record;
import sort.Bound;
import sort.GeneralRange;
import sort.RangeHelper;

public class BasicSearch {
	public static void init() {
		System.out.println("======== TRAWLEXPERT ALPHA v1 ========");
		while(true) {
			System.out.print("Search by taxonId: ");
			Scanner s = new Scanner(System.in);
			int taxonId = s.nextInt();
			
			Date lower = new Date(1990,01,01);
			Date upper = new Date(2001,01,01);
			
			GeneralRange<Record> a0 = RangeHelper.date(Bound.LOWHIGH, lower, upper);
			GeneralRange<Record> a1 = RangeHelper.taxonID(Bound.EQUALS, taxonId);
			GeneralRange<Record> a2 = r -> 0;
			GeneralRange<Record> a3 = r -> 0;
			
			ArrayList<GeneralRange<Record>> axes = new ArrayList<GeneralRange<Record>>();
			
			axes.add(a0);axes.add(a1);axes.add(a2);axes.add(a3);
			
			Stopwatch sw = new Stopwatch();
			Iterable<Record> results = DataStore.records.rangeSearch(axes);
			double elapsed = sw.elapsedTime();
			String format = "|%1$-45s|%2$-15s|%3$-15s|%4$-15s|%5$-15s|%6$-15s\n";
			System.out.format(format, "Scientific Name", "IndividualCount", "Latitude", "Longitude","Year","Month","Day");
			for (Record r: results) {
				System.out.println(r);
			}
			
			System.out.println("Found " + ((ArrayList) results).size() + " records in " + elapsed + " seconds.");
		}
	}
}
