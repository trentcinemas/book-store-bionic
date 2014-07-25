$(document).ready(function(){
    $("#enter").click(function(){
        $("#login").slideToggle("slow");
        $(this).toggleClass("active");
    });
});