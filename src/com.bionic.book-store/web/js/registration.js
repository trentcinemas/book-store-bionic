/**
 * Created by jsarafajr on 7/17/14.
 */

$(document).ready(function() {
    var regexp =/^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
    var email = $('#email').val();
    if(!email.match(regexp)){
        $("#email").css({ border:"2px solid red"});
        $(".email-fail").css({display:"block"});
    }

    $('#regist_field').submit(function(e) {
        e.preventDefault();
        var email = $('#email').val();
        var password = $('#password').val();
        var name = $("#name").val();

        if(password=$("#conf_pass").val()) {
            $.ajax({
                type: 'post',
                url: '/rest/register',
                crossDomain: true,
                data: {'email': email, 'name': name, 'password': password},
                response: 'text', // response type
                success: function (data) {
                    $('#status').html("Success");
                    location.reload();
                },
                statusCode: {
                	// HTTP 409 - Conflict
                	409: function (data) {
                		$('#status').html(data.responseText);	
                	}
                }

            });

            return false;
        }
    });
});
