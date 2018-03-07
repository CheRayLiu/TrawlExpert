package biotree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; // Testing ONLY
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class reads and parses files in the format of occurences.csv
 * It provides methods to get chunks of data. 
 * 
 * Notes:
 * - Processes one row at a time
 * - Press enter for the next row. 
 * - Parsed data is in string format
 * - Data will be converted to the appropriate type in a toADT() function
 */
public class FileProcessor {
	private static String path = "src/occurrence.csv";
	
	/**
	 * Gets the path string
	 * 
	 * @return Path String
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Sets the path string
	 */
	public void setPath(String newPath) {
		path = newPath;
	}
	
	/**
	 *  Reads file at path. 
	 *  Calls parse() automatically
	 */
	private static void initProcessing() {
		FileReader fr;
		BufferedReader br;

		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			
			Scanner s = new Scanner(System.in); //Testing ONLY
			String currentLine;
			
			br.readLine();	// Reads Past Field Names
			while ((currentLine = br.readLine()) != null) {
				System.out.println(currentLine);
				parse(currentLine);
				s.nextLine(); //Testing ONLY for checking one line at a time
			}
			
			s.close();//Testing ONLY
			br.close();
			fr.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parses out data from string
	 * 
	 * @param currentLine, a line/row of data
	 */
	// TODO: Fix spahgetti code where taxonId needs to be converted to a string when it is received from biotree.
	private static void parse(String currentLine) throws IOException {
		String[] splitLine = currentLine.split(",");
		String eventId = null, occurId, taxonId = null, individualCount, latitude, longitude, year=null, month= null, day=null;
		
		occurId = splitLine[3];
		individualCount = splitLine[4];
		
		Pattern patternEventId = Pattern.compile("OP_ID (\\d+)");
		Matcher matchEventId = patternEventId.matcher(splitLine[7]);
		if(matchEventId.find()) {
			eventId = matchEventId.group(1);
		}
		else {
			// TODO: Throw Exception?
			System.out.println("Could not parse eventId. String may be unique or missing.");
		}
		
		Pattern patternDate = Pattern.compile("(\\d+)-(\\d+)-(\\d+)");
		Matcher matchDate = patternDate.matcher(splitLine[8]);
		if(matchDate.find()) {
			year = matchDate.group(1);
			month = matchDate.group(2);
			day = matchDate.group(3);
		}
		else {
			// TODO: Throw Exception?
			System.out.println("Could not parse eventId. String may be unique or missing.");
		}
		latitude = splitLine[9];
		longitude = splitLine[10];
		
		Pattern patternTaxId = Pattern.compile(":(\\d+)");
		Matcher matchTaxId = patternTaxId.matcher(splitLine[11]);
		if(matchTaxId.find()) {
			taxonId = matchTaxId.group(1);
		}
		else {
			// TODO: Throw Exception?
			System.out.println("Could not parse TaxId. String may be unique or missing.");
		}
		
		// Call BioTree
		if(taxonId != null) {
			BioTree.processRecord(Integer.parseInt(taxonId));
		}
		else if (splitLine[12] != "NA") {
			try{
				taxonId = Integer.toString(BioTree.processRecord(splitLine[12]));
			} catch(IOException e) {
				System.out.println("No Taxon ID or Scientific Name");
			}
		}		
		
		// Create Record Object
		createRecord(Integer.parseInt(eventId), occurId, Integer.parseInt(taxonId), Integer.parseInt(individualCount), Float.parseFloat(latitude), Float.parseFloat(longitude), Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		
		
		// Testing ONLY Print lines
//		System.out.println("Occurence Id:" + occurId);
//		System.out.println("Ind. Count:" + individualCount);
//		System.out.println("event Id:" + eventId);
//		System.out.println("Year:" + year);
//		System.out.println("Month:" + month);
//		System.out.println("Day:" + day);
//		System.out.println("lat:" + latitude);
//		System.out.println("long:" + longitude);
//		System.out.println("tax Id:" + taxonId);
	}
	
	public static Record createRecord(int eventId, String occurId, int taxonId, int individualCount, float latitude, float longitude, int year, int month, int day) {
		return new Record(eventId, occurId, taxonId, individualCount, latitude, longitude, year, month, day);
	}
	
	public static void main(String[] args) {
		initProcessing();
	}
}
