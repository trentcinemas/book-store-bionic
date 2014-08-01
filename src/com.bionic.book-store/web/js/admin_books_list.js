/**
 * Created by jsarafajr on 31.07.14.
 */
$(document).ready(function() {

    addAjaxLoader();

    $.ajax({
        type: 'get',
        url: '/rest/book/listAll',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id;
                $('#all_books_table').append(
                        "<tr id='book" + id + "'> " +
                            "<td>" + id + "</td>" +
                            "<td id='title"+id+"'>" + data[i].title + "</td>" +
                            "<td id='author"+id+"'>" + data[i].author + "</td>" +
                            "<td id='genre"+id+"'>" + data[i].genre + "</td>" +
                            "<td id='pages_cnt"+id+"'>" + data[i].pagesCnt + "</td>" +
                            "<td id='price"+id+"'>" + data[i].price + "</td>" +
                            "<td id='download_cnt"+id+"'>" + data[i].downloadCnt + "</td>" +
                            "<td id='view_cnt"+id+"'>" + data[i].viewCnt + "</td>" +
                            "<td id='add_date"+id+"'>" + data[i].date + "</td>" +
                            "<td id='user"+id+"'>" + data[i].user + "</td>" +
                            "<td id='button"+id+"'>" + "<button onclick='editUser(" + data[i].id + ")'>Edit</button>" + "</td>" +
                        "</tr>"
                );
            }
        }
    });

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