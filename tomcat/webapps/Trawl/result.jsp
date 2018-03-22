<%@ page import="java.util.*" %>
<html>
<body>
<h1 align="center">Trawl Results</h1>
<p>

<%
	long time = (long)request.getAttribute("time");
	out.print("The server was started at " + time);
%>
</body>
</html>