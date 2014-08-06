/**
 * Created by jsarafajr on 31.07.14.
 */
var order_title=true;
var order_author=true;
var order_pagecount=true;
var order_price=true;
var order_downcount=true;
var order_reviewcount=true;
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

function fillTable(data){
    $("#books_table").html("");
    for (var i = 0; i < data.length; i++) {
        var id = data[i].id;
        $('#all_books_table').append(
                "<tr id='book" + id + "'> " +
                "<td>" + id + "</td>" +
                "<td id='title"+id+"'>" + data[i].title + "</td>" +
                "<td id='author"+id+"'>" + data[i].author + "</td>" +
                "<td id='genre"+id+"'>" + data[i].genre + "</td>" +
                "<td id='pages_cnt"+id+"'>" + data[i].pagesCnt + "</td>" +
                "<td id='price"+id+"'>" + data[i].price + " грн</td>" +
                "<td id='download_cnt"+id+"'>" + data[i].downloadCnt + "</td>" +
                "<td id='view_cnt"+id+"'>" + data[i].viewCnt + "</td>" +
                "<td id='add_date"+id+"'>" + data[i].date + "</td>" +
                "<td id='user"+id+"'>" + data[i].user + "</td>" +
                "<td>" +
                "<a href=/rest/admin/editbook?id=" + data[i].id + "><button>Edit</button></a>" + "</td>" +
                "<td><button onclick='removeBook(" + data[i].id + ")'>Remove</button></td>" +
                "</tr>"
        );
    }

}
// функція яка виводить таблицю список сторінок та книжок
function showBooks(){
    $.ajax({
        type: 'get',
        url: '/rest/book/getPageCount',
        crossDomain: true,
        cache: false,
        success: function (data) {
            fillPagination(data);
            getBooksFromPage(current_page);
        }

    });
}
// функція яка виводить авторів з певної сторінки
function getBooksFromPage(page){
    $.ajax({
        type: 'get',
        url: '/rest/book/getFromPage/' + page,
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });
}

function getOrderedByTitle(){
    var url='/rest/book/sort/'+current_page+"/title/"+order_title.toString();

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
function getOrderedByAuthor(){
    var url='/rest/book/sort/'+current_page+"/author/"+order_author.toString();
    if(order_author==false) order_author=true;
    else {
        order_author=false;
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
function getOrderedByPageCount(){
    var url='/rest/book/sort/'+current_page+"/page_count/"+order_pagecount.toString();
    if(order_pagecount==false) order_pagecount=true;
    else {
        order_pagecount=false;
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
function getOrderedByPrice(){
    var url='/rest/book/sort/'+current_page+"/price/"+order_price.toString();
    if(order_price==false) order_price=true;
    else {
        order_price=false;
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
function getOrderedByDownloadCount(){
    var url='/rest/book/sort/'+current_page+"/downloads_count/"+order_downcount.toString();
    if(order_downcount==false) order_downcount=true;
    else {
        order_downcount=false;
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
function getOrderedByReviewCount(){
    var url='/rest/book/sort/'+current_page+"/review_count/"+order_reviewcount.toString();
    if(order_reviewcount==false) order_reviewcount=true;
    else {
        order_reviewcount=false;
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
    var url='/rest/book/sort/'+current_page+"/date/"+order_date.toString();
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
function search(searchstring){
    $.ajax({
        type:"get",
        url:"/rest/book/search/"+searchstring,
        success:function(data){
            fillTable(data);
        }
    });
}

function removeBook(id) {
    if (confirm("Видалити книгу?")) {
        $.ajax({
            type: 'post',
            url: '/rest/book/remove',
            data: {'id': id},
            crossDomain: true,
            success: function (data) {
                alert("Removed");
                location.reload();
            }
        });
    }
}




$(document).ready(function() {

    
    addAjaxLoader();
    showBooks();

    $('.pagination').on('click','li', function(){
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

        getBooksFromPage($(".pagination .active"))
    });


    $("th#title").click(function(){
    getOrderedByTitle()
    });

    $("th#author").click(function(){
    getOrderedByAuthor();
    });

    $("th#pagecount").click(function(){
    getOrderedByPageCount();
    });

    $("th#price").click(function(){
    getOrderedByPrice();
    });

    $("th#downcount").click(function(){
    getOrderedByDownloadCount();
    });
    $("th#reviewcount").click(function(){
    getOrderedByReviewCount();
    });
    $("th#date").click(function(){
    getOrderedByDate();
    });

    $("#searchFrom").submit(function(e){
        e.preventDefault();
        search($("#search").val());
    });

});







// Ajax activity indicator bound to ajax start/stop document events
$(document).ajaxStart(function(){
    $('#ajaxBusy').show();
}).ajaxStop(function(){
    $('#ajaxBusy').hide();
});