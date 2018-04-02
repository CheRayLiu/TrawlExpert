function infoGenerator(names, dates, count){
	
	
	
	var infoArray =[];
	
	for (int i=0; i < names.length; i++){
		 var year = dates[i][0];
		 var month = dates[i][1];
		 var day = dates[i][2];
		 var name = names[i];
		 var count = count[i];

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
