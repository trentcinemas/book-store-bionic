/**
 * Created by jsarafajr on 29.07.14.
 */
$(document).ready(function() {
    $('#add_author_form').submit(function(e) {
        e.preventDefault();

        var formData = new FormData(this);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/rest/author/add");

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