function init() {
    initPhylum();
}

// Returns list of animalia children == all phylums
function initPhylum(){
    // alert("initPhylum function");
    var parentId = 2;   //Animalia AphiaID = 2

    // TODO Request
    var nodeList;
    // nodeList = {
    //     "taxonId":[1065,1821,51],
    //     "taxonName":["Arthropoda", "Chordata", "Mollusca"]
    // };
    nodeList = {
        "taxonId":[1821,51,1065],
        "taxonName":["Chordata","Mollusca","Arthropoda"]
    };

    var content = "", x, y;

    for (i in nodeList.taxonId){
        x = nodeList.taxonId[i];
        y = nodeList.taxonName[i];
        content += '"<option value="' + x + '">' + y + '</option>';
    }

    document.getElementById("pickPhylum").innerHTML = content;

    // {taxid: intID}

}

function changedSciR(rank){
    // var changedRank = document.getElementsByName()
}



// For Range Slider
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