var actionBtn = function() {
    var width = $(window).width();
        var buttonHide = $("#btnHide").find("button");
		var menu = $("#btnHide").find("ul");
    if (width <= 768 ) {
		menu.addClass("dropdown-menu");
		buttonHide.removeClass("hidden");
    }else if(width > 768){
		buttonHide.addClass("hidden");
		menu.removeClass("dropdown-menu");

	}
};

$(document).ready(actionBtn);
$(window).resize(actionBtn);