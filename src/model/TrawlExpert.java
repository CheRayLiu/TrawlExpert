package model;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import data.BioTree;
import data.DataStore;
import data.FileProcessor;
import data.Record;
import data.TaxonNode;
import search.kdt.KDT;
import search.trawl.BasicSearch;
import search.trawl.BasicSearchResult;

public class TrawlExpert {
	public TrawlExpert() {
		//load data
		try {
			BioTree.init("data/biotree/");
			DataStore.records = new KDT<Record>("data/kdt.ser");
		} catch (Exception e0) {
			try {
				BioTree.init();
				FileProcessor.initProcessing();
			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BioTree.write("data/biotree/");
			DataStore.records.writeToFile("data/kdt.ser");
		};
	}
	
	public Iterable<Integer> listAllSpecies(){
		return BioTree.getNonEmptyChildren(2);
	}
	
	public BasicSearchResult rangeSearch(Integer taxonId, Integer yearLo, Integer yearHi) {
		return BasicSearch.range(taxonId, yearLo, yearHi);
	}
	
	public TaxonNode getTaxonRecord(Integer taxonId) {
		return BioTree.getTaxonRecord(taxonId);
	}
	
	public TaxonNode getTaxonRecord(String scientificName) {
		try {
			return BioTree.getTaxonRecord(scientificName);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Iterable<Integer> getNonEmptyChildren(int taxonId){
		return BioTree.getNonEmptyChildren(taxonId);
	}
	
	public Iterable<Integer> getAllChildren(int taxonId){
		return BioTree.getAllChildren(taxonId);
	}
	
}
