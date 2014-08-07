$(document).ready(function(){
    addAjaxLoader();
    formReplyActionSet();
    addAuthorization()

   var searchstring=window.location.search.split("?search=")[1];
    var arr=searchstring.split("+");
    searchstring=arr[0];
    for(var i=1;i<arr.length;i++)
    searchstring+=" "+arr[i];
    $.ajax({
       type:"get",
       url:"/rest/book/search/"+searchstring,
       dataType:"json",
       success:function(data) {
           $(".search-result").html("");
           if (data.length == 0) {
               $(".search-result").html("<b>Нічого не знайдено</b>");
           }
           for (var i = 0; i < data.length; i++) {
               $(".search-result").append("<li>" + "<a href='/rest/book/getPage/"+data[i].id+"'><span class = 'title'>" + data[i].title + "</span></a>"+ "<br>" + "<span class = 'author'>"+data[i].author +"</span>"+ "<br>" +
                   "<a href='/rest/book/getPage/" + data[i].id+"'>"+
                   "<img width='150px' height='200px' src='/rest/file/getsmallimage/" +
                   data[i].id + "'></a><br><span class = 'price'>" + data[i].price + " грн.</span>" + "<li>")


           }
       },
        error:function(data) {
            $(".search-result").html("Нічого не знайдено");
        }
    });
});

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

});

function logoutButtonEnable(name) {
    $('#navbarHeader').html(
            "<ul class='nav navbar-nav pull-right'>" +
            "<li class='active'><a href='/'>Головна</a></li>" +
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
            "<li class='active'><a href='/'>Головна</a></li>" +
            "<li><a href='/allbooks.html'>Усі книжки</a></li>" +
            "<li><a href='/how2buy.html'>Як придбати</a></li>" +
            "<li><a href='/about.html'>Про видавництво</a></li>" +
            "<li><a href='javascript:void(0)' id = 'enter'>Увійти</a></li>" +
            "</ul>"
    );

    $("#enter").click(function () {
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
                location.href = '/thnksforfeedback.html'
            }
        });
        return false;
    });
}

function addAuthorization() {
    $('#login').submit(function () {
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

function addAjaxLoader() {
    // Setup the ajax indicator
    $('#ajax_loading_div').append('<div class="ajaxBusy"><img src="/images/ajax-loader_circle.gif"></div>');

    $('.ajaxBusy').css({
        display: "none",
        paddingLeft: "45%",
        paddingTop: "0px",
        paddingBottom: "0px",
        position: "relative",
        right: "3px",
        top: "3px",
        width: "auto"
    });
}

// Ajax activity indicator bound to ajax start/stop document events
$(document).ajaxStart(function () {
    $('.ajaxBusy').show();
}).ajaxStop(function () {
    $('.ajaxBusy').hide();
});
