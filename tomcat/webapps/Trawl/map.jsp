<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST, search.BasicSearchResult,data.BioTree,data.TaxonNode" %>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>

<%
		TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
		BasicSearchResult result = te.rangeSearch(2, 1960, 2016);

		
		
		JSONParser parser = new JSONParser();
		
		
		JSONObject js = new JSONObject();
		JSONArray longitude = new JSONArray();
		JSONArray latitude = new JSONArray();
		JSONArray name = new JSONArray();
		JSONArray date = new JSONArray();
		JSONArray count = new JSONArray();
		
		
		
		for (Record r: result.results()){
			longitude.add(r.getLongitude());
			latitude.add(r.getLatitude());
			name.add(BioTree.getTaxonRecord(r.getTaxonId()).getName());
			JSONObject dateobj = new JSONObject();
			dateobj.put("year",r.getDate().getYear());
			dateobj.put("month",r.getDate().getMonth());
			dateobj.put("day",r.getDate().getDay());
			date.add(r.getDate());
			count.add(r.getCount());
		}
	
		js.put("latitude", latitude);
		js.put("longitude", longitude);
		js.put("name", name);
		js.put("date", date);
		js.put("individual count", count);
		js.put("time", result.time());
		
	
		out.print(js.toJSONString());
%>

	