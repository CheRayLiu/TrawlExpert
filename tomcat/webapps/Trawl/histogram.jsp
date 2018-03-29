<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST, search.BasicSearchResult" %>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>

<%

		TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
		BasicSearchResult result = te.rangeSearch(2, 1960, 2016);

		BST<Integer, Integer> histogram = result.histogram();
		Iterable<Integer> results = histogram.keys();
		
		JSONParser parser = new JSONParser();
		
		
		JSONObject js = new JSONObject();
		JSONArray x = new JSONArray();
		JSONArray y    = new JSONArray();
		
		
		for (Integer year: results){
			x.add(year);
			y.add(histogram.get(year));
		}
	
		js.put("year", x);
		js.put("individualCount", y);
	
		out.print(js.toJSONString());
			
		
%>

	