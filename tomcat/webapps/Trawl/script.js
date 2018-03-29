// Initialization
function init() {
    initPickPhylum();
}

// nodeList = {
//    "taxonId":[1821,51,1065],
//    "taxonName":["Chordata","Mollusca","Arthropoda"]
// };

function initPickPhylum(){
    updateSciR("pickPhylum", {value:2});
}

// Updates a dropdown List
// tagID = tag of dropdown that changes
// taxID = id of parent of the thing that changes
function updateSciR(tagID, object) {
    var path = 'doBioLookup.do';
    var taxID = object.value;
    alert("Updating: " + tagID + "with children of: " + taxID);   //TODO Remove me
    var params = { taxId: taxID};
    var xhr = new XMLHttpRequest();
    xhr.open("POST", path);

    //Send the proper header information along with the request
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function() {//Call a function when the state changes (i.e. response comes back)
            // Update the dropdown
            if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
                document.getElementById("console").innerHTML += "Retrieved: "; //TODO: Console REMOVEME
                document.getElementById("console").innerHTML += this.responseText + "<br>"; //TODO: Console REMOVEME
                console.log("RETRIEVED:" + this.responseText);
                var nodeList = JSON.parse(this.responseText);
                document.getElementById("console").innerHTML += nodeList + "<br>"; //TODO: Console REMOVEME

                var content = "", x, y;
                for (var i in nodeList.taxonId){
                    x = nodeList.taxonId[i];
                    y = nodeList.taxonName[i];
                    content += '"<option value="' + x + '">' + y + '</option>';
                }

                document.getElementById(tagID).innerHTML = content;
            }
            else{
                document.getElementById("console").innerHTML += "Error<br>"; //TODO: Console REMOVEME
            }
    };
    //generate JSON string
    var jsonString= JSON.stringify(params);

    //send request to server
    xhr.send(jsonString);   // xhr.send('{taxid":"2"}');
    document.getElementById("console").innerHTML += "Sent request to " + path + ": "  + jsonString + "<br>"; //TODO: Console REMOVEME
}

// JQuery for Range Slider
$( function() {
    $( "#slider-range" ).slider({
        range: true,
        min: 1960,
        max: 2016,
        values: [ 1960, 2016 ],
        slide: function( event, ui ) {
            $( "#fromtoYear" ).html(ui.values[ 0 ] + " - " + ui.values[ 1 ] )
        }
    });
    $( "#fromtoYear" ).html($( "#slider-range" ).slider( "values", 0 ) + " - " + $( "#slider-range" ).slider( "values", 1 ))
} );

window.onload=init;