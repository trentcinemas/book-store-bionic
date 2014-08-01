/**
 * Created by jsarafajr on 31.07.14.
 */

$(document).ready(function() {
    $("#add_genre_form").submit(function() {
        var title = $('#topic_title').val();

        $.ajax({
            type: 'post',
            url: '/rest/genre/add',
            data: {'title': title},
            crossDomain: true,
            success: function (data) {
                alert("Added");
            }
        });

        return false;
    });
});