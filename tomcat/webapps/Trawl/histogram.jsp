<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST,search.trawl.BasicSearchResult" %>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>

<%
		TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
		JSONParser parser = new JSONParser();
		JSONObject req = (JSONObject) parser.parse(request.getReader().readLine());

		Integer taxonId = (int) (long) req.get("taxId");
		Integer yearLo = (int) (long) req.get("yearF");
		Integer yearHi = (int) (long) req.get("yearT");

		BasicSearchResult result = te.rangeSearch(taxonId, yearLo, yearHi);

		BST<Integer, Integer> histogram = result.histogram();
		Iterable<Integer> results = histogram.keys();
		
		
		JSONObject js = new JSONObject();
		JSONArray x = new JSONArray();
		JSONArray y    = new JSONArray();
		
		
		for (Integer year: results){
			x.add(year);
			y.add(histogram.get(year));
		}
	
		js.put("x", x);
		js.put("y", y);
		js.put("time", result.time());
		js.put("n", result.n());
		js.put("individualCount", result.sum());
	
		out.print(js.toJSONString());
%>

	