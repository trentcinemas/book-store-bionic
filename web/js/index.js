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
            if (data.name != null) {
                logoutButtonEnable(data.name);
            }
        }
    });

    // Authorization
    $('#login_form').submit(function() {
        loginUser();
        return false;
    });
});



function loginUser() {
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
}

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

