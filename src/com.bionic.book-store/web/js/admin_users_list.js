/**
 * Created by jsarafajr on 27.07.14.
 */
var order_name=true;
var order_email=true;
var order_group=true;
var current_page=1;

function fillTable(data){
    $("#users_table").html("");
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
    });

    $("th#email").click(function(){

        var url='/rest/user/sort/'+current_page+"/email/"+order_email.toString();
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
    $("th#name").click(function(){
        var url='/rest/user/sort/'+current_page+"/name/"+order_name.toString();
        if(order_name==false) order_name=true;
        else {
            order_name=false;
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
    $("th#group").click(function(){
        var url='/rest/user/sort/'+current_page+"/group/"+order_group.toString();
        if(order_group==false) order_group=true;
        else {
            order_group=false;
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
           url:"/rest/user/search/"+searchstring,
            success:function(data){
                fillTable(data);
            }
        });
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