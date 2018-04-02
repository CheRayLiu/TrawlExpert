function histogram(x,y){
	console.log("Histogram")
	var data = [
	  	{ 	x: x,
	    	y: y,
	    	type: 'bar',
	     	marker: {
	    	color: '#76ADD6',
	    	},
	  	}
		];

		var layout = {
			title: 'Individual count vs Year',
	    	xaxis:{title: 'Year',
	    			type: 'category',
	    		titlefont: {
	      			family: 'Courier New, monospace',
	      			size: 18,
	      			color: '#7f7f7f'
	    			}
		, autosize: true
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
		
}

