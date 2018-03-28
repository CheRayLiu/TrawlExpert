<%@page import="search.BasicSearchResult"%>
<%@ page import="java.util.*, data.Record, model.TrawlExpert" %>
<html>
<body>
<h1 align="center">Trawl Results</h1>
<p>

<table>
<tr>
	<th>Scientific Name</th>
	<th>Year</th>
	<th>Month</th>
	<th>Day</th>
	<th>Latitude</th>
	<th>Longitude</th>
	<th>Individual Count</th>
</tr>
<%
	TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
	BasicSearchResult results = te.rangeSearch(159512, 1960, 2016);
	for (Record r: results.results()){
		out.print("<tr>\n");
		out.print("<th>" + te.getTaxonRecord(r.getTaxonId()).getName() + "</th>\n");
		out.print("<th>" + r.getDate().getYear() + "</th>\n");
		out.print("<th>" + r.getDate().getMonth() + "</th>\n");
		out.print("<th>" + r.getDate().getDay() + "</th>\n");
		out.print("<th>" + r.getLatitude() + "</th>\n");
		out.print("<th>" + r.getLongitude() + "</th>\n");
		out.print("<th>" + r.getCount() + "</th>\n");
		out.print("</tr>\n");
	}
%>
</table>
</body>
</html>