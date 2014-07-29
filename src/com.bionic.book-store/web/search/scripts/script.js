/**
 * Created by UFO on 7/29/14.
 */

$(document).ready(function() {

    $('#searchForm').submit(function() {
        var searchString = $('#searhString').val();


        $.ajax({
            type: 'post',
            url: 'rest/book/search',
            crossDomain: true,
            data: {'searchString': searchString},
            response: 'text', // response type
            success: function(data) {
                $('#status').html("Success");
            },
            error: function(data) {
                $('#status').html("Error");
            }

        });

        return false;
    });



});



