<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST,search.trawl.BasicSearchResult,data.BioTree,data.TaxonNode" %>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>

<%
	// Sample result data containing an iterable of records
		TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
		JSONParser parser = new JSONParser();
		JSONObject req = (JSONObject) parser.parse(request.getReader().readLine());
		
		Integer taxonId = (int) (long) req.get("taxId");
		Integer yearLo = (int) (long) req.get("yearF");
		Integer yearHi = (int) (long) req.get("yearT");

		BasicSearchResult result = te.rangeSearch(taxonId, yearLo, yearHi);
		
		// Initialize JSON Object and Arrays
		JSONObject js = new JSONObject();
		JSONArray longitude = new JSONArray();
		JSONArray latitude = new JSONArray();
		JSONArray name = new JSONArray();
		JSONArray date = new JSONArray();
		JSONArray count = new JSONArray();
		
		
		// Update value of each JSON Object/Array at the same index as the corresponding Record in Result input
		for (Record r: result.results()){
			longitude.add(r.getLongitude());
			latitude.add(r.getLatitude());
			name.add(BioTree.getTaxonRecord(r.getTaxonId()).getName());
			JSONObject dateobj = new JSONObject();
			dateobj.put("year",r.getDate().getYear());
			dateobj.put("month",r.getDate().getMonth());
			dateobj.put("day",r.getDate().getDay());
			date.add(dateobj);
			count.add(r.getCount());
		}
	
		// Insert JSON Array and Objects into main Object
		js.put("latitude", latitude);
		js.put("longitude", longitude);
		js.put("name", name);
		js.put("date", date);
		js.put("n", result.n());
		js.put("individualCount", count);
		js.put("time", result.time());
		
	
		out.print(js.toJSONString());
%>

	