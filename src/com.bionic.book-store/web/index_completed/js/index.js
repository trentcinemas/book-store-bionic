/**
 * Created by jsarafajr on 28.07.14.
 */
$(document).ready(function(){
    // set action on login form
    $('#login').submit(function() {
        var email = $('#email').val();
        var password = $('#password').val();

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
});

$('#navbarHeader').ready(function() {
    // If user is Sign In show logout
    $.ajax({
        type: 'get',
        url: '/rest/session/get-user',
        crossDomain: true,
        success: function (data) {
            if (data.name == null) { // if response doesn't have user
                loginButtonEnable();
            } else {
                logoutButtonEnable(data.name);
            }
        }
    });

    formReplyActionSet();
});

function logoutButtonEnable(name) {
    $('#navbarHeader').html(
            "<ul class='nav navbar-nav pull-right'>" +
                "<li class='active'><a href='index.html'>Головна</a></li>" +
                "<li><a href='#'>Усі книжки</a></li>" +
                "<li><a href='#'>Як придбати</a></li>" +
                "<li><a href='#'>Про видавництво</a></li>" +
                "<li><a href='#'>Кабінет</a></li>" +
                "<li><a href='#' id = 'logout_button'>Вийти</a></li>" +
            "</ul>" +
            "<div id='hellouser'>" + name + "</div>"
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

function loginButtonEnable() {
    $(('#navbarHeader')).html(
            "<ul class='nav navbar-nav pull-right'>" +
            "<li class='active'><a href='index.html'>Головна</a></li>" +
            "<li><a href='#'>Усі книжки</a></li>" +
            "<li><a href='#'>Як придбати</a></li>" +
            "<li><a href='#'>Про видавництво</a></li>" +
            "<li><a href='#' id = 'enter'>Увійти</a></li>" +
            "</ul>"
    );

    $("#enter").click(function(){
        $("#login").slideToggle("slow");
        $(this).toggleClass("active");
    });
}

function formReplyActionSet() {
    $('#reply_form').submit(function() {
        var email = $('#reply_email').val();
        var receiver = $('#reply_receiver').val();
        var text = $('#reply_text').val();

        $.ajax({
            type: 'post',
            url: '/rest/reply/send',
            crossDomain: true,
            data: {'email': email, 'receiver' : receiver, 'text' : text},
            error: function (data) {
                alert(data.error);
            }
        });
        return false;
    });
}

function searchActionSet() {

}