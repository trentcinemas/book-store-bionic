/**
 * Created by jsarafajr on 7/17/14.
 */

$(document).ready(function() {

    $('#logout').click(function() {
        $.ajax({
            type: 'post',
            url: '/rest/session/logout',
            crossDomain: true,
            response: 'text', // response type
            success: function(data) {
                $('status').html("Log outed");
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
            url: '/rest/session/login',
            crossDomain: true,
            data: {'email': email, 'password' : password},
            response: 'text', // response type
            success: function(data) {
                $('#status').html("Success");
            },
            error: function(data) {
                $('#status').html(data.responseText);
            },
            statusCode: {
                307: function(data) {
                    document.location.href = data.responseText;
                }
            }


        });

        return false;
    });

});
