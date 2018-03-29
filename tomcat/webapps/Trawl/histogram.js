function histogram(x,y){
	console.log("Histogram")
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
	    			type: 'category',
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
		
}

window.onload = histogram([1990,1991,1992],[54656,65654,32555]);

