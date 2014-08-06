/**
 * Created by jsarafajr on 7/17/14.
 */

$(document).ready(function() {
$("#registration_email").change(function(){
    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);

    if (!pattern.test($(this).val())) {
        $(this).css({ border:"2px solid red"});
        $(".email-fail").css({display:"block"});
    }
    else{
        $(this).css({ border:"1px solid #ccc"});
        $(".email-fail").css({display:"none"});
    }
});

    $("#registration_password").change(function(){

        if (($(this).val()!=$("#registration_conf_pass").val())) {
            $(this).css({ border:"2px solid red"});
            $("#registration_conf_pass").css({border:"2px solid red"});
            $(".password-fail").css({display:"block"});
        }
        else{
            $(this).css({ border:"1px solid #ccc"});
            $("#registration_conf_pass").css({ border:"1px solid #ccc"});
            $(".password-fail").css({display:"none"});
        }
    });
    $("#registration_conf_pass").change(function(){

        if (($(this).val()!=$("#registration_password").val())) {
            $(this).css({ border:"2px solid red"});
            $("#registration_conf_pass").css({border:"2px solid red"});
            $(".password-fail").css({display:"block"});
        }
        else{
            $(this).css({ border:"1px solid #ccc"});
            $("#registration_password").css({ border:"1px solid #ccc"});
            $(".password-fail").css({display:"none"});
        }
    });


    $('#regist_field').submit(function(e) {
        e.preventDefault();
        var email = $('#registration_email').val();
        var password = $('#registration_password').val();
        var name = $("#registration_name").val();
        if(password==$("#registration_conf_pass").val()) {
            $.ajax({
                type: 'post',
                url: '/rest/register',
                crossDomain: true,
                data: {'email': email, 'name': name, 'password': password},
                response: 'text', // response type
                success: function (data) {
                    $('#status').html("Success");
                    location.reload();
                },
                statusCode: {
                	// HTTP 409 - Conflict
                	409: function (data) {
                		$('#status').html(data.responseText);	
                	}
                }

            });

        } else {
            alert("Не правильні паролі");
        }
        return false;
    });

});

function addAuthorization() {
    $('#login_form').submit(function () {
        var email = $('#email_login').val();
        var password = $('#password_login').val();

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

$('#navbarHeader').ready(function () {
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


    addAuthorization();
    formReplyActionSet();

});

function logoutButtonEnable(name) {
    $('#navbarHeader').html(
            "<ul class='nav navbar-nav pull-right'>" +
            "<li><a href='/'>Головна</a></li>" +
            "<li><a href='/allbooks.html'>Усі книжки</a></li>" +
            "<li><a href='/how2buy.html'>Як придбати</a></li>" +
            "<li><a href='/about.html'>Про видавництво</a></li>" +
            "<li><a href='/user-cabinet.html'>Кабінет</a></li>" +
            "<li><a href='javascript:void(0)' id = 'logout_button'>Вийти</a></li>" +
            "</ul>" +
            "<div id='hellouser'>Доброго дня, " + name + "</div>"
    );

    // set on click action on logout button
    $('#logout_button').click(function () {
        $.ajax({
            type: 'post',
            url: '/rest/session/logout',
            crossDomain: true,
            success: function (data) {
                location.reload();
            }
        });
    });
}

function loginButtonEnable() {
    $(('#navbarHeader')).html(
            "<ul class='nav navbar-nav pull-right'>" +
            "<li><a href='/'>Головна</a></li>" +
            "<li><a href='/allbooks.html'>Усі книжки</a></li>" +
            "<li><a href='/how2buy.html'>Як придбати</a></li>" +
            "<li><a href='/about.html'>Про видавництво</a></li>" +
            "<li><a href='javascript:void(0)' id = 'enter'>Увійти</a></li>" +
            "</ul>"
    );

    $("#enter").click(function () {
        $("#login_form").slideToggle("slow");
        $(this).toggleClass("active");
    });
}

function formReplyActionSet() {
    $('#reply_form').submit(function () {
        var email = $('#reply_email').val();
        var receiver = $('#reply_receiver').val();
        var text = $('#reply_text').val();

        $.ajax({
            type: 'post',
            url: '/rest/reply/send',
            crossDomain: true,
            data: {'email': email, 'receiver': receiver, 'text': text},
            success: function () {
                location.reload();
            }
        });
        return false;
    });
}
