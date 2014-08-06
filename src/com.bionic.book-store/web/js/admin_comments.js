/**
 * Created by jsarafajr on 31.07.14.
 */

var order_name=true;
var order_email=true;
var order_group=true;
var current_page=1;

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

$(document).ready(function() {

    addAjaxLoader();

    $.ajax({
        type: 'get',
        url: '/rest/comment/allcomments/1',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });



function fillPagination(data){
    var count = parseInt(data)/15;
    for(var i=0;i<count;i++){
        if(i==0)
            $("#list-nav").append("<li value='"+(i+1)+"' class='active'><a>"+(i+1)+"<span class='sr-only'>(current)</span></a></li>");
        else
            $("#list-nav").append("<li value='"+(i+1)+"'><a>"+(i+1)+"</a></li>");
    }
}

    addAjaxLoader();
    $.ajax({
        type:'get',
        url:'/rest/user/getPageCount',
        success: function (data) {
            fillPagination(data);
            $.ajax({
                type: 'get',
                url: '/rest/user/getPage/1',
                crossDomain: true,
                dataType: "json",
                cache: false,
                success: function (data) {
                    fillTable(data);
                }
            });

        }
    });

    $('.pagination').on('click','li', function(){
        $(this).addClass('active').siblings().removeClass('active');
        current_page=$(this).val();
        $.ajax({
            type: 'get',
            url: '/rest/user/getPage/'+current_page,
            crossDomain: true,
            dataType: "json",
            cache: false,
            success: function (data) {
                fillTable(data);
            }
        });


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

    $("#sortUser").click(function(){

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
    });
// end to change
});

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