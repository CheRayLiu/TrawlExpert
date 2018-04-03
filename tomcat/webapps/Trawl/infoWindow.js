//*This code references the info window google maps API
//https://developers.google.com/maps/documentation/javascript/infowindows

// Displays markers on a map centred at the Laurentian Great Lakes 
// When the user clicks the marker, an info window opens

function initInfo(lati, longi,info) {
	for(var i=0; i < lati.length; i++){
		
		// Generate map centred at Great Lakes
		  var map = new google.maps.Map(document.getElementById('map'), {
		    zoom: 5.5,
		    center:  {lat: 45.0349575, lng: -88.6941305};
		  });
		  
		  // Set info window contents to input string
		  var contentString = info[i];
		  var infowindow = new google.maps.InfoWindow({
		    content: contentString
		  });
		
		  // Plot points
		  var marker = new google.maps.Marker({
		    position: {lat: lati[i], lng: longi[i]} ,
		    map: map,
		  });
		  marker.addListener('click', function() {
		    infowindow.open(map, marker);
		  });
	}
}