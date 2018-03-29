// Initialization
function init() {
    updateSci("pickAnimalia", {value:2});    // Propagate Dropdowns on Startup
}

function populateDropdown(childTagID, nodeList, subnode){
    var x, y, content="";
    document.getElementById("console").innerHTML += "Populating Dropdown with ... " + nodeList[subnode]+"<br>"; //TODO: Console
    for (var i in nodeList[subnode]["taxonName"]){
        x = nodeList[subnode]["taxonId"][i];
        y = nodeList[subnode]["taxonName"][i];
        content += '"<option value="' + x + '">' + y + '</option>';
    }
    document.getElementById(childTagID).innerHTML = content;
}

// This function will get the new nodelist from the server.
// ParentId is the taxId of the node that holds the list of children we want.
function getNodeList(parentId){
    return nodeList = {
        "order":{
            "taxonName":["o1n","o2n","o3n"],
            "taxonId":[6,7,8]
        },
        "phylum":{
            "taxonName":["p1n","p2n","p3n"],
            "taxonId":[3,4,5]
        },
        "class":{
            "taxonName":["c1n","c2n","c3m"],
            "taxonId":[9,10,11]
        },
        "family":{
            "taxonName":["f1n","f2n","f3n"],
            "taxonId":[12,13,14]
        },
        "genus":{
            "taxonName":["g1n","g2n","g3n"],
            "taxonId":[15,16,17]
        },
        "species":{
            "taxonName":["s1n","s2n","s4n"],
            "taxonId":[18,19,20]
        }
    };  //TODO replace Substitude Testing JSON object
}

function resolveAny(object){
    document.getElementById("console").innerHTML += "In | resolveAny()<br>"; //TODO: Console
    return 2;
}

function updateSci(tagID, object) {
    var taxID = object.value;

    if (taxID == 0){
        taxID = resolveAny(object);
    }

    var nodeList = getNodeList(taxID);   //TODO This is where you call Java

    document.getElementById("console").innerHTML += "In | updateSci()<br>"; //TODO: Console

    while (tagID != "END"){
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
                console.log("All dropdowns should be loaded");
                tagID = "END";
                break;

            default:    //TODO: Remove this eventually. Shouldnt be necessary anyways.
                document.getElementById("console").innerHTML += "Error: switch(tagID) failed<br>"; //TODO: Console
        }
    }
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

// Archive
// Updates a dropdown List
// tagID = tag of dropdown that changes
// taxID = id of parent of the thing that changes


// function updateSciR(tagID, object) {
//     var path = 'doBioLookup.do';
//     var taxID = Number(object.value);
//     // alert("Updating: " + tagID + "with children of: " + taxID);   //TODO Remove me
//     var params = {taxId: taxID};
//     var xhr = new XMLHttpRequest();
//     xhr.open("POST", path);
//
//     //Send the proper header information along with the request
//     xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//
//     xhr.onreadystatechange = function() {//Call a function when the state changes (i.e. response comes back)
//         // Update the dropdown
//         if(xhr.readyState == XMLHttpRequest.DONE && xhr.status == 200) {
//             document.getElementById("console").innerHTML += "Retrieved: "; //TODO: Console REMOVEME
//             document.getElementById("console").innerHTML += this.responseText + "<br>"; //TODO: Console REMOVEME
//             console.log("RETRIEVED:" + this.responseText);
//             var nodeList = JSON.parse(this.responseText);
//             document.getElementById("console").innerHTML += nodeList + "<br>"; //TODO: Console REMOVEME
//
//             var content = "", x, y;
//             for (var i in nodeList.taxonId){
//                 x = nodeList.taxonId[i];
//                 y = nodeList.taxonName[i];
//                 content += '"<option value="' + x + '">' + y + '</option>';
//             }
//
//             document.getElementById(tagID).innerHTML = content;
//         }
//         else{
//             document.getElementById("console").innerHTML += "Error<br>"; //TODO: Console REMOVEME
//         }
//     };
//
//     var jsonString= JSON.stringify(params);     //generate JSON string
//     xhr.send(jsonString);                       //send request to server
//     document.getElementById("console").innerHTML += "Sent request to " + path + ": "  + jsonString + "<br>"; //TODO: Console REMOVEME
// }