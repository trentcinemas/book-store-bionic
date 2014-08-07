/**
 * Created by Джон on 31.07.2014.
 */
var currentBook;

$(document).ready(function(){
    var URL = window.location.search;
    var getRequest=URL.split("?")[1];
    id=getRequest.split("=")[1];
//    $("#book").val(id);
  $.ajax({
       type:"get",
       dataType:"json",
       url:"/rest/book/get/"+id,
       success:function(data){
        /*   if(data.newb==true){
               $('.b-badge').css("display","block");
           }*/
            currentBook = data;
            $("#athor").html(data.author);
            $("#athor").attr("href","/rest/author/getAuthPage/"+data.athorid);
            $("#title").html(data.title);
            $("#description").html(data.description);
            $("#price").html(data.price + " грн");
            $("#big-cover").attr("src","/rest/file/getbigimage/"+data.id);
       }

    });

    getComments(id);



    $('.b-buy').ready(function(){
        $(".b-buy").click(function(){
            $(".b-form").slideToggle("slow");
            $(this).toggleClass("active");
        });
    });


    addAuthorization();
    formReplyActionSet();
    addBuyBookAction();
    popupReviewWindow();

});


function addBuyBookAction() {
    $('#buy_book_form').submit(function() {
        $.ajax({
            type: 'post',
            url: '/rest/book/buy',
            crossDomain: true,
            data: {'id' : id},
            success: function (data) {
                location.href = '/thnkstocustomer.html';
            },
            statusCode: {
                // HTTP 401 - unauthorized
                401: function (data) {
                    alert("Ви не зареєстровані");
                }
            }
        });
        return false;
    });
}


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
                sendCommentsDisable();
            } else {
                logoutButtonEnable(data.name);
                sendCommentsEnabe();
                $("#add_comment_form").submit(function(e){
                    e.preventDefault();
                    var comment = $("#comment").val();

                    addComment(id,comment);
                });
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

function sendCommentsEnabe() {
    $('#send_comment_div').html(
            "<h3 class='page-header'>Додати коментар</h3>" +
            "<form role='form' id='add_comment_form' method='post'>" +
            "<input type='hidden' id='book' name='book'>" +
            "<div class='form-group'>" +
                "<label for='comment'>Коментар</label>" +
                "<input type='text' class='form-control' id='comment' name='comment'>" +
                "</div>" +
                "<button type='submit' class='btn btn-default' id='submit'>Зберегти</button>" +
            "</form>"
    );
}

function sendCommentsDisable() {
    $('#send_comment_div').html("");
}

    function addComment(id,comment) {
//        $("#add_comment_form").submit(function () {

//            var URL = window.location.search;
//            var getRequest = URL.split("?")[1];


            $.ajax({
                type: "post",
                url: "/rest/comment/addComment",
                data: {
                    book: id,
                    comment: comment
                },
                success: function (data) {
                    alert("added comment");
                    location.reload();
                },
                error: function (data) {
                    alert(data);
                }

            });
//        });
    }

        function getComments(id) {
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



/* Pop Up window "REVIEW" */

function popupReviewWindow() {
    $('body').append('<div class="popup-box" id="popup-box-1">' +
        '<div class="top">' +
        "<span id='popup_title' style='font-weight: bold; width: 100%'></span><br>" +
        "<embed src='/rest/file/getreviewfile/" + id + "' width='100%' height='"+ ($(window).height() / 1.3) + "'>" +
        '</div>' +
        '</div>');
    $('body').append('<div id="blackout"></div>');

    boxWidth = $(window).width() / 1.8;
    console.log(boxWidth);

    $(window).resize(centerBox);
    $(window).scroll(centerBox);
    centerBox();

    $('[class*=popup-link]').click(function(e) {

        /* Prevent default actions */
        e.preventDefault();
        e.stopPropagation();

        /* Set popup title */
        $('#popup_title').html($('#title').html());

        /* Get the id (the number appended to the end of the classes) */
        var name = $(this).attr('class');
        var id = name[name.length - 1];
        var scrollPos = $(window).scrollTop();

        /* Show the correct popup box, show the blackout and disable scrolling */
        $('#popup-box-'+id).show();
        $('#blackout').show();
        $('html,body').css('overflow', 'hidden');

        /* Fixes a bug in Firefox */
        $('html').scrollTop(scrollPos);
    });
    $('[class*=popup-box]').click(function(e) {
        /* Stop the link working normally on click if it's linked to a popup */
        e.stopPropagation();
    });
    $('html').click(function() {
        var scrollPos = $(window).scrollTop();
        /* Hide the popup and blackout when clicking outside the popup */
        $('[id^=popup-box-]').hide();
        $('#blackout').hide();
        $("html,body").css("overflow","auto");
        $('html').scrollTop(scrollPos);
    });
    $('.close').click(function() {
        var scrollPos = $(window).scrollTop();
        /* Similarly, hide the popup and blackout when the user clicks close */
        $('[id^=popup-box-]').hide();
        $('#blackout').hide();
        $("html,body").css("overflow","auto");
        $('html').scrollTop(scrollPos);
    });
}

function centerBox() {

    /* Preliminary information */
    var winWidth = $(window).width();
    var winHeight = $(document).height();
    var scrollPos = $(window).scrollTop();
    /* auto scroll bug */

    /* Calculate positions */

    var disWidth = (winWidth - boxWidth) / 2;
    var disHeight = scrollPos + 50;

    /* Move stuff about */
    $('.popup-box').css({'width' : boxWidth+'px', 'left' : disWidth+'px', 'top' : disHeight+'px'});
    $('#blackout').css({'width' : winWidth+'px', 'height' : winHeight+'px'});

    return false;
}
