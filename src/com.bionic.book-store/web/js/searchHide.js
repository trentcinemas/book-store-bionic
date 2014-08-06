
var search = function() {
    var searchSize = $("header").find(".form-control");
    searchSize.focus(function(){
        $(this).animate({ width:"350px"}, 1000);
    }).blur(function(){
        $(this).animate({ width:"250px"}, 500);
    });
};
var searchControl = function() {
    $(document).ready(function () {
        var searchSize = $("header").find(".form-control");
        var width = $(window).width();
        if (width > 769) {
            search();
        }else{
            searchSize.addClass("search-tool-style");
            return;
        }
    });
}
$(document).ready(searchControl);
$(window).resize(searchControl);