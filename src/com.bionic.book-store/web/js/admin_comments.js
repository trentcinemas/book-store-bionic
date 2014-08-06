/**
 * Created by jsarafajr on 31.07.14.
 */

var order_title=true;
var order_email=true;
var order_date=true;
var current_page=1;
function addAjaxLoader() {
    // Setup the ajax indicator
    $('#ajax_loading_div').append('<div id="ajaxBusy"><img src="/images/ajax-loader.gif"></div>');

    $('#ajaxBusy').css({
        display: "none",
        paddingLeft: "40%",
        paddingTop: "0px",
        paddingBottom: "0px",
        position: "relative",
        right: "3px",
        top: "3px",
        width: "auto"
    });
}

// функція яка заповняє список сторінок
function fillPagination(data){
    var count = parseInt(data)/15;
    $("#list-nav").append("<li class='disabled prev'><a>«</a></li>");
    for(var i=1;i<=count+1;i++){
        if(i==current_page)
            $("#list-nav").append("<li value='"+i+"' class='active'><a>"+i+"</a></li>");
        else
            $("#list-nav").append("<li value='"+i+"'><a>"+i+"</a></li>");

    }
    if(count<1)
        $("#list-nav").append("<li class='disabled next'><a>»</a>")
    else
        $("#list-nav").append("<li class='next'><a>»</a>")

}
function fillTable(data) {

    for (var i = 0; i < data.length; i++) {
        var id = data[i].id;
        $('#comments_table').append(
                "<tr id='comments" + id + "'> " +
                "<td>" + id + "</td>" +
                "<td id='user" + id + "'>" + data[i].user + "</td>" +
                "<td id='book" + id + "'>" + data[i].book + "</td>" +
                "<td id='comment_date" + id + "'>" + data[i].date + "</td>" +
                "<td id='comment" + id + "'>" + data[i].comm_desc + "</td>" +
                "</tr>"
        );
    }
};
function getCommentsFromPage(page){
    $.ajax({
        type: 'get',
        url: '/rest/comment/getFromPage/' + page,
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });
}
function showComments(){
    $.ajax({
        type: 'get',
        url: '/rest/comment/getPageCount',
        crossDomain: true,
        cache: false,
        success: function (data) {
            fillPagination(data);
            getCommentsFromPage(current_page);
        }
    });
}
function getOrderedByEmail(){
    var url='/rest/comment/sort/'+current_page+"/email/"+order_email.toString();
    if(order_email==false) order_email=true;
    else {
        order_email=false;
    }
    $.ajax({
        type: 'get',
        url: url,
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });
}
function getOrderedByTitle(){
    var url='/rest/comment/sort/'+current_page+"/title/"+order_title.toString();
    if(order_title==false) order_title=true;
    else {
        order_title=false;
    }
    $.ajax({
        type: 'get',
        url: url,
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });
}
function getOrderedByDate(){
    var url='/rest/comment/sort/'+current_page+"/date/"+order_date.toString();
    if(order_date==false) order_date=true;
    else {
        order_date=false;
    }
    $.ajax({
        type: 'get',
        url: url,
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });
}
$(document).ready(function() {

    addAjaxLoader();
    showComments();

    $('.pagination').on('click', 'li', function () {

        if($(this).hasClass("disabled")){
            return false;
        }

        if($(this).hasClass("prev")|| $(this).hasClass("next")){
            if($(this).hasClass("prev")){
                $(".pagination .active").addClass("temp");
                $(".pagination .active").prev().addClass("active")
                $(".pagination .temp").removeClass("active");
                $(".pagination .temp").removeClass("temp");
                current_page--;
            }
            else
            if($(this).hasClass("next")){
                $(".pagination .active").addClass("temp");
                $(".pagination .active").next().addClass("active")
                $(".pagination .temp").removeClass("active");
                $(".pagination .temp").removeClass("temp");
                current_page++;
            }

        }
        else{
            current_page=$(this).val();
            $(this).addClass('active').siblings().removeClass('active');
        }

        if(current_page-1==0)
            $(".pagination .prev").addClass("disabled");
        else
            $(".pagination .prev").removeClass("disabled");

        if($(".pagination .active").next().hasClass("next"))
            $(".pagination .next").addClass("disabled");
        else
            $(".pagination .next").removeClass("disabled");

        getCommentsFromPage($(".pagination .active").val());
    });



    $("#sortemail").click(function(){
        getOrderedByEmail();
    });
    $("#sorttitle").click(function(){
        getOrderedByTitle();
    });
    $("#sortdate").click(function(){
        getOrderedByDate();
    });
// end to change
});

    // Ajax activity indicator bound to ajax start/stop document events
    $(document).ajaxStart(function(){
        $('#ajaxBusy').show();
    }).ajaxStop(function(){
        $('#ajaxBusy').hide();
    });