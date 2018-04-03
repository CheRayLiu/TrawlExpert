//*This code references the info window google maps API
//https://developers.google.com/maps/documentation/javascript/infowindows

// Displays markers on a map centred at the Laurentian Great Lakes 
// When the user clicks the marker, an info window opens
var openCWindow;

function initCluster(lati, longi,n,  individualCount) {
    // Generate map centred at Great Lakes
    var map = new google.maps.Map(document.getElementById('cluster'), {
        zoom: 5.5,
        center: {lat: 45.0349575, lng: -88.6941305}
    });
    console.log("Gened info map");
    setClusters(map, lati, longi,n, individualCount);
}


		  // Set info window contents to input string
function setCluster(map, lati, longi,n, individualCount) {
	for (var i = 0; i < lati.length; i++)
	{
        var marker = new google.maps.Marker({position: new google.maps.LatLng(lati[i], longi[i]), map: map});
        // var content = "point numero" + i;
        var content = '<div>' +
			'Name:' + name[i] +
            '<br>Cluster Count: ' + n[i]+
            '<br>Individual Count:' + individualCount[i] + '</div></div>';
		var infowindow = new google.maps.InfoWindow();

        google.maps.event.addListener(marker,'click', (function(marker,content,infowindow){
            return function() {
                console.log("Clicked!!!!");
                if (openCWindow)
                    openCWindow.close();
                infowindow.setContent(content);
				openCWindow = infowindow;
                infowindow.open(map,marker);
            };
        })(marker,content,infowindow));
        console.log(i);
	}
}

