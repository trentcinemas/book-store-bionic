/**
 * Created by jsarafajr on 23.07.14.
 */

$(document).ready(function() {

    $.ajax({
        type: 'get',
        url: '/rest/session/get-user',
        response: 'json', // response type
        success: function(data) {
            setUser(data.name, data.email);
        },
        error: function(data) {
            alert(data.responseText);
        }
    });

});

function setUser(userName, userEmail) {
    $('#user_email').html(userEmail);
    $('#user_name').html(userName);
}