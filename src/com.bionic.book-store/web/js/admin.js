/**
 * Created by jsarafajr on 31.07.14.
 */
$(document).ready(function() {

    addAjaxLoader();
    showReplyMessages();
    showAddedBooks();
    showLastUsers();

});

function showReplyMessages() {
    $.ajax({
        type: 'get',
        url: '/rest/reply/getLast',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id;
                $('#reply_messages').append(
                        "<tr id='book" + id + "'> " +
                        "<td>" + id + "</td>" +
                        "<td id='email"+id+"'>" + data[i].email + "</td>" +
                        "<td id='receiver"+id+"'>" + data[i].receiver + "</td>" +
                        "<td id='text"+id+"'>" + data[i].text + "</td>" +
                        "<td id='date"+id+"'>" + data[i].date + "</td>" +
                        "</tr>"
                );
            }
        }
    });
}

function showAddedBooks() {
    $.ajax({
        type: 'get',
        url: '/rest/book/list/10/1',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id;
                $('#last_books').append(
                        "<tr id='book" + id + "'> " +
                        "<td>" + id + "</td>" +
                        "<td id='title"+id+"'>" + data[i].title + "</td>" +
                        "<td id='receiver"+id+"'>" + data[i].author + "</td>" +
                        "<td id='genre"+id+"'>" + data[i].user + "</td>" +
                        "<td id='pages_cnt"+id+"'>" + data[i].date + "</td>" +
                        "</tr>"
                );
            }
        }
    });
}

function showLastUsers() {
    $.ajax({
        type: 'get',
        url: '/rest/user/getLast',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id;
                $('#last_users').append(
                        "<tr id='book" + id + "'> " +
                        "<td>" + id + "</td>" +
                        "<td id='name"+id+"'>" + data[i].name + "</td>" +
                        "<td id='email"+id+"'>" + data[i].email + "</td>" +
                        "<td id='group"+id+"'>" + data[i].group + "</td>" +
                        "<td id='pages_cnt"+id+"'>" + "2014.08.01 12:00" + "</td>" +
                        "</tr>"
                );
            }
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