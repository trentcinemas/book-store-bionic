/**
 * Created by jsarafajr on 27.07.14.
 */
$(document).ready(function() {
    addAjaxLoader();

    $.ajax({
        type: 'get',
        url: '/rest/user/getAll',
        crossDomain: true,
        dataType:"json",
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                var id = data[i].id;
                $('#users_table').append(
                        "<tr id='user" + id + "'> " +
                            "<td>" + id + "</td>" +
                            "<td id='email"+id+"'>" + data[i].email + "</td>" +
                            "<td id='name"+id+"'>" + data[i].name + "</td>" +
                            "<td id='group"+id+"'>" + data[i].group + "</td>" +
                            "<td id='button"+id+"'>" + "<button onclick='editUser(" + data[i].id + ")'>Edit</button>" + "</td>" +
                        "</tr>"
                );
            }
        }
    });

});

function editUser(id) {
    var email = $('#email' + id).html();
    var name = $('#name' + id).html();
    var group = $('#group' + id).html();

    $('#email' + id).html(
        "<input type='text' value='" + email + "'>"
    );

    $('#name' + id).html(
            "<input type='text' value='" + name + "'>"
    );

    $('#group' + id).html(
        "<select id='" + "select_group" + id + "'>" +
            "<option>Користувач</option>" +
            "<option>Робітник</option>" +
            "<option>Модератор</option>" +
            "<option>Адміністратор</option>" +
        "</select>"
    );

    $('#select_group' + id).val(group);

    $('#button' + id).html(
            "<button onclick='applyEdit(" + id + ")'>Apply</button>"
    );

}

function applyEdit(id) {

    var email = $('#email' + id).children().val();
    var name = $('#name' + id).children().val();
    var group = $('#group' + id).children().val();

    $('#email' + id).html(email);

    $('#name' + id).html(name);

    $('#group' + id).html(group);

    $('#button' + id).html(
            "<button onclick='editUser(" + id + ")'>Edit</button>"
    );


    // sending to Server
    $.ajax({
        type: 'post',
        url: '/rest/user/update',
        crossDomain: true,
        data: {'id': id, 'email': email, 'name': name, 'group': group},
        success: function(data) {
            alert("Success");
        },
        error: function (data) {
            alert("Error")
        }
    });

    return false;
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