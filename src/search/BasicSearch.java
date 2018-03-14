package search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;

import utils.Stopwatch;
import data.BioTree;
import data.DataStore;
import data.Date;
import data.Record;
import data.WormsAPI;
import sort.Bound;
import sort.GeneralRange;
import sort.RangeHelper;

public class BasicSearch {
	public static void init() {
		System.out.println("Welcome!");
		while(true) {
			System.out.println("Main Menu");
			System.out.println("Available commands:");
			System.out.println("\ttree [taxonId / scientific name]");
			System.out.println("\trecords (taxonId / scientific name) [-t start end]\n");
			Pattern pat = Pattern.compile("([a-zA-Z]+)[ ]?([0-9a-zA-Z ]+[0-9a-zA-Z])?[ ]?[-]?([a-zA-Z])?[ ]?([A-Za-z0-9]+)?[ ]?([A-Za-z0-9]+)?[ ]?([A-Za-z0-9]+)?[ ]?([A-Za-z0-9]+)?[ ]?([A-Za-z0-9]+)?[ ]?");
			Scanner s = new Scanner(System.in);
			String line = s.nextLine();
			Matcher matcher = pat.matcher(line);
			if (!matcher.find()) continue;
			
			//tree
			//tree taxonId
			//tree scientific name
			//records taxonId
			//records scientific name
			String command = matcher.group(1);
			
			if (command.equals("records"))
				rangeSearch(matcher);
			else if (command.equals("tree"))
				printTree(matcher);
		}
	}
	
	private static void rangeSearch(Matcher matcher) {
		Integer start = null;
		Integer end = null;
		GeneralRange<Record> a0 = RangeHelper.date(Bound.ANY);
		if (matcher.group(2) == "-t") {
			if (matcher.group(3) != null)
				start = Integer.parseInt(matcher.group(3));
			if (matcher.group(4) != null)
				end = Integer.parseInt(matcher.group(4));
			Date lower = new Date(start,01,01);
			Date upper = new Date(end+1,01,01);
			
			a0 = RangeHelper.date(Bound.LOWHIGH, lower, upper);
		}
		
		Integer taxonId = null;
		try {
			taxonId = Integer.parseInt(matcher.group(2));
		} catch (NumberFormatException e) {
			if (taxonId == null) {
				try {
					taxonId = WormsAPI.nameToRecordID(matcher.group(2));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		GeneralRange<Record> a1 = RangeHelper.taxonID(Bound.EQUALS, taxonId);
		GeneralRange<Record> a2 = r -> 0;
		GeneralRange<Record> a3 = r -> 0;
		
		ArrayList<GeneralRange<Record>> axes = new ArrayList<GeneralRange<Record>>();
		
		axes.add(a0);axes.add(a1);axes.add(a2);axes.add(a3);
		
		Stopwatch sw = new Stopwatch();
		Iterable<Record> results = DataStore.records.rangeSearch(axes);
		double elapsed = sw.elapsedTime();
		
		System.out.println("Found " + ((ArrayList) results).size() + " records in " + elapsed + " seconds.");
		
		while(true) {
			System.out.println("Available commands: list, histogram, sum, exit");
			
			Scanner s = new Scanner(System.in);
			String command = s.nextLine();
			
			if (command.equals("list"))
				printRecords(results);
			else if (command.equals("histogram")) {
				Histogram.printHistogram(Histogram.histogram(results));
			} else if (command.equals("exit"))
				return;
			else if (command.equals("sum")) {
				int sum = Histogram.sum(results);
				System.out.println(sum);
			}
		}
	}
	
	private static void printRecords(Iterable<Record> results) {
		String format = "|%1$-45s|%2$-15s|%3$-15s|%4$-15s|%5$-15s|%6$-15s\n";
		System.out.format(format, "Scientific Name", "IndividualCount", "Latitude", "Longitude","Year","Month","Day");
		for (Record r: results) {
			System.out.println(r);
		}
	}
	
	private static void printTree(Matcher matcher) {
		Integer taxonId;
		String name;
		if (matcher.group(2) == null)
			BioTree.printTree();
		else {
			name = matcher.group(2);
			try {
				taxonId = Integer.parseInt(name);
				BioTree.printTree(taxonId);
			} catch (Exception e) {
				BioTree.printTree(name);
			}
		}
		System.out.println();
	}
}
