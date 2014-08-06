/**
 * Created by jsarafajr on 27.07.14.
 */
var order_name = true;
var order_email = true;
var order_group = true;
var current_page = 1;

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

function fillPagination(data) {
    var count = parseInt(data) / 15;
    $("#list-nav").append("<li class='disabled prev'><a>«</a></li>");
    for (var i = 1; i <= count + 1; i++) {
        if (i == current_page)
            $("#list-nav").append("<li value='" + i + "' class='active'><a>" + i + "</a></li>");
        else
            $("#list-nav").append("<li value='" + i + "'><a>" + i + "</a></li>");
    }
    if (count < 1)
        $("#list-nav").append("<li class='disabled next'><a>»</a>")
    else
        $("#list-nav").append("<li class='next'><a>»</a>")
}

function fillTable(data) {
    $("#users_table").html("");
    for (var i = 0; i < data.length; i++) {
        var id = data[i].id;

        $('#users_table').append(
                "<tr id='user" + id + "'> " +
                "<td>" + id + "</td>" +
                "<td id='email" + id + "'>" + data[i].email + "</td>" +
                "<td id='name" + id + "'>" + data[i].name + "</td>" +
                "<td id='group" + id + "'>" + data[i].group + "</td>" +
                "<td id='button" + id + "'>" + "<button onclick='editUser(" + data[i].id + ")'>Edit</button>" + "</td>" +
                "</tr>"
        );
    }
}

function showUsers() {
    $.ajax({
        type: 'get',
        url: '/rest/user/getPageCount',
        success: function (data) {
            fillPagination(data);
            getUsersFromPage(1);
            }
        });
}

function getOrderedByEmail() {
    var url = '/rest/user/sort/' + current_page + "/email/" + order_email.toString();
    if (order_email == false) order_email = true;
    else {
        order_email = false;
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

function getOrderedByName() {
    var url = '/rest/user/sort/' + current_page + "/name/" + order_name.toString();
    if (order_name == false) order_name = true;
    else {
        order_name = false;
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

function getOrderedByGroup() {
    var url = '/rest/user/sort/' + current_page + "/group/" + order_group.toString();
    if (order_group == false) order_group = true;
    else {
        order_group = false;
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
        type: "get",
        url: "/rest/user/search/" + searchstring,
        success: function (data) {
            fillTable(data);
        }
    });
}

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
        success: function (data) {
            alert("Success");
        },
        error: function (data) {
            alert("Error")
        }
    });

    return false;
}

function getUsersFromPage(page){
    $.ajax({
        type: 'get',
        url: '/rest/user/getFromPage/' + page,
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });
}

$(document).ready(function () {
    addAjaxLoader();
    showUsers();


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

        getUsersFromPage($(".pagination .active").val())
    });


    $("th#email").click(function () {
        getOrderedByEmail();
    });
    $("th#name").click(function () {
        getOrderedByName();
    });
    $("th#group").click(function () {
        getOrderedByGroup();
    });
    $("#searchFrom").submit(function (e) {
        e.preventDefault();
        search($("#search").val());
    });
});

// Ajax activity indicator bound to ajax start/stop document events
$(document).ajaxStart(function () {
    $('#ajaxBusy').show();
}).ajaxStop(function () {
    $('#ajaxBusy').hide();
});