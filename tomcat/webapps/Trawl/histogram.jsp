<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST, search.BasicSearchResult" %>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<script>

<%

	TrawlExpert te = (TrawlExpert)request.getServletContext().getAttribute("trawl");
	BasicSearchResult result = te.rangeSearch(159512, 1960, 2016);

	BST<Integer, Integer> histogram = result.histogram();
	Iterable<Integer> results = histogram.keys();
	out.print("var y=[];");
	out.print("var x=[];");
	for (Integer year: results){
		out.print("y.push("+ histogram.get(year) +");");
		out.print("x.push("+ year +");");
	}

	out.print((String) request.getAttribute("url"));

%>

var data = [
  { x: x,
    y: y,
    type: 'bar',
    orientation: 'h',
     marker: {
    color: 'blue',
    },
  }
];

var layout = {
    xaxis:{
        autorange:'reversed'
    },
    yaxis:{
      side:'right'
    }
}
Plotly.newPlot('Histogram', data,layout);  
</script>