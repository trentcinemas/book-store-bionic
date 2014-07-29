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
$('.row').ready(function(){
  $.ajax({
        type: "get",
        url: "rest/book/list/5/0",
        crossDomain: true,
        dataType:"json",
        cache: false,
        success:function (data) {
            var i=0;
            if (data != null) {
            $('.new').each(function(){
                        $(this).html(" <div class = 'small-thubnail'><a href='#'><img src='rest/file/getimage/"+data[i].sm_cover+"' alt='100%x180'style='height: 203px; width: 142px; display: block;'></a></div>"+
                            "<div class = 'b-title'>"+
                            "<a href='#'><span class = 'title'>"+data[i].title+"</span></a> </div>"+
                            "<div class = 'b-author'>"+
                            "<a href = '#'><span class = 'author'>AUTHOR</span></a></div>"+
                            "<span class = 'price'>"+data[i].price+"<span>");
                i++
            })
             $('.popular').each(function() {
                         $(this).html(" <div class = 'small-thubnail'><a href='#'><img src='/rest/file/getimage/"+data[i].sm_cover+"' alt='100%x180'style='height: 203px; width: 142px; display: block;'></a></div>"+
                             "<div class = 'b-title'>"+
                             "<a href='#'><span class = 'title'>"+data[i].title+"</span></a> </div>"+
                             "<div class = 'b-author'>"+
                             "<a href = '#'><span class = 'author'>AUTHOR</span></a></div>"+
                             "<span class = 'price'>"+data[i].price+"<span>");
                i++;
             });
			}
        },
        error: function (data) {
            alert("Error");
        }
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