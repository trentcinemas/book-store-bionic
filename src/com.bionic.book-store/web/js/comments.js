///**
// * Created by UFO on 04.08.2014.
// */
//$(document).ready(function () {
//    var URL = window.location.search;
//    var getRequest = URL.split("?")[1];
//    var id = getRequest.split("=")[1];
//    $.ajax({
//        type: "get",
//        dataType: "json",
//        url: "/rest/comment/forBook/" + id,
//        success: function (data) {
//            for (var i = 0; i < data.length; i++) {
//                /*   $("#user").html(data[i].user);
//                 $("#date").html(data[i].date);
//                 $("#comm_desc").html(data[i].comm_desc);*/
//                $(".comments").append("<div>" +
//                    "<h3 class='comm_desc'>" + data[i].comm_desc + "</h3>" +
//                    "<h4 class='date'>" + data[i].date + "</h4>" +
//                    "<a class='user'>" + data[i].user + "</a>" +
//                    "</div>")
//
//            }
//        }
//    });
//});
//
//
////$(document).ready(function () {
////    $("#add_comment_form").submit(function () {
////
////        var URL = window.location.search;
////        var getRequest = URL.split("?")[1];
////        var book = getRequest.split("=")[1];
////        var comment = $("#comment").val;
////
////        $.ajax({
////            type: "post",
////            url: "rest/comment/addComment",
////            data: {
////                book: book,
////                comm_desc: comm_desc
////            },
////            success: function (data) {
////                alert("added comment");
////            },
////            error: function (data) {
////                alert(data);
////            }
////
////        });
////
////
////    });
////
//////        $.ajax({
//////            type: 'get',
//////            url: '/rest/comment/addComment',
//////            data: {'comment': comment},
//////            crossDomain: true,
//////            success: function (data) {
//////                alert("Added");
//////            }
//////        });
//////
//////        return false;
//////    });
////});
