/**
 * Created by Джон on 25.07.2014.
 */
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


