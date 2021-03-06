/**
 * Created by jsarafajr on 31.07.14.
 */
$(document).ready(function() {

    addAjaxLoader();

    $.ajax({
        type: 'get',
        url: '/rest/purchase/get-all',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id;
                $('#purchased_book_list').append(
                        "<tr id='purchased" + id + "'> " +
                        "<td>" + id + "</td>" +
                        "<td id='book_title"+id+"'>" + data[i].book_title + "</td>" +
                        "<td id='book_author"+id+"'>" + data[i].book_author + "</td>" +
                        "<td id='user"+id+"'>" + data[i].user + "</td>" +
                        "<td id='date"+id+"'>" + data[i].date + "</td>" +
                        "<td><button onclick='removePurchase(" + id + ")'>Remove</button></td>" +
                        "</tr>"
                );
            }
        }
    });

});

function removePurchase(id) {
    if (confirm("Видалити покупку?")) {
        $.ajax({
            type: 'post',
            url: '/rest/purchase/remove',
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