<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST, search.BasicSearchResult,data.BioTree,data.TaxonNode,search.RecordCluster,sandbox.Point" %>
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
		double area = (double) (long) req.get("area");

		BasicSearchResult result = te.rangeSearch(taxonId, yearLo, yearHi);
		
		ArrayList<RecordCluster> clusters = result.cluster(area);
		
		// Initialize JSON Object and Arrays
		JSONObject js = new JSONObject();
		JSONArray longitude = new JSONArray();
		JSONArray latitude = new JSONArray();
		JSONArray count = new JSONArray();
		
		// Update value of each JSON Object/Array at the same index as the corresponding Record in Result input
		for (RecordCluster rc: clusters){
			Point centroid = rc.centroid();
			
			longitude.add(centroid.getX());
			latitude.add(centroid.getY());
			JSONObject dateobj = new JSONObject();
			count.add(rc.getCount());
		}
	
		// Insert JSON Array and Objects into main Object
		js.put("latitude", latitude);
		js.put("longitude", longitude);
		js.put("n", count);
		js.put("individualCount", result.sum());
		js.put("time", result.time());

		out.print(js.toJSONString());
%>

	