/**
 * Created by Джон on 25.07.2014.
 */
var photo = null, doc = null, fb2 = null, pdf = null;
$("#photo").on("change", function (e) {
    photo = e.target.files[0];

});
$("#pdf").on("change", function (e) {
    pdf = e.target.files[0];

});
$("#doc").on("change", function (e) {
    doc = e.target.files[0];
    /*if(doc.type!="doc") {
     doc=null;
     }*/
});
$("#fb2").on("change", function (e) {
    fb2 = e.target.files[0];

});
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


