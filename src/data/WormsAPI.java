package data;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//Need to add library

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class WormsAPI {

	public static void main(String[] args) throws IOException, ParseException {
		// small test
		//System.out.println(nameToID("Neogobius melanostomus"));
		//System.out.println(nameToRecordID("Neogobius melanostomus"));
		/*
		 * TaxonNode[] taxnodes = idToClassification(126916);
		 * 
		 * for (int i =0; i < taxnodes.length;i++) {
		 * System.out.println(taxnodes[i].getTaxonId());
		 * System.out.println(taxnodes[i].getTaxonType());
		 * System.out.println(taxnodes[i].getName()); }
		 */
		System.out.println(nameToRecordID("Hello"));
	}

	/**
	 * Search the WORMS database by scientific name to return Aphia (taxon) ID. This
	 * must be an exact name or it will fail. Use fuzzyNameToID if the name is not
	 * exact.
	 * 
	 * @param scientificName
	 *            Scientific name of taxon (family, genus, species, etc)
	 * @return Aphia (taxon) ID of given scientific name.
	 * @throws Exception 
	 */
	public static Integer nameToID(String scientificName) throws Exception {
		System.out.println("nameToID: " + scientificName);
		scientificName = repSpaces(scientificName);
		String resp = makeRequest(
				String.format("http://marinespecies.org/rest/AphiaIDByName/%s?marine_only=false", scientificName));
		if (resp.length() == 0) 
			throw new Exception("Name not found in WORMS database.");
		return Integer.parseInt(resp);
	}

	/**
	 * Search the WORMS database by fuzzy scientific name (a slightly misspelled
	 * name). This has the advantage of being more flexible but it can be less
	 * accurate and it is slower. If you have the actual scientific name, use
	 * nameToID() instead.
	 * 
	 * @param fuzzyName
	 *            Fuzzy scientific name of taxon (family, genus, species, etc)
	 * @return Aphia (taxon) ID of given scientific name.
	 * @throws IOException
	 * @throws ParseException 
	 */
	public static Integer fuzzyNameToID(String fuzzyName) throws IOException, ParseException {
		//System.out.println("Fuzzy name: " + fuzzyName);
		fuzzyName = repSpaces(fuzzyName);
		String resp = makeRequest(String.format(
				"http://marinespecies.org/rest/AphiaRecordsByMatchNames?scientificnames%%5B%%5D=%s&marine_only=false",
				fuzzyName));

		if (resp.length() == 0) return null;
		JSONParser parser = new JSONParser();
		
		JSONArray json = (JSONArray) parser.parse(resp);
		JSONObject first = (JSONObject)((JSONArray)json.get(0)).get(0);
		
		return (int) (long) first.get("AphiaID");
		
		
	}
	
	
	/**
	 * Search the WORMS database by a scientific name from multiple records  
	 * 
	 * @param Name
	 * @return Aphia (taxon) ID of given scientific name.
	 * @throws IOException
	 * @throws ParseException 
	 */
	public static Integer nameToRecordID(String Name) throws IOException, ParseException {
		//System.out.println( Name);
		Name = repSpaces(Name);
		String resp = makeRequest(String.format(
				"http://marinespecies.org/rest/AphiaRecordsByName/%s?like=true&marine_only=false&offset=1",
				Name));

		if (resp.length() == 0) return null;
		JSONParser parser = new JSONParser();
		
		JSONArray json = (JSONArray) parser.parse(resp);
		int id =  (int) (long) ((JSONObject)json.get(0)).get("AphiaID");
		
		return id;
	}

	/**
	 * Search by taxonId (AphiaID) and return bioclassification of that and above.
	 * 
	 * /AphiaClassificationByAphiaID/{ID}
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */

	public static TaxonNode[] idToClassification(int taxonId) throws IOException, ParseException {
		System.out.println("idToClassification " + taxonId);
		String resp = makeRequest(
				String.format("http://marinespecies.org/rest/AphiaClassificationByAphiaID/%d", taxonId));
		if (resp.length() == 0) return null;
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(resp);

		// Assume length of 8 based on number of taxontypes
		TaxonNode[] taxnodes = new TaxonNode[8];
		int arraysize = parseIdCall(taxnodes, json, 0);
		TaxonNode[] copiedArray = new TaxonNode[arraysize];
		System.arraycopy(taxnodes, 0, copiedArray, 0, arraysize);

		return copiedArray;

	}

	/**
	 * Perform a GET request to the given URL and return the content of the
	 * response.
	 * 
	 * @param url
	 *            The URL to which to make a request.
	 * @return The content returned by the server (if successful).
	 * @throws IOException
	 */
	private static String makeRequest(String url) throws IOException {
		// Request method adapted from http://www.baeldung.com/java-http-request
		// create new URL instance
		URL urll = new URL(url);
		// create and set up connection
		HttpURLConnection con = (HttpURLConnection) urll.openConnection();
		con.setRequestMethod("GET");

		int status = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
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
	 * 
	 * @param in
	 *            Input string
	 * @return String with spaces replaced by "%20" for spaces in URLs.
	 */
	private static String repSpaces(String in) {
		return in.replaceAll(" ", "%20");
	}

	/**
	 * Parses the json request recursively and also keeping track of the array
	 * length
	 * 
	 * @param nodes
	 *            the TaxonNode array
	 * @param current
	 *            current rank level of JSON file
	 * @param n
	 *            index of where the TaxonNode is being stored at
	 * @return arraysize number of elements added within array
	 */
	private static int parseIdCall(TaxonNode[] nodes, JSONObject current, int n) {
		boolean checktype = false;
		TaxonNode curNode = null;
		int arraysize = n;
		// Checks if rank matches the TaxonType Enum
		for (TaxonType c : TaxonType.values()) {

			if (c.name().equals((String) current.get("rank"))) {
				checktype = true;
				break;
			} else
				checktype = false;
		}
		JSONObject child = (JSONObject) current.get("child");

		if (checktype == true) {
			curNode = new TaxonNode((int) (long) current.get("AphiaID"), TaxonType.valueOf((String) current.get("rank")),
					(String) current.get("scientificname"));
			nodes[n] = curNode;
			n++;
		}
		// If child is null, return
		if ((JSONObject) current.get("child") == null)
			return n;
		arraysize = parseIdCall(nodes, child, n);
		return arraysize;
	}

}
