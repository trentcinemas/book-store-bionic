/**
 * Created by Джон on 25.07.2014.
 */
function content(){
    $.ajax({
        type: "get",
        url: "rest/book/listAll",
        crossDomain: true,
        dataType:"json",
        cache: false,
        success:function (data) {
            if (data != null) {
               $("#content").append("<ul>");
               for(var i=0;i<data.length;i++){
                   $("#content").append("<li><span>"+data[i].title+"</span>"+"<br><span>"+data[i].price+"$</span><br>");
                   $("#content").append("<img width='100px' height='100px' src='file:///"+data[i].cover+"'>");
               }
                $("#content").append("</ul>");
            }
        },
        error: function (data) {
            alert("Error");
        }
    });
}
$.ajax({
    type: "get",
    url: "rest/session/get-user",
    crossDomain: true,
    cache: false,
    success: function (data) {
        if (data != null) {
            $("#header").html(data.name);
            $("#header").append("<a href='book_add.html'>Додати книгу</a>");
            content();
        }
        return false;
    },
    error: function (data) {
        alert("Error");
    }

});


