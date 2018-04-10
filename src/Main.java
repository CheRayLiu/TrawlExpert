import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;

import data.Record;
import data.biotree.BioTree;
import graph.RecordCluster;
import model.TrawlExpert;
import search.BST;
import search.trawl.BasicSearchResult;

public class Main {
	public static TrawlExpert te;
	
	public static void main(String[] args) {
		printLogo();
		te = new TrawlExpert();
		init();
	}

	private static void printLogo() {
		System.out.println("======== TRAWLEXPERT ALPHA v1 ========");
		System.out.println("                                   _...----.\r\n" + 
				"                       _,      _,-'_...--'`/\r\n" + 
				"                     ,^__\\__ ,'  ,'      /(\r\n" + 
				"                 _,.'  |  | `-._;        \\/\r\n" + 
				"              ,-'  |  /  /   \\  \\-.       \\\r\n" + 
				"            ,'   \\ \\  \\ <    /  <  \\     ,:\r\n" + 
				"           /     | <   `.\\   >  /   \\  ,' |    _,-'/\r\n" + 
				"          /      /  \\        \\  \\    \\/  _; ,-'   ;\r\n" + 
				"         /       `.  \\       /   \\   /`<__,' \\   /|\r\n" + 
				"        ; (O)      > |       >   <   \\ \\`^.  /   :|\r\n" + 
				"       /         :\\  |_.--. <    ,`  / /  (  >   :|\r\n" + 
				"       >)        ;/,='   `.\\ \\   |  / / __/  \\   ;|\r\n" + 
				"       \\       ,' |)     ,'/ /   |  ) |/-.`. /   \\|\r\n" + 
				"        `.   ,'   | `--=='`  >   |  `./`-.\\ `-._  :\r\n" + 
				"          `.      / /        \\   \\   ;`.__/     `-.\\\r\n" + 
				"            `-._  \\ |_       :   < _;    <   SSt\r\n" + 
				"                ``'.:``-._____\\_,-'-..___,\\\r\n" + 
				"                     \\`.|`._\\  `--..__     `._\r\n" + 
				"                      `-'             `````````");
		System.out.println("Loading.......");
	}
	
	public static void init() {
		System.out.println("Welcome!");
		
		while(true) {
			System.out.println("Main Menu");
			System.out.println("Available commands:");
			System.out.println("\ttree [taxonId / scientific name]");
			System.out.println("\trecords (taxonId / scientific name) [-t start end]");
			System.out.print("> ");
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
	
	/**
	 * 
	 * @param matcher
	 */
	private static void rangeSearch(Matcher matcher) {
		Integer start = null;
		Integer end = null;
		if (matcher.group(3) != null)
			if (matcher.group(3).equals("t")) {
				if (matcher.group(4) != null)
					start = Integer.parseInt(matcher.group(4));
				if (matcher.group(5) != null)
					end = Integer.parseInt(matcher.group(5));
			}
		
		Integer taxonId = null;
		try {
			taxonId = Integer.parseInt(matcher.group(2));
		} catch (NumberFormatException e) {
			if (taxonId == null) {
				try {
					taxonId = BioTree.nameToTaxonId(matcher.group(2));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		BasicSearchResult result = te.rangeSearch(taxonId, start, end);
		
		System.out.println("Found " + result.n() + " records in " + result.time() + " seconds.");
		
		while(true) {
			System.out.println("Available commands: list, histogram, sum, cluster (area), exit");
			System.out.print("> ");
			
			Scanner s = new Scanner(System.in);
			String command = s.nextLine();
			
			if (command.equals("list"))
				printRecords(result.results());
			else if (command.equals("histogram")) {
				printHistogram(result.histogram());
			} else if (command.equals("exit"))
				return;
			else if (command.equals("sum")) {
				System.out.println(result.sum());
			} else if (command.startsWith("cluster")) {
				String[] strSpl = command.split(" ");
				try {
					doCluster(result.cluster(Double.parseDouble(strSpl[1])));
				} catch (NumberFormatException e) {
					
				}
			} else
				System.out.println("Invalid command " + command);
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
	
	/**
	 * Prints a histogram based on a BST of records
	 * 
	 * @param record -An BST of records
	 */
	public static void printHistogram(BST<Integer,Integer> record) {
		int max = 0;
		int scale = 100;
		Iterable<Integer> results = record.keys();
		for (Integer year: results) {
			if (max < record.get(year)) max =record.get(year);

		}
		System.out.println("     |" + (new String(new char[scale]).replace('\0', '-')) + "|");
		String format = "%1$-5d|%2$-" + (scale + 1) + "s";
		for (Integer year: results) {
			String s = "=";
			int loopc = (int) ((float)(record.get(year)/ (float) max) * scale);
			for (int j=0; j< loopc; j++) {
				s+="=";
			}
			System.out.format(format, year, s);
			System.out.println("| " + record.get(year));
		}
		System.out.format("Scale: one = is %d individuals.\n", max / scale);
	}
	
	/**
	 * Prints a histogram based on a BST of records
	 * 
	 * @param record -An BST of records
	 */
	public static void doCluster(ArrayList<RecordCluster> clusters) {
		System.out.println("Found " + clusters.size() + " clusters.");
		String format = "|%1$-15s|%2$-15s|%3$-15s|%4$-15s|%5$-15s\n";
		System.out.format(format, "Cluster #", "Latitude", "Longitude", "Record Count", "Individual Count");
		for(int i = 0; i < clusters.size(); i++)
			System.out.format(format, (i+1), String.format("%.5f", clusters.get(i).centroid().getY()), String.format("%.5f", clusters.get(i).centroid().getX()), clusters.get(i).N(), clusters.get(i).getCount());
		
		while(true) {
			System.out.println("Available commands: list (cluster #), clusters, exit");
			
			Scanner s = new Scanner(System.in);
			String command = s.nextLine();
			
			
			//check command that the user typed
			if (command.startsWith("list")){
				String[] strSpl = command.split(" ");
				int clusterNum = 0;
				try {
					clusterNum = Integer.parseInt(strSpl[1]);
				} catch (Exception e) {
					System.out.println("Invalid command.");
					continue;
				}
				try {
					printRecords(clusters.get(clusterNum-1).records());
				} catch (Exception e) {
					System.out.println("Invalid cluster #");
				}
			} else if (command.equals("exit"))
				return;
			else if (command.equals("clusters")) {
				doCluster(clusters);
				return;
			} else 
				System.out.println("Invalid command.");
		}
	}
}
