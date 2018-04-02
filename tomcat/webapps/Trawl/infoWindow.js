//*This code references the info window google maps API
//https://developers.google.com/maps/documentation/javascript/infowindows

// This example displays a marker at the center of Australia.
// When the user clicks the marker, an info window opens.

function initMap(lati, longi,info) {
	for(int i=0; i < lati.length; i++){
		
	
		  
		  var map = new google.maps.Map(document.getElementById('map'), {
		    zoom: 5.5,
		    center:  {lat: 45.0349575, lng: -88.6941305};
		  });
		  
		  
		  var contentString = info[i];
		
		  var infowindow = new google.maps.InfoWindow({
		    content: contentString
		  });
		
		  var marker = new google.maps.Marker({
		    position: {lat: lati[i], lng: longi[i]} ,
		    map: map,
		  });
		  marker.addListener('click', function() {
		    infowindow.open(map, marker);
		  });
	}
}