<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST, search.BasicSearchResult, org.json.simple.JSONObject, data.TaxonNode" %>
<%
	TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
	Iterable<TaxonNode> txNodes = te.getTaxonRecord(2).getChildren();
	JSONParser parser = new JSONParser();
	//JSONObject req = (JSONObject) parser.parse(request.getReader().toString());
	
	//Integer taxonId = (int) (long) req.get("taxonId");

	JSONObject js = new JSONObject();
	JSONArray taxonIds = new JSONArray();
	JSONArray names    = new JSONArray();
	
	for (TaxonNode tx: txNodes){
		taxonIds.add(tx.getTaxonId());
		names.add(tx.getName());
	}
	
	js.put("taxonId", taxonIds);
	js.put("taxonName", names);
	
	out.print(js.toJSONString());
	
%>