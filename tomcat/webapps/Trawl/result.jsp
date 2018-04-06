<%@page import="search.BasicSearchResult"%>
<%@ page import="java.util.*, data.Record, model.TrawlExpert" %>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<html>
<head>
<script src='tablesort.min.js'></script>
<!-- Include sort types you need -->
<script src='tablesort.number.js'></script>
<script src='tablesort.date.js'></script>
<link rel="stylesheet" type="text/css" href="tablesort.css">

<script>
  new Tablesort(document.getElementById('table-id'));
</script>
</head>
<body>
<p>

<table id="table">
<tr>
	<th role="columnheader">Scientific Name</th>
	<th role="columnheader">Year</th>
	<th role="columnheader">Month</th>
	<th role="columnheader">Day</th>
	<th role="columnheader">Latitude</th>
	<th role="columnheader">Longitude</th>
	<th role="columnheader">Individual Count</th>
</tr>
<%
	TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
	JSONParser parser = new JSONParser();
	JSONObject req = (JSONObject) parser.parse(request.getReader().readLine());
	
	Integer taxonId = (int) (long) req.get("taxId");
	Integer yearLo = (int) (long) req.get("yearF");
	Integer yearHi = (int) (long) req.get("yearT");
	Integer searchLo = (int) (long) req.get("searchLo");
	Integer searchHi = (int) (long) req.get("searchHi");

	BasicSearchResult results = te.rangeSearch(taxonId, yearLo, yearHi);
	int newSearchHiinc= 0, newSearchLoinc =0 , newSearchHidec= 0, newSearchLodec= 0;
	if (searchHi + 50 < results.n()){
		newSearchLoinc = (searchLo + 50);
		newSearchHiinc = (searchHi + 50);
	}else if (searchHi + 50 > results.n()){
		newSearchLoinc = results.n() - results.n() % 50;
		newSearchHiinc = results.n() - 1;
	}
	
	if (searchLo > 0){
		newSearchLodec = (searchLo - 50);
		newSearchHidec = (searchHi - 50);
	} else if (searchLo - 50 < 0){
		newSearchLodec = 0;
		newSearchHidec = 50;
	}
	if (searchHi > results.n())
		searchHi = results.n() - 1;
	out.print("Found " + results.n() + " results (" + results.time() + " seconds) (Showing " + searchLo + " - " + searchHi + ")");
	
	String incBtn = String.format("reqResults(JSON.stringify({taxId:%d,yearF:%d,yearT:%d,area:0}),%d,%d)", taxonId, yearLo, yearHi, newSearchLoinc, newSearchHiinc);
	String decBtn = String.format("reqResults(JSON.stringify({taxId:%d,yearF:%d,yearT:%d,area:0}),%d,%d)", taxonId, yearLo, yearHi, newSearchLodec, newSearchHidec);

	out.print("<button type='button' id='nextBtn', onclick='"+decBtn+"'><</button>" + "<button type='button' id='nextBtn', onclick='"+incBtn+"'>></button>");
	
	int i = 0;
	for (Record r: results.results()){
		if (i > searchLo && i < searchHi) {
			out.print("<tr>\n");
			out.print("<td>" + te.getTaxonRecord(r.getTaxonId()).getName() + "</td>\n");
			out.print("<td>" + r.getDate().getYear() + "</td>\n");
			out.print("<td>" + r.getDate().getMonth() + "</td>\n");
			out.print("<td>" + r.getDate().getDay() + "</td>\n");
			out.print("<td>" + r.getLatitude() + "</td>\n");
			out.print("<td>" + r.getLongitude() + "</td>\n");
			out.print("<td>" + r.getCount() + "</td>\n");
			out.print("</tr>\n");
		} else if (i > searchHi) break;
		i++;
	}
%>
</table>
</body>
</html>