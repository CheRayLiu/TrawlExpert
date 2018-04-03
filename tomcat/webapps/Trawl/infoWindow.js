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
		  

function initInfo(lati, longi,name, date, count ) {
	console.log("Trying to gen. info map");
    // Generate map centred at Great Lakes
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 5.5,
        center:  {lat: 45.0349575, lng: -88.6941305}
    });
    console.log("Gened info map");



		  // Set info window contents to input string

	var infowindow = new google.maps.InfoWindow();
	var marker, i;
	for (i = 0; i < lati.length; i++) {
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(lati[i], longi[i]),
            map: map
        });


        google.maps.event.addListener(marker, 'click', (function (marker, i) {
            return function () {
                infowindow.setContent('<div id=“content”>' +
                    '<div id=“siteNotice”>' +
                    '</div>' +
                    '<h1 id=“firstHeading” class=“firstHeading”>' + name[i] + '</h1>' +
                    '<div id=“bodyContent”>' +
                    '<p><b>Name: </b>' + name[i] + '</p>' +
                    '<p><b>Date: </b>' + findMonth(date[i].month) + ' ' + date[i].day + ', ' + date[i].year + '</p>' +
                    '<p><b>Count: </b>' + count[i] + '</p>' +
                    '</div>' +
                    '</div>');
                infowindow.open(map, marker);
            }
        })(marker, i));

        console.log("Plotted" + i + " markers");
    }
}