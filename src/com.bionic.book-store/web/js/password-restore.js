/**
 * Created by Джон on 02.08.2014.
 */
$(document).ready(function(){
    $("#sendResetForm").submit(function(e){

        $.ajax({
            type:"post",
            url:"/rest/user/resetCode",
            data:{email:$("#email").val()},
            success:function(){alert("check email");},
            error:function(){alert("smth wrong");}
        }) ;
        e.preventDefault();
    });
});
