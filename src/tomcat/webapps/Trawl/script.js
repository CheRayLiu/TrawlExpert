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