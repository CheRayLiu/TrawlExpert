package search.trawl;

import java.util.ArrayList;
import java.util.Collection;
import utils.Stopwatch;
import data.DataStore;
import data.Date;
import data.Record;
import data.biotree.BioTree;
import sort.Bound;
import sort.GeneralRange;
import sort.RangeHelper;

/**
 * Provides functionality for range searching the Record database.
 * @author Christopher W. Schankula
 *
 */
public class BasicSearch {
	public static BasicSearchResult range(Integer taxonId, Integer yearLo, Integer yearHi){
		return range(taxonId, yearLo, yearHi, -90.0, 90.0, -180.0, 180.0);
	}
	
	/**
	 * Returns all records matching any of the children of the given TaxonID and in the 
	 * date range given
	 * @param taxonId The TaxonID for which to search
	 * @param yearLo The lower bound on the year range
	 * @param yearHi The upper bound on the year range
	 * @return
	 */
	public static BasicSearchResult range(Integer taxonId, Integer yearLo, Integer yearHi, Double latLo, Double latHi, Double longLo, Double longHi){
		GeneralRange<Record> dateRange = RangeHelper.date(Bound.ANY);
		
		if ((yearLo != null) && (yearHi != null)) {
			Date lower = new Date(yearLo,01,01);
			Date upper = new Date(yearHi+1,01,01);
			dateRange = RangeHelper.date(Bound.LOWHIGH, lower, upper);
		}
		
		GeneralRange<Record> latRange = RangeHelper.latitude(Bound.ANY);
		GeneralRange<Record> longRange = RangeHelper.longitude(Bound.ANY);
		
		if ((latLo != null) && (latHi != null)) {
			latRange = RangeHelper.latitude(Bound.LOWHIGH, latLo, latHi);
		}
		
		if ((longLo != null) && (longHi != null)) {
			longRange = RangeHelper.longitude(Bound.LOWHIGH, longLo, longHi);
		}
		
		GeneralRange<Record> taxonRange;
		
		Iterable<Integer> searches = BioTree.getNonEmptyChildren(taxonId);
		
		Stopwatch sw = new Stopwatch();
		ArrayList<Record> results = new ArrayList<Record>();
		for (Integer txId: searches) {
			taxonRange = RangeHelper.taxonID(Bound.EQUALS, txId);
			ArrayList<GeneralRange<Record>> axes = new ArrayList<GeneralRange<Record>>();
			
			axes.add(dateRange);axes.add(taxonRange);axes.add(latRange);axes.add(longRange);
			
			results.addAll((Collection<? extends Record>) DataStore.records.rangeSearch(axes));
		}
		double time = sw.elapsedTime();
		
		return new BasicSearchResult(taxonId, yearLo, yearHi, results, time);
	}
}
