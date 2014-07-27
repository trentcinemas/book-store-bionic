/**
 * Created by jsarafajr on 27.07.14.
 */
$(document).ready(function() {
    $.ajax({
        type: 'get',
        url: '/rest/session/get-user',
        crossDomain: true,
        success: function (data) {
            $('#user_name').html(data.name);
        },
        error: function (data) {
            alert("fuck");
        }
    });
});

