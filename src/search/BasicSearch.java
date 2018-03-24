package search;

import java.util.ArrayList;
import java.util.Collection;
import utils.Stopwatch;
import data.BioTree;
import data.DataStore;
import data.Date;
import data.Record;
import sort.Bound;
import sort.GeneralRange;
import sort.RangeHelper;

/**
 * Provides functionality for range searching the Record database.
 * @author Christopher W. Schankula
 *
 */
public class BasicSearch {
	/**
	 * Returns all records matching any of the children of the given TaxonID and in the 
	 * date range given
	 * @param taxonId The TaxonID for which to search
	 * @param yearLo The lower bound on the year range
	 * @param yearHi The upper bound on the year range
	 * @return
	 */
	public static BasicSearchResult range(Integer taxonId, Integer yearLo, Integer yearHi){
		GeneralRange<Record> a0 = RangeHelper.date(Bound.ANY);
		
		if ((yearLo != null) && (yearHi != null)) {
			Date lower = new Date(yearLo,01,01);
			Date upper = new Date(yearHi+1,01,01);
			a0 = RangeHelper.date(Bound.LOWHIGH, lower, upper);
		}
		
		GeneralRange<Record> a2 = r -> 0;
		GeneralRange<Record> a3 = r -> 0;
		
		GeneralRange<Record> a1;
		
		Iterable<Integer> searches = BioTree.getNonEmptyChildren(taxonId);
		
		Stopwatch sw = new Stopwatch();
		ArrayList<Record> results = new ArrayList<Record>();
		for (Integer txId: searches) {
			a1 = RangeHelper.taxonID(Bound.EQUALS, txId);
			ArrayList<GeneralRange<Record>> axes = new ArrayList<GeneralRange<Record>>();
			
			axes.add(a0);axes.add(a1);axes.add(a2);axes.add(a3);
			
			results.addAll((Collection<? extends Record>) DataStore.records.rangeSearch(axes));
		}
		double time = sw.elapsedTime();
		
		return new BasicSearchResult(results, time);
	}
}
