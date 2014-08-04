/**
 * Created by Джон on 25.07.2014.
 */
var authors;
var book;
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

    var URL = window.location.search;
    var getRequest = URL.split("?")[1];
    var id = getRequest.split("=")[1];

    $.ajax({
        type:"get",
        dataType:"json",
        url:"/rest/book/get/" + id,
        success:function(data){
            book = data;
            $('#edit_book_title').html("Редагування \"" + data.title + "\"");
            $('#title').val(data.title);
            $('#description').val(data.description);
            $('#page_count').val(data.pagesCnt);
            $('#price').val(data.price);
            $("#author option").filter(function() {
                return this.text == data.author;
            }).attr('selected', true);
            $("#genre option").filter(function() {
                return this.text == data.genre;
            }).attr('selected', true);
        }
    });


    $('#add_book_form').submit(function(e) {
        e.preventDefault();

        var formData = new FormData(this);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/rest/book/update");

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                if(xhr.status == 400){
                    console.log(formData);
                }
                if (xhr.status == 200) {
                    alert("Book changed");
                    location.reload();
                }
            }
        };
        xhr.send(formData);
    });
});

