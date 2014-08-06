/**
 * Created by jsarafajr on 31.07.14.
 */
var order_title=true;
var order_author=true;
var order_pagecount=true;
var order_price=true;
var order_downcount=true;
var order_rewiewcount=true;
var order_date=true;
var current_page=1;


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
                "<a href=editbook-admin.html?id=" + data[i].id + "><button>Edit</button></a>" + "</td>" +
                "<td><button onclick='removeBook(" + data[i].id + ")'>Remove</button></td>" +
                "</tr>"
        );
    }

}

function fillPagination(data){
    var count = parseInt(data)/15;
    for(var i=0;i<count;i++){
        if(i==0)
            $("#list-nav").append("<li value='"+(i+1)+"' class='active'><a>"+(i+1)+"<span class='sr-only'>(current)</span></a></li>");
        else
            $("#list-nav").append("<li value='"+(i+1)+"'><a>"+(i+1)+"</a></li>");
    }
}


$(document).ready(function() {

    
    addAjaxLoader();

    $.ajax({
        type:'get',
        url: '/rest/book/getPageCount',
        crossDomain:true,
        cache: false,
        success: function (data) {
         fillPagination(data);
            $.ajax({
                type: 'get',
                url: '/rest/book/getFromPage/'+current_page,
                crossDomain: true,
                dataType:"json",
                cache: false,
                success: function (data) {
                    fillTable(data)
                }
            });

        }
    })  ;

// сторінки

    $('.pagination').on('click','li', function(){
        $(this).addClass('active').siblings().removeClass('active');
        current_page=$(this).val();
        $.ajax({
            type: 'get',
            url: '/rest/user/getFromPage/'+current_page,
            crossDomain: true,
            dataType: "json",
            cache: false,
            success: function (data) {
                fillTable(data);
            }
        });
    });


    $("th#title").click(function(){
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
});

    $("th#author").click(function(){
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
    });

    $("th#pagecount").click(function(){
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
    });

    $("th#price").click(function(){
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
    });

    $("th#downcount").click(function(){
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
    });
    $("th#reviewcount").click(function(){
        var url='/rest/book/sort/'+current_page+"/review_count/"+order_rewiewcount.toString();
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
    });
    $("th#date").click(function(){
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
    });

    $("#searchFrom").submit(function(e){
        e.preventDefault();
        var searchstring=$("#search").val();
        $.ajax({
            type:"get",
            url:"/rest/book/search/"+searchstring,
            success:function(data){
                fillTable(data);
            }
        });
    });

});



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

// Ajax activity indicator bound to ajax start/stop document events
$(document).ajaxStart(function(){
    $('#ajaxBusy').show();
}).ajaxStop(function(){
    $('#ajaxBusy').hide();
});