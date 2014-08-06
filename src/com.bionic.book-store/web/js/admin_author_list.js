/**
 * Created by jsarafajr on 31.07.14.
 * Edited by bbetter on 06.08.14
 */

// порядок сортування
var order_name=true;
var order_surname=true;
// поточна сторінка
var current_page=1;


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

// функція яка заповняє список сторінок
function fillPagination(data){
    var count = parseInt(data)/15;
    $("#list-nav").append("<li class='disabled prev'><a>«</a></li>");
    for(var i=1;i<=count+1;i++){
        if(i==current_page)
            $("#list-nav").append("<li value='"+i+"' class='active'><a>"+i+"</a></li>");
        else
            $("#list-nav").append("<li value='"+i+"'><a>"+i+"</a></li>");

    }
    if(count<1)
    $("#list-nav").append("<li class='disabled next'><a>»</a>")
    else
    $("#list-nav").append("<li class='next'><a>»</a>")

}
// функція яка заповнює таблицю авторів
function fillTable(data){
    $("#authors_table").html("");
    for (var i = 0; i < data.length; i++) {
        var id = data[i].id;
        $('#authors_table').append(
                "<tr id='author" + id + "'> " +
                "<td>" + id + "</td>" +
                "<td id='firstname"+id+"'>" + data[i].firstName + "</td>" +
                "<td id='lastname"+id+"'>" + data[i].lastName + "</td>" +
                "<td id='description"+id+"'>" + data[i].description + "</td>" +
                "<td><a href='/rest/admin/edit-author?id=" + id + "'><button>Edit</button></a></td>" +
                "<td><button onclick='removeAuthor(" + id + ")'>Remove</button></td>" +
                "</tr>"
        );
    }
}
// функція яка виводить авторів з певної сторінки
function getAuthorsFromPage(page){
    $.ajax({
        type: 'get',
        url: '/rest/author/getFromPage/' + page,
        crossDomain: true,
        dataType: "json",
        cache: false,
        success: function (data) {
            fillTable(data);
        }
    });
}
// функція яка виводить таблицю список сторінок та авторів
function showAuthors(){
    $.ajax({
        type: 'get',
        url: '/rest/author/getPageCount',
        crossDomain: true,
        cache: false,
        success: function (data) {
            fillPagination(data);
            getAuthorsFromPage(current_page);
        }

    });
}
// функція яка виводить авторів сортованих за іменем
function getSortedByName(){
    var url = '/rest/author/sort/' + current_page + "/name/" + order_name.toString();
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
// функція яка виводить авторів сортованих за певним прізвищем
function getSortedBySurname(){
    var url = '/rest/author/sort/' + current_page + "/surname/" + order_surname.toString();
    if (order_surname == false) order_surname = true;
    else {
        order_surname = false;
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
        url: "/rest/author/search/" + searchstring,
        success: function (data) {
            fillTable(data);
        }
    });
}
//функція що видаляє автора за айді
function removeAuthor(id) {
    if (confirm("Видалити автора?")) {
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
}


$(document).ready(function() {

    addAjaxLoader();
    showAuthors();

    $('.pagination').on('click', 'li', function () {

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

        getAuthorsFromPage($(".pagination .active").val());
    });
    $("th#name").click(function () {
       getSortedByName();
    });

    $("th#surname").click(function () {
        getSortedBySurname();
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
