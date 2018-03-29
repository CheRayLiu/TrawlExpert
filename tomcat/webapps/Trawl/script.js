// Initialization
function init() {
    initPhylum()
}

// Post Function
// Used by getChildren(id)
function post(path, params) {
    document.getElementById("console").innerHTML += "In | post()<br>"; //TODO: Console REMOVEME
    var xhr = new XMLHttpRequest();
    xhr.open("POST", path);

    //Send the proper header information along with the request
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    // xhr.setRequestHeader("Content-type", "application/json");

    xhr.onreadystatechange = function() {//Call a function when the state changes (i.e. response comes back)
            document.getElementById("console").innerHTML += "In | onreadystatechange ... "; //TODO: Console REMOVEME
            //this is where we can update the various dropdowns
            if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
                document.getElementById("console").innerHTML += "Retrieved:<br>"; //TODO: Console REMOVEME
                document.getElementById("console").innerHTML += xhr.responseText + "<br>"; //TODO: Console REMOVEME
                console.log("RETRIEVED:")
                console.log(this.responseText); // Have to go to dev console to print this. Won't show up on pseudo-console.
                // return this.responseText;
            }
            else{
                document.getElementById("console").innerHTML += "Error<br>"; //TODO: Console REMOVEME
            }
    };
    //generate JSON string
    var jsonString= JSON.stringify(params);
    console.log(jsonString);
    //send request to server
    xhr.send(jsonString);
    // xhr.send('{taxid":"2"}');
    document.getElementById("console").innerHTML += "Sent request to " + path + ": "  + jsonString + "<br>"; //TODO: Console REMOVEME
}

// Get Children ranks of Parent ranks from Java
function getChildren(id) {
    document.getElementById("console").innerHTML += "In | getChildren()<br>"; //TODO: Console REMOVEME
    var nodeList;
    nodeList = {
        "taxonId":[1821,51,1065],
        "taxonName":["Chordata","Mollusca","Arthropoda"]
    };
    //nodeList = post('doBioLookup.do', { taxid: id});    // {taxid: intID}
    return nodeList;
}

// Creates Phylum dropdown choices on page load
function initPhylum(){
    // alert("initPhylum function");
    document.getElementById("console").innerHTML += "init phylum<br>"; //TODO: Console REMOVEME
    var parentId = 2;   //Animalia AphiaID = 2
    var nodeList = getChildren(parentId);
    var content = "", x, y;

    for (i in nodeList.taxonId){
        x = nodeList.taxonId[i];
        y = nodeList.taxonName[i];
        content += '"<option value="' + x + '">' + y + '</option>';
    }

    document.getElementById("pickPhylum").innerHTML = content;
}

function changedSciR(rank){
    // var changedRank = document.getElementsByName()
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