/**
 * Created by UFO on 7/29/14.
 */

$(document).ready(function() {

    $("#searchString").keyup(function(e){

        $.ajax({
           type:"get",
           url:"../../rest/book/search/"+$(this).val(),
           crossDomain:true,
           dataType:"json",
           cache: false,
           success:function(data) {
               $("#result").html("");
               for (var i = 0; i < data.length; i++) {
                   $("#result").append("<div style='width:50px;height:50px;' id='"+data[i].id+"'>"+data[i].title + "<br>"+data[i].price+"<img src='/rest/file/getimage/" + data[i].sm_cover + "'></div>");
               }
           },
            error:function(){

                $("#result").html("");

            }
        });
    })
});



