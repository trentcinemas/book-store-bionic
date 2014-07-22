/**
 * Created by jsarafajr on 7/17/14.
 */

$(document).ready(function() {

    $('#cookie_remove').click(function() {
        $.ajax({
            type: 'post',
            url: '/rest/logout',
            crossDomain: true,
            response: 'text', // response type
            success: function(data) {
                alert(data.responseText);
            },
            error: function(data) {
                alert(data.responseText);
            }
        });
    });

    $('#loginForm').submit(function() {
        var email = $('#email').val();
        var password = $('#password').val();

        $.ajax({
            type: 'post',
            url: '/rest/login',
            crossDomain: true,
            data: {'email': email, 'password' : password},
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
