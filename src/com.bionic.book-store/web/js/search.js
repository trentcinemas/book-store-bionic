$(document).ready(function(){
    addAjaxLoader();


   var searchstring=window.location.search.split("?search=")[1];
    var arr=searchstring.split("+");
    searchstring=arr[0];
    for(var i=1;i<arr.length;i++)
    searchstring+=" "+arr[i];
    $.ajax({
       type:"get",
       url:"/rest/book/search/"+searchstring,
       dataType:"json",
       success:function(data) {
           $(".search-result").html("");
           for (var i = 0; i < data.length; i++) {
               $(".search-result").append("<li>" + data[i].title + "<br>" + data[i].author + "<br>" +
                   "<a href='/rest/book/getPage/" + data[i].id+"'>"+
                   "<img width='150px' height='200px' src='/rest/file/getimage/" +
                   data[i].sm_cover + "'></a><br><span style='font-weight: bold'>" + data[i].price + " грн.</span>" + "<li>")
           }
       },
        error:function(data) {
            $(".search-result").html("");
        }
    });
});


function addAjaxLoader() {
    // Setup the ajax indicator
    $('#ajax_loading_div').append('<div class="ajaxBusy"><img src="/images/ajax-loader_circle.gif"></div>');

    $('.ajaxBusy').css({
        display: "none",
        paddingLeft: "45%",
        paddingTop: "0px",
        paddingBottom: "0px",
        position: "relative",
        right: "3px",
        top: "3px",
        width: "auto"
    });
}

// Ajax activity indicator bound to ajax start/stop document events
$(document).ajaxStart(function(){
    $('.ajaxBusy').show();
}).ajaxStop(function(){
    $('.ajaxBusy').hide();
});