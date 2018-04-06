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

import search.BasicSearch;

/**
 * Class contains methods for reading and parsing files in the format of occurences.csv
 */
public class FileProcessor {
	private static String path = "src/occurrence.csv";
	private static ArrayList<Record> al = new ArrayList<Record>(1000); //temporary storage for records
	
	/**
	 * Gets the path string
	 * 
	 * @return Path String
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Sets a new path string
	 */
	public void setPath(String newPath) {
		path = newPath;
	}
	
	/**
	 * Initiates the processing of the file on path when called
	 *  Reads file and calls parse() for each line
	 *  
	 * @throws ParseException 
	 * @throws NumberFormatException 
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
			DataStore.init(al.toArray(new Record[al.size()]));
			al = null; //free temporary memory storage of Record objects now that they're in the KDTree.
			
			br.close();
			fr.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses data from string
	 * Calls createObject on successful regex matching
	 * 
	 * @param currentLine, a line/row of data
	 * @throws IOException
	 * @throws ParseException 
	 * @throws NumberFormatException 
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
	 * @param matchEventId
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public static void createObjects(Matcher matchEventId) throws NumberFormatException, ParseException {
		// If a scientific name exists
		if (!matchEventId.group(10).equals("NA")) {
			try{
				Integer taxonId = BioTree.processRecord(matchEventId.group(10));
				if (taxonId != null)
					al.add(createRecord(Integer.parseInt(matchEventId.group(3)), matchEventId.group(1), taxonId, Integer.parseInt(matchEventId.group(2)), Float.parseFloat(matchEventId.group(7)), Float.parseFloat(matchEventId.group(8)), Integer.parseInt(matchEventId.group(4)), Integer.parseInt(matchEventId.group(5)), Integer.parseInt(matchEventId.group(6))));
			} catch(IOException e) {
				System.out.println("Input Error:" + e);
			} catch(NullPointerException e) {
				System.out.println("Null Pointer Error:" + e);
			}
		}
	}
	
	/**
	 * Creates and returns a Record Object
	 * 
	 * @param eventId
	 * @param occurId
	 * @param taxonId
	 * @param individualCount
	 * @param latitude
	 * @param longitude
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Record createRecord(int eventId, String occurId, int taxonId, int individualCount, float latitude, float longitude, int year, int month, int day) {
		return new Record(eventId, occurId, taxonId, individualCount, latitude, longitude, year, month, day);
	}
	
	/**
	 * The main method initiates file processing of occurrances.csv located in the src folder.
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws NumberFormatException, ParseException {
		initProcessing();
	}
}
