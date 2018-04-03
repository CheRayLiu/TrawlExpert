//*This code references the info window google maps API
//https://developers.google.com/maps/documentation/javascript/infowindows

// Displays markers on a map centred at the Laurentian Great Lakes 
// When the user clicks the marker, an info window opens

function initInfo(lati, longi,name, date, count ) {
	console.log("Trying to gen. info map");
    // Generate map centred at Great Lakes
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 5.5,
        center:  {lat: 45.0349575, lng: -88.6941305}
    });
    console.log("Gened info map");

	for(var i=0; i < lati.length; i++){
        console.log("Trying to plot");
		  // Set info window contents to input string
        var contentString = '<div id=“content”>'+
            '<div id=“siteNotice”>'+
            '</div>'+
            '<h1 id=“firstHeading” class=“firstHeading”>' + name[i] + '</h1>'+
            '<div id=“bodyContent”>'+
            '<p><b>Name: </b>' + name[i] + '</p>'+
            '<p><b>Date: </b>' + findMonth(date[i].month) + ' ' + date[i].day + ', ' + date[i].year + '</p>'+
            '<p><b>Count: </b>' + count[i] + '</p>'+
            '</div>'+
            '</div>';
		  var infowindow = new google.maps.InfoWindow({
		    content: contentString
		  });
		
		  // Plot points
			  // var marker = new google.maps.Marker({
			  //   position: {lat: lati[i], lng: longi[i]} ,
			  //   map: map,
			  // });
		  google.maps.event.addListener('click', function(marker) {
		  	return function(){
                infowindow.open(map, marker);
			}

		  });
        console.log("Plotted" + i + " markers");
	}
}