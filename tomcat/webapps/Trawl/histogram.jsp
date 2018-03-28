<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<script>

<%


	Iterable<Integer, Integer> = (Iterable<Integer,Integer>)request.getAttribute("record");
	Iterable<Integer> results = record.keys();
	out.print("var y=[];");
	out.print("var x=[];");
	for (Integer year: results){
		out.print("y.push("+(String) record.get(year) +");");
		out.print("x.push("+ (String) year+");");
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