/**
 * Created by jsarafajr on 23.07.14.
 */

$(document).ready(function() {

    $.ajax({
        type: 'get',
        url: '/rest/session/get-user',
        crossDomain: true,
        response: 'json', // response type
        success: function(data) {

        },
        error: function(data) {
            alert(data.responseText);
        }
    });

});
