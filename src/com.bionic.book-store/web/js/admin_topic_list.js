/**
 * Created by jsarafajr on 31.07.14.
 */
$(document).ready(function() {

    addAjaxLoader();

    $.ajax({
        type: 'get',
        url: '/rest/genre/getAll',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id;
                $('#all_genre_table').append(
                        "<tr id='book" + id + "'> " +
                        "<td>" + id + "</td>" +
                        "<td id='title"+id+"'>" + data[i].type + "</td>" +
                        "<td id='button"+id+"'>" + "<button onclick='removeGenre(" + data[i].id + ")'>Remove</button>" + "</td>" +
                        "</tr>"
                );
            }
        }
    });

});

function removeGenre(id) {
    var lol;

    $.ajax({
        type: 'post',
        url: '/rest/genre/remove',
        data: {'id': id},
        crossDomain: true,
        success: function (data) {
            alert("Removed");
            location.reload();
        }
    });
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