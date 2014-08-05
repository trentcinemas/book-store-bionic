/**
 * Created by jsarafajr on 29.07.14.
 */
$(document).ready(function() {
    var URL = window.location.search;
    var getRequest = URL.split("?")[1];
    var id = getRequest.split("=")[1];

    $.ajax({
        type:"get",
        dataType:"json",
        url:"/rest/author/get/" + id,
        success:function(data){
            book = data;
            $('#edit_author_title').html("Редагування \"" + data.firstName + " " + data.lastName + "\"");
            $('#au_firstname').val(data.firstName);
            $('#au_lastname').val(data.lastName);
            $('#au_description').val(data.description);
        }
    });

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
                    alert("Authors information changed");
                    location.reload();
                }
            }
        };
        xhr.send(formData);
    });
});