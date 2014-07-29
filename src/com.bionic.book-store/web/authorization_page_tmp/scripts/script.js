/**
 * Created by jsarafajr on 7/17/14.
 */

$(document).ready(function() {

    $('#loginForm').submit(function() {
        var email = $('#email').val();
        var password = $('#password').val();

        $.ajax({
            type: 'post',
            url: 'http://localhost:8080/rest/authorize',
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
