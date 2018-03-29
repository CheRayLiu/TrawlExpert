<%@page import="org.json.simple.JSONArray"%>
<%@page import="sort.MergeSort"%>
<%@page import="sort.GeneralCompare"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST, search.BasicSearchResult, org.json.simple.JSONObject, data.TaxonNode" %>
<%
	TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
	JSONParser parser = new JSONParser();
	JSONObject req = (JSONObject) parser.parse(request.getReader().readLine());
	
	Integer taxonId = (int) (long) req.get("taxid");
	//System.out.println(taxonId);
	ArrayList<TaxonNode> txNodes = ((ArrayList<TaxonNode>) te.getTaxonRecord(taxonId).getChildren());
	TaxonNode[] txNodesAr = txNodes.toArray(new TaxonNode[txNodes.size()]);
	
	GeneralCompare<TaxonNode> gc = (tn0, tn1) -> ((TaxonNode) tn0).getName().compareTo(((TaxonNode) tn1).getName());
	MergeSort.sort(txNodesAr, 0, txNodesAr.length - 1, gc);

	JSONObject js = new JSONObject();
	JSONArray taxonIds = new JSONArray();
	JSONArray names    = new JSONArray();
	
	taxonIds.add(-1);
	names.add("Any");
	
	for (TaxonNode tx: txNodesAr){
		taxonIds.add(tx.getTaxonId());
		names.add(tx.getName());
	}
	
	js.put("taxonId", taxonIds);
	js.put("taxonName", names);
	
	out.print(js.toJSONString());
	
%>