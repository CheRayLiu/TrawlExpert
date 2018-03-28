




<%@ page import="java.util.*, data.Record, model.TrawlExpert, search.BST, search.BasicSearchResult" %>
<head>
  <!-- Plotly.js -->
  <script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
</head>

<body>
  
  <div id="histogram"><!-- Plotly chart will be drawn inside this DIV --></div>
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
  	{ 	x: x,
    	y: y,
    	type: 'bar',
     	marker: {
    	color: 'blue',
    	},
  	}
	];

	var layout = {
		title: 'Individual count vs Year',
    	xaxis:{title: 'Year',
    		titlefont: {
      			family: 'Courier New, monospace',
      			size: 18,
      			color: '#7f7f7f'
    			}
    	},
    	yaxis:{title: 'Individual count',
    		titlefont: {
      			family: 'Courier New, monospace',
      			size: 18,
      			color: '#7f7f7f'
    			}
    		}
	}
	Plotly.newPlot('histogram', data,layout);  
	</script>
</body>
