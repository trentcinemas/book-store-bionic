/**
 * Created by jsarafajr on 7/17/14.
 */

$(document).ready(function() {

    /* ------------  АВТОРИЗАЦИЯ -----------*/

    $('#login').submit(function() {
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

    /* -------------- РЕГИСТРАЦИЯ --------- */

    $('#registerForm').submit(function() {
        var email = $('#mail').val();
        var password = $('#password').val();
        var name=$("#name").val();
        if(password=$("#conf_pass").val()) {
            $.ajax({
                type: 'post',
                url: 'http://localhost:8080/rest/register',
                crossDomain: true,
                data: {'email': email, 'name': name, 'password': password},
                response: 'text', // response type
                success: function (data) {
                    $('#status').html("Success");
                },
                error: function (data) {
                    $('#status').html("Error");
                }

            });

            return false;
        }
        else alert("Паролі не співпадають");
        });
    $('#navbarHeader').
});