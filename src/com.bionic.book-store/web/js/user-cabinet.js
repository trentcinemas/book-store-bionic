/**
 * Created by Джон on 03.08.2014.
 */
$(document).ready(function(){
    $.ajax({
        type:"get",
        url:"rest/user/get",
        dataType:"json",
        success:function(data){
            $(".userName").html(data.name);
            $(".userMail").html(data.email);
        },
        error:function(data){

        }
    }) ;
});