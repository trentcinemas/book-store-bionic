/**
 * Created by jsarafajr on 31.07.14.
 */
var order=true;
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
function fillTable(data) {
    $("#books_table").html("");
    for (var i = 0; i < data.length; i++) {
        var id = data[i].id;
        $('#all_genre_table').append(
                "<tr id='book" + id + "'> " +
                "<td>" + id + "</td>" +
                "<td id='title" + id + "'>" + data[i].type + "</td>" +
                "<td id='button" + id + "'>" + "<button onclick='editGenre(" + data[i].id + ")'>Edit</button>" + "</td>" +
                "<td><button onclick='removeGenre(" + data[i].id + ")'>Remove</button>" + "</td>" +
                "</tr>"
        );
    }
}
function getOrderedByNameOfGenre(){
    var url='/rest/genre/sort/'+order.toString();
    if(order==false) order=true;
    else {
        order=false;
    }
    $.ajax({
        type: 'get',
        url: url,
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        },
        error:function(){
            alert("error");
        }
    });
}

function editGenre(id) {
    var title = $('#title' + id).html();

    $('#title' + id).html(
            "<input type='text' value='" + title + "'>"
    );

    $('#button' + id).html(
            "<button onclick='applyEdit(" + id + ")'>Apply</button>"
    );
}

function applyEdit(id) {
    var title = $('#title' + id).children().val();

    $('#title' + id).html(title);

    $('#button' + id).html(
            "<button onclick='editGenre(" + id + ")'>Edit</button>"
    );


    // sending to Server
    $.ajax({
        type: 'post',
        url: '/rest/genre/update',
        crossDomain: true,
        data: {'id': id, 'title': title},
        success: function (data) {
            alert("Success");
            location.reload();
        }
    });

    return false;
}

function removeGenre(id) {
    if (confirm("Видалити жанр?")) {
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
}

$(document).ready(function () {
    addAjaxLoader();
    $.ajax({
        type: 'get',
        url: '/rest/genre/getAll',
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });

    $("th#type").click(function () {
        getOrderedByNameOfGenre();
    });

});


// Ajax activity indicator bound to ajax start/stop document events
$(document).ajaxStart(function () {
    $('#ajaxBusy').show();
}).ajaxStop(function () {
    $('#ajaxBusy').hide();
});