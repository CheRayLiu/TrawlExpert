/**
 * Generates html text to fill info windows on a Google Maps API
 * @param names String array of scientific fish names
 * @param dates Array of JSON objects containing 3 arrays: Year[], Month[], Day[]
 * @param count Integer array of counts of each fish species
 * @returns String array where each entry is html code to describe a marker on a map
 */
function infoGenerator(names, dates, count){

	var infoArray =[];
	
	for (int i=0; i < names.length; i++){
		// Initialize variables to each data point's information
		 var year = dates[i][0];
		 var month = dates[i][1];
		 var day = dates[i][2];
		 var name = names[i];
		 var count = count[i];

		 // Generate string of html
		 var contentString = ‘<div id=“content”>‘+
		     ‘<div id=“siteNotice”>‘+
		     ‘</div>‘+
		     ‘<h1 id=“firstHeading” class=“firstHeading”>’ + name + ‘</h1>‘+
		     ‘<div id=“bodyContent”>‘+
		     ‘<p><b>Name: </b>’ + name + ‘</p>‘+
		     ‘<p><b>Date: </b>’ + month + ' ' + day + ‘, ’ + year + ‘</p>‘+
		     ‘<p><b>Count: </b>’ + count + ‘</p>‘+
		     ‘</div>‘+
		     ‘</div>’;	
		     
		 infoArray.push(contentString);
		    }
	
	return infoArray;
}
