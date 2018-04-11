package data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner; // Testing ONLY
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.parser.ParseException;

import data.biotree.BioTree;
import search.trawl.BasicSearch;

/**
 * Class contains methods for reading and parsing files in the format of occurences.csv
 */
public class FileProcessor {
	/**
	 * The path to file the .csv file containing the records.
	 */
	private static String path = "src/occurrence.csv";
	/**
	 * A temporary list of records to add to as they are processed.
	 */
	private static ArrayList<Record> tempRecords = new ArrayList<Record>(1000); //temporary storage for records
	
	/**
	 * Gets the path string
	 * 
	 * @return Path String
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Sets a new path string to the .csv file.
	 */
	public static void setPath(String newPath) {
		path = newPath;
	}
	
	/**
	 * Initiates the processing of the file on path when called
	 *  Reads file and calls parse() for each line
	 *  
	 * @throws ParseException Could not parse the text
	 * @throws NumberFormatException Found an incorrectly formatted number in the text.
	 */
	public static void initProcessing() throws NumberFormatException, ParseException {
		FileReader fr;
		BufferedReader br;

		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			String currentLine;			
			br.readLine();	// Reads Past Field Names
			int i = 0;
			while ((currentLine = br.readLine()) != null) {
				i++;
				//System.out.println("Processed line " + i);
				parse(currentLine);
			}
			
			//initialize the storage of records
			DataStore.init(tempRecords.toArray(new Record[tempRecords.size()]));
			tempRecords = null; //free temporary memory storage of Record objects now that they're in the KDTree.
			
			br.close();
			fr.close();
		}
		catch (IOException e) {
			System.out.println("Fatal Error: Cannot find dataset at " + path + ".");
		}
	}
	
	/**
	 * Parses data from string
	 * Calls createObject on successful regex matching
	 * 
	 * @param currentLine a line/row of data
	 * @throws IOException Could not read file
	 * @throws ParseException Could not parse the text
	 * @throws NumberFormatException Found an incorrectly formatted number in the text.
	 */
	private static void parse(String currentLine) throws IOException, NumberFormatException, ParseException {
		/* Regex Pattern Grouping Guide
		 * 	Retrieve String Groups with: matchEventId.group(x):
		 *  group 0: full matched string
		 *  group 1: occurrenceId
		 *  group 2: individualCount
		 *  group 3: eventId
		 *  group 4: year
		 *  group 5: month
		 *  group 6: date
		 *  group 7: latitude
		 *  group 8: longitude
		 *  group 9: taxonId
		 *  group 10: Scientific Name
		 */
		Pattern patternEventId = Pattern.compile("([^,]+)?,([^,]+)?,[^,]+,[^,]+,[^,]+OP_ID (\\d+)?,(\\d+)?-(\\d+)?-(\\d+)?,(\\d+.\\d+)?,(-\\d+.\\d+)?,[^,\\d]+(\\d+)?,([^,]+)?");
		Matcher matchEventId = patternEventId.matcher(currentLine);

		if(matchEventId.find()) {
			createObjects(matchEventId);
		}
		else {
			System.out.println("Regex Matching Failed.");
		}		
	}
	
	/**
	 * Passes scientific name to BioTree and creates a record object from the returned taxonId. 
	 * 
	 * @param matchEventId The regex matcher
	 * @throws ParseException Could not parse the text
	 * @throws NumberFormatException Found an incorrectly formatted number in the text.
	 */
	public static void createObjects(Matcher matchEventId) throws NumberFormatException, ParseException {
		// If a scientific name exists
		if (!matchEventId.group(10).equals("NA")) {
			try{
				Integer taxonId = BioTree.processRecord(matchEventId.group(10));
				if (taxonId != null)
					tempRecords.add(createRecord(Integer.parseInt(matchEventId.group(3)), matchEventId.group(1), taxonId, Integer.parseInt(matchEventId.group(2)), Float.parseFloat(matchEventId.group(7)), Float.parseFloat(matchEventId.group(8)), Integer.parseInt(matchEventId.group(4)), Integer.parseInt(matchEventId.group(5)), Integer.parseInt(matchEventId.group(6))));
			} catch(IOException e) {
				System.out.println("Input Error:" + e);
			} catch(NullPointerException e) {
				System.out.println("Null Pointer Error:" + e);
			}
		}
	}
	
	/**
	 * Helper that creates and returns a Record Object
	 * 
	 * @param eventId The event id of the record.
	 * @param occurId The occurance id of the record
	 * @param taxonId The record's taxon id
	 * @param individualCount The individual count in that record
	 * @param latitude The latitude of the record
	 * @param longitude The longitude of the record
	 * @param year The year of the record
	 * @param month The month of the record
	 * @param day The day of the record
	 * @return A new Record object with these parameters.
	 */
	public static Record createRecord(int eventId, String occurId, int taxonId, int individualCount, float latitude, float longitude, int year, int month, int day) {
		return new Record(eventId, occurId, taxonId, individualCount, latitude, longitude, year, month, day);
	}
	
	/**
	 * The main method initiates file processing of occurrances.csv located in the src folder.
	 * 
	 * @param args
	 * @throws NumberFormatException An incorrectly formatted number was found in the file.
	 * @throws ParseException A part of the file could not be parsed.
	 */
	public static void main(String[] args) throws NumberFormatException, ParseException {
		initProcessing();
	}
}
