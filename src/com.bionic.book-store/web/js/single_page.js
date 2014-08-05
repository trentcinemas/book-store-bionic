/**
 * Created by Джон on 31.07.2014.
 */
$(document).ready(function(){
    var URL = window.location.search;
    var getRequest=URL.split("?")[1];
    var id=getRequest.split("=")[1];
    $("#book").val(id);
  $.ajax({
       type:"get",
       dataType:"json",
       url:"/rest/book/get/"+id,
       success:function(data){
           if(data.newb==true){
               $('.b-badge').css("display","block");
           }

            $("#athor").html(data.author);
            $("#title").html(data.title);
            $("#description").html(data.description);
            $("#price").html(data.price + " грн");
            $("#big-cover").attr("src","/rest/file/getimage/"+data.big_cover);
       }
    });

    getComments();

    $("#add_comment_form").submit(function(){
        var comment = $("#comment").val();
        var book =$("#book").val();
        addComment(book,comment);
    });

    $('.b-buy').ready(function(){
        $(".b-buy").click(function(){
            $(".b-form").slideToggle("slow");
            $(this).toggleClass("active");
        });
    });


    addAuthorization();
    formReplyActionSet();

});


function addAuthorization() {
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
}

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
    $('#logout_button').click(function() {
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

    $("#enter").click(function(){
        $("#login").slideToggle("slow");
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

    function addComment(book,comm_desc)
    {
//        $("#add_comment_form").submit(function () {

            var URL = window.location.search;
            var getRequest = URL.split("?")[1];


            $.ajax({
                type: "post",
                url: "rest/comment/addComment",
                data: {
                    book: book,
                    comm_desc: comm_desc
                },
                success: function (data) {
                    alert("added comment");
                },
                error: function (data) {
                    alert(data);
                }

            });
//        });
    }

        function getComments() {
//            var URL = window.location.search;
//            var getRequest = URL.split("?")[1];
//            var id = getRequest.split("=")[1];
            $.ajax({
                type: "get",
                dataType: "json",
                url: "/rest/comment/forBook/" + id,
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        /*   $("#user").html(data[i].user);
                         $("#date").html(data[i].date);
                         $("#comm_desc").html(data[i].comm_desc);*/
                        $(".comments").append("<div>" +
                            "<h3 class='comm_desc'>" + data[i].comm_desc + "</h3>" +
                            "<h4 class='date'>" + data[i].date + "</h4>" +
                            "<a class='user'>" + data[i].user + "</a>" +
                            "</div>")

                    }
                }
            });
        }




