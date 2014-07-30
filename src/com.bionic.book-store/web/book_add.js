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
               $("#author").append("<option value='"+authors[i].id+"'>"+authors[i].firstName+" "+authors[i].lastName+"</option>");

           }
           $.ajax({
               type:"get",
               dataType:"json",
               url:"/rest/genre/getAll",
               success:function(data){
                   var genres=data;
                   for(var i=0;i<genres.length;i++){
                       $("#genre").append("<option value='"+genres[i].id+"'>"+genres[i].type+"</option>");
                   }
               }

           });

       }

    });


    $("#author").change(function(){
       $("#selected_author_id").val($('#author option:selected').attr('value'));
    });
    $("#genre").change(function(){
       $("#selected_genre_id").val($('#genre option:selected').attr('value'));
    });
});

$("#upload").submit(function (e) {
    e.preventDefault();

    var form = document.forms.upload;

    var formData = new FormData(form);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "rest/book/upload");

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

    /*  var formData=new FormData($("#upload")[0])
     $.ajax({
     type:"post",
     data:formData,
     processData:false,
     success:function(data){alert("good")}
     })*/
});


