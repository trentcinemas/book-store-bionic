/**
 * Created by jsarafajr on 31.07.14.
 */
$(document).ready(function() {

    addAjaxLoader();

    $.ajax({
        type: 'get',
        url: '/rest/author/getAll',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id;
                $('#authors_table').append(
                        "<tr id='author" + id + "'> " +
                        "<td>" + id + "</td>" +
                        "<td id='firstname"+id+"'>" + data[i].firstName + "</td>" +
                        "<td id='lastname"+id+"'>" + data[i].lastName + "</td>" +
                        "<td id='description"+id+"'>" + data[i].description + "</td>" +
                        "<td id='button"+id+"'>" + "<button onclick='removeAuthor(" + data[i].id + ")'>Remove</button>" +
                            "<button onclick='edit(" + data[i].id + ")'>Edit</button></td>" +
                        "</tr>"
                );
            }
        }
    });

});

function removeAuthor(id) {

    $.ajax({
        type: 'post',
        url: '/rest/author/remove',
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