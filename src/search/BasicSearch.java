package search;

import java.util.ArrayList;
import java.util.Scanner;

import data.DataStore;
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
			
			GeneralRange<Record> a0 = r -> 0;
			GeneralRange<Record> a1 = r -> r.getTaxonId() - taxonId;//RangeHelper.taxonID(Bound.EQUALS, taxonId);
			GeneralRange<Record> a2 = r -> 0;
			GeneralRange<Record> a3 = r -> 0;
			
			ArrayList<GeneralRange<Record>> axes = new ArrayList<GeneralRange<Record>>();
			
			axes.add(a0);axes.add(a1);axes.add(a2);axes.add(a3);
			
			Iterable<Record> results = DataStore.records.rangeSearch(axes);
			
			for (Record r: results) {
				System.out.println(r.getTaxonId());
			}
		}
	}
}
