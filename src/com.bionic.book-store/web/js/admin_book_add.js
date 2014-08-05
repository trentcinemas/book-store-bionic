/**
 * Created by Джон on 25.07.2014.
 */
var authors;
$(document).ready(function(){
    $.ajax({
        type:"get",
        dataType:"json",
        url:"/rest/author/getAll",
        success:function(data){
            authors=data;
            for(var i=0;i<authors.length;i++){
                $("#author").append("<option value='"+authors[i].firstName+" "+authors[i].lastName+"'>"+
                    authors[i].firstName+" "+authors[i].lastName+"</option>");
            }
        }
    });

    $.ajax({
        type:"get",
        dataType:"json",
        url:"/rest/genre/getAll",
        success:function(data){
            var genres=data;
            for(var i=0;i<genres.length;i++){
                $("#genre").append("<option value='"+genres[i].type+"'>"+genres[i].type+"</option>");
            }
        }
    });

    $('#add_book_form').submit(function(e) {
        e.preventDefault();

        var formData = new FormData(this);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/rest/book/upload");

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if(xhr.status == 400){
                    console.log(formData);
                }
                if (xhr.status == 200) {
                    alert("book added");
                }
            }
        };
        xhr.send(formData);
    });

});

