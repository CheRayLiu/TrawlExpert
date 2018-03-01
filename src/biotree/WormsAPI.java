package biotree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WormsAPI {
	
	public static void main(String[] args) throws IOException {
		//small test
		System.out.println(nameToID("Neogobius melanostomus"));
	}
	
	/**
	 * Search the WORMS database by scientific name to return Aphia (taxon) ID.
	 * This must be an exact name or it will fail. Use fuzzyNameToID if the name 
	 * is not exact.
	 * @param scientificName Scientific name of taxon (family, genus, species, etc)
	 * @return Aphia (taxon) ID of given scientific name.
	 * @throws IOException
	 */
	public static int nameToID(String scientificName) throws IOException{
		scientificName = repSpaces(scientificName);
		String resp = makeRequest(String.format("http://marinespecies.org/rest/AphiaIDByName/%s?marine_only=false", scientificName));
		
		return Integer.parseInt(resp);
	}
	
	/**
	 * Search the WORMS database by fuzzy scientific name (a slightly misspelled name).
	 * This has the advantage of being more flexible but it can be less accurate and it
	 * is slower. If you have the actual scientific name, use nameToID() instead.
	 * @param fuzzyName Fuzzy scientific name of taxon (family, genus, species, etc)
	 * @return Aphia (taxon) ID of given scientific name.
	 * @throws IOException
	 */
	public static int fuzzyNameToID(String fuzzyName) throws IOException{
		fuzzyName = repSpaces(fuzzyName);
		String resp = makeRequest(String.format("http://marinespecies.org/rest/AphiaRecordsByMatchNames?scientificnames%5B%5D=%s&marine_only=true", fuzzyName));
		
		//TODO: finish this function.
		return 123123;
	}
	
	/**
	 * Search by taxonId (AphiaID) and return bioclassification of that and above.
	 * http://marinespecies.org/rest/
	 * /AphiaClassificationByAphiaID/{ID}
	 */
	public static <Integer,String>[] idToClassification(int taxonId) {
		
	}
	
	
	
	/**
	 * Perform a GET request to the given URL and return the content of the response.
	 * @param url The URL to which to make a request.
	 * @return The content returned by the server (if successful).
	 * @throws IOException
	 */
	private static String makeRequest(String url) throws IOException {
		//Request method adapted from http://www.baeldung.com/java-http-request
		//create new URL instance
		URL urll = new URL(url);
		//create and set up connection
		HttpURLConnection con = (HttpURLConnection) urll.openConnection();
		con.setRequestMethod("GET");
		
		int status = con.getResponseCode();
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		con.disconnect();
		
		return content.toString();
	}
	
	/**
	 * Helper to replace a space with the correct one for URLs.
	 * @param in Input string
	 * @return String with spaces replaced by "%20" for spaces in URLs.
	 */
	private static String repSpaces(String in) {
		return in.replaceAll(" ", "%20");
	}
}
