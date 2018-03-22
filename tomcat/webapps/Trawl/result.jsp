<%@ page import="java.util.*" %>
<html>
<body>
<h1 align="center">Trawl Results</h1>
<p>

<%
	Iterable<Integer> species = (Iterable<Integer>)request.getAttribute("species");
	for (Integer s: species)
		out.print("SpeciesID: " + s + "<br>");
	out.print((String) request.getAttribute("url"));
%>
</body>
</html>