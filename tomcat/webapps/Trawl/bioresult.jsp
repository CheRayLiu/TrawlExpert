<%@page import="org.json.simple.JSONArray"%>
<%@page import="sort.MergeSort"%>
<%@page import="sort.GeneralCompare"%>
<%@page import="search.RedBlackTree"%>
<%@page import="search.Field"%>
<%@page import="data.biotree.TaxonType"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST,search.trawl.BasicSearchResult, org.json.simple.JSONObject,data.biotree.TaxonNode" %>
<%
	TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
	JSONParser parser = new JSONParser();
	JSONObject req = (JSONObject) parser.parse(request.getReader().readLine());
	
	Integer taxonId = (int) (long) req.get("taxId");
	//System.out.println(taxonId);
	
	//get all children
	ArrayList<Integer> txIds = ((ArrayList<Integer>) te.getAllChildren(taxonId));
	
	//GeneralCompare<String> gc = (t0, t1) -> ((String) t0).compareTo((String)t1);
	//Field<String, ArrayList<TaxonNode>> fld = tn -> ((ArrayList<TaxonNode>) tn).get(0).getName().toString().toLowerCase();
	//RedBlackTree<String, ArrayList<TaxonNode>> tt = new RedBlackTree<String, ArrayList<TaxonNode>>(fld, gc);

	BST<TaxonType, ArrayList<TaxonNode>> tt = new BST<TaxonType, ArrayList<TaxonNode>>();
	
	//add to BST in bins
	for (Integer txId: txIds){
		TaxonNode txNode = te.getTaxonRecord(txId);
		ArrayList<TaxonNode> txList = tt.get(txNode.getTaxonType());
		if (txList == null){
			txList = new ArrayList<TaxonNode>();
			txList.add(txNode);
			tt.put(txNode.getTaxonType(), txList);
		} else{
			txList.add(txNode);
		}
	}
	//initialize JSON objects and arrays
	JSONObject js = new JSONObject();
	JSONArray taxonIds = new JSONArray();
	JSONArray names    = new JSONArray();
	
	GeneralCompare<TaxonNode> gc = (tn0, tn1) -> ((TaxonNode) tn0).getName().compareTo(((TaxonNode) tn1).getName());

	//iterate through all results
	for(TaxonType taxType: tt.keys()){
		String name = taxType.toString().toLowerCase();
		ArrayList<TaxonNode> al = tt.get(taxType);
		TaxonNode[] txNodesAr = al.toArray(new TaxonNode[al.size()]);
		//sort result
		MergeSort.sort(txNodesAr, 0, txNodesAr.length - 1, gc);
		taxonIds = new JSONArray();
		names = new JSONArray();
		
		taxonIds.add(-1);
		names.add("Any");
		
		for (TaxonNode tx: txNodesAr){
			taxonIds.add(tx.getTaxonId());
			names.add(tx.getName());
		}
		JSONObject inner = new JSONObject();
		inner.put("taxonName", names);
		inner.put("taxonId", taxonIds);
		
		js.put(name, inner);
	}
	
	out.print(js.toJSONString());
	
%>