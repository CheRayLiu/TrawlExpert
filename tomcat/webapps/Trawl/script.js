// Initialization
function init() {
    callUpdateSci({id:"pickAnimalia", value:2});    // Propagate Dropdowns on Startup
    reqHistogram(2);
}

function resolveAny(obj) {
    if(obj.id === "pickPhylum"){
        return {id:"pickAnimalia", value:2};
    }
    else {
        if (Number(obj.previousElementSibling.value) === -1) {
            return resolveAny(obj.previousElementSibling);
        }
        else {
            return {id: obj.previousElementSibling.id, value: obj.previousElementSibling.value};
        }
    }
}

function callUpdateSci(object){
    var tagID = object.id;
    var taxID = Number(object.value);

    // Resolve "Any" option
    if (taxID === -1){
        var obj2 = resolveAny(object);
        tagID = obj2.id;
        taxID = Number(obj2.value);
    }
    reqBioLookup(tagID, taxID);
}

function populateDropdown(childTagID, nodeList, subnode){
    var x, y, content="";
    for (var i in nodeList[subnode]["taxonName"]){
        x = nodeList[subnode]["taxonId"][i];
        y = nodeList[subnode]["taxonName"][i];
        content += '"<option value="' + x + '">' + y + '</option>';
    }
    document.getElementById(childTagID).innerHTML = content;
}

function updateSci(tagID, nodeList) {
    while (tagID !== "END"){
        switch (tagID){
            case "pickAnimalia":
                populateDropdown("pickPhylum", nodeList, "phylum");
                tagID = "pickPhylum";
                break;

            case "pickPhylum":
                populateDropdown("pickClass", nodeList, "class");
                tagID = "pickClass";
                break;

            case "pickClass":
                populateDropdown("pickOrder", nodeList, "order");
                tagID = "pickOrder";
                break;

            case "pickOrder":
                populateDropdown("pickFamily", nodeList, "family");
                tagID = "pickFamily";
                break;

            case "pickFamily":
                populateDropdown("pickGenus", nodeList, "genus");
                tagID = "pickGenus";
                break;

            case "pickGenus":
                populateDropdown("pickSpecies", nodeList, "species");
                tagID = "pickSpecies";
                break;

            case "pickSpecies":
                tagID = "END";
                break;
        }
    }
}

// This function will get the new nodelist from the server.
// ParentId is the taxId of the node that holds the list of children we want.
function reqBioLookup(tagID, parentId){
    var path = 'doBioLookup.do';
    var params = {taxId: Number(parentId)};
    var xhr = new XMLHttpRequest();
    xhr.open("POST", path);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  //Send the proper header info

    xhr.onreadystatechange = function() {//Call a function when the state changes (i.e. response comes back)
        // Update the dropdown when response is ready
        if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
            var nodeList = JSON.parse(this.responseText);
            updateSci(tagID, nodeList);
        }
        else{
            console.log("Server Response: Error"); //RME
        }
    };

    var jsonString= JSON.stringify(params);     //generate JSON string
    xhr.send(jsonString);                       //send request to server
    // document.getElementById("console").innerHTML += "Sent request to " + path + ": "  + jsonString + "<br>"; //RME
}

//This function will get the new nodelist from the server.
//ParentId is the taxId of the node that holds the list of children we want.
function reqHistogram(taxonId){
 var path = 'doHist.do';
 var params = {taxId: Number(taxonId)};
 var xhr = new XMLHttpRequest();
 xhr.open("POST", path);
 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  //Send the proper header info

 xhr.onreadystatechange = function() {//Call a function when the state changes (i.e. response comes back)
     // Update the dropdown when response is ready
     if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
         var nodeList = JSON.parse(this.responseText);
         histogram(nodeList["x"], nodeList["y"]);
     }
     else{
         console.log("Server Response: Error"); //RME
     }
 };

 var jsonString= JSON.stringify(params);     //generate JSON string
 xhr.send(jsonString);                       //send request to server
 // document.getElementById("console").innerHTML += "Sent request to " + path + ": "  + jsonString + "<br>"; //RME
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