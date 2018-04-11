package model;

import org.json.simple.parser.ParseException;

import data.DataStore;
import data.FileProcessor;
import data.Record;
import data.biotree.BioTree;
import data.biotree.TaxonNode;
import search.kdt.KDT;
import search.trawl.BasicSearch;
import search.trawl.BasicSearchResult;

/**
 * The model of the TrawlExpert codebase. Gives hooks to views such as the server
 * and the command-line interface. Attempts to load serialized data from the disc,
 * but if that fails it will regenerate the BioTree and Record kd-tree from the
 * original dataset, then save the serialized data when it's done.
 * @author Christopher W. Schankula
 *
 */
public class TrawlExpert {
	/**
	 * Initialize the TrawlExpert instance. Either loads from dataset or from serialized data on disc.
	 */
	public TrawlExpert() {
		//load data
		try {
			//try loading from disc, otherwise load from dataset
			BioTree.init("data/biotree/");
			DataStore.records = new KDT<Record>("data/records.kdtree");
		} catch (Exception e0) {
			try {
				BioTree.init();
				FileProcessor.initProcessing();
			} catch (NumberFormatException | ParseException e) {
				
			}
			BioTree.write("data/biotree/");
			DataStore.records.writeToFile("data/records.kdtree");
		};
	}
	
	/**
	 * A wrapped for listing all taxon records in the dataset.
	 * @return
	 */
	public Iterable<Integer> listAllSpecies(){
		return BioTree.getNonEmptyChildren(2);
	}
	
	/**
	 * A wrapper for performing a basic search of the data.
	 * @param taxonId The taxonId of the records to find (and all of that TaxonID's descendants)
	 * @param yearLo The lower-bound on the year to search for.
	 * @param yearHi The upper-bound on the year to search for.
	 * @return A BasicSearchResult object of the records found in the database.
	 */
	public BasicSearchResult rangeSearch(Integer taxonId, Integer yearLo, Integer yearHi) {
		return BasicSearch.range(taxonId, yearLo, yearHi);
	}
	
	/**
	 * Wrapper for getting a taxon node from the BioTree.
	 * @param taxonId The taxonId to search for.
	 * @return An object representing information about the taxonId requested.
	 */
	public TaxonNode getTaxonRecord(Integer taxonId) {
		return BioTree.getTaxonRecord(taxonId);
	}
	
	/**
	 * Wrapper for getting a taxon node from the BioTree.
	 * @param taxonId The scientific name to search for.
	 * @return An object representing information about the scientific name requested.
	 */
	public TaxonNode getTaxonRecord(String scientificName) {
		return BioTree.getTaxonRecord(scientificName);
	}
	
	/**
	 * Helper function for returning all of the non-empty children of a given taxon Id.
	 * @param taxonId The taxonId to search for.
	 * @return An iterable of all the taxonIds which are descendants of the given taxonId and
	 * have at least one record in the dataset.
	 */
	public Iterable<Integer> getNonEmptyChildren(int taxonId){
		return BioTree.getNonEmptyChildren(taxonId);
	}
	
	/**
	 * Helper function for returning all of the children of a given taxon Id.
	 * @param taxonId The taxonId to search for.
	 * @return An iterable of all the taxonIds which are descendants of the given taxonId.
	 */
	public Iterable<Integer> getAllChildren(int taxonId){
		return BioTree.getAllChildren(taxonId);
	}
	
}
