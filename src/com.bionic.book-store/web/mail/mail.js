/**
 * Created by Джон on 02.08.2014.
 */
$(document).ready(function() {
    $("#mailForm").submit(function (e) {

        $.ajax({
            type: "post",
            url: "/rest/mail/send",
            data: {
                to: $("#to").val(),
                sub: $("#sub").val(),
                message: $("#message").val()
            },
            success: function () {
                alert("send")
            },
            error: function () {
                alert("not_send")
            }
        })
        e.preventDefault();
    });
});