import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; // Testing ONLY
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 
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
	
	public String getPath() {
		return path;
	}

	public void setPath(String newPath) {
		path = newPath;
	}
	
	// Calls Parse automatically
	private static void read() {
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
	
	//"scientificName" in raw data heading = species name
	private static void parse(String currentLine) {
		String[] splitLine = currentLine.split(",");
		String eventId = null, taxId = null, occurrenceId, latitude, longitude, month= null, year=null, day=null, individualCount;
		
		occurrenceId = splitLine[3];
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
			taxId = matchTaxId.group(1);
		}
		else {
			// TODO: Throw Exception?
			System.out.println("Could not parse TaxId. String may be unique or missing.");
		}
		
		//Taxonomy Info [12-18] - Using a species ADT instead?
		
		// At this point, adt constructor will be called to create the object. 
		// Testing ONLY Print lines
		System.out.println("Occurence Id:" + occurrenceId);
		System.out.println("Ind. Count:" + individualCount);
		System.out.println("event Id:" + eventId);
		System.out.println("Year:" + year);
		System.out.println("Month:" + month);
		System.out.println("Day:" + day);
		System.out.println("lat:" + latitude);
		System.out.println("long:" + longitude);
		System.out.println("tax Id:" + taxId);
	}
	
	public static void main(String[] args) {
		read();
	}
}
