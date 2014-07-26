/**
 * Created by jsarafajr on 25.07.14.
 */

$(document).ready(function() {
    // If user is Sign In show logout
    $.ajax({
        type: 'get',
        url: '/rest/session/get-user',
        crossDomain: true,
        success: function (data) {
            if (data.name != null) { // if response doesn't have user
                logoutButtonEnable(data.name);
            } else {
                loginFormEnable();
            }
        }
    });


});


function logoutButtonEnable(name) {
    $('#authorization').html(
        "<span id='user_name_logined'>Hello, " + name + "!</span><br>" +
        "<input type='button' id='logout_button' value='Вийти'>"
    );
    // set on click action on logout button
    $('#logout_button').click(function() {
        $.ajax({
            type: 'post',
            url: '/rest/session/logout',
            crossDomain: true,
            success: function (data) {
                location.reload();
            },
            error: function (data) {
                alert("You are not signed in!")
            }
        });
    });
}

function loginFormEnable() {
    $('#authorization').html(
            "<form id='login_form' method='post'>" +
            "<label id='login_message'></label>" +
            "<br>" +
            "<label>e-mail : </label><input type='text' id='login_email'>" +
            "<label>password : </label><input type='text' id='login_password'>" +
            "<input type='submit'>" +
            "</form>"
    );
    // set action on login form
    $('#login_form').submit(function() {
        var email = $('#login_email').val();
        var password = $('#login_password').val();

        $.ajax({
            type: 'post',
            url: '/rest/session/login',
            crossDomain: true,
            data: {'email': email, 'password': password},
            error: function (data) {
                $('#login_message').html(data.responseText);
            },
            statusCode: {
                // HTTP 307 - redirect
                307: function (data) {
                    document.location.href = data.responseText;
                }
            }
        });
        return false;
    });
}

