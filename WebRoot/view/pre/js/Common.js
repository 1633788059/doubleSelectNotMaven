// JavaScript Document
$(function () {
   $('.header-select').hover(function () {
   
        $(this).addClass('city-select-hover');
    }, function () {
        $(this).removeClass('city-select-hover');
    });
	$(".all").hover(function () {
       // $(".expose .item:eq(0)").toggleClass('hover');
        $(".nav-exposed").show();
       // var nav_height = $('.nav_new').height();
       // $('.nav_new').height(nav_height);
    }, function () {
       // $(".expose .item").removeClass('hover');
        $(".nav-exposed").hide();
    })
	
	   //loginbar
    $(".loginbar,.membar").hover(function () {
        $(this).find('dt').attr("class", 'hover');
        $("dd,this").find('.loginbox,.membox').show();
        $("#showtip").hide();
    }, function () {
        $(this).find('dt').attr("class", '');
        $("dd,this").find('.loginbox,.membox').hide();
    })
	    //cartbar
    $(".cartbar").hover(function () {
        $(".cartbox").show();
        $(".cartbar dt").eq(0).attr("class", 'hover');
    }, function () {
        $(".cartbox").hide();
        $(".cartbar dt").eq(0).attr("class", '');
    })
    $("form").hover(function () {
        $(this).find(".p-quick").show();
    }, function () {
        $(this).find(".p-quick").hide();
    })
	var t_li = $("#promotion-tab a")
    var c_li = $(".promotion-content .promotion-item")
    t_li.hover(function () {
        var i = t_li.index($(this));
        function way() {
            t_li.removeClass("active").eq(i).addClass("active");
            c_li.hide().eq(i).show();
        }
        timer = setTimeout(way, 100);
    }, function () {
        clearTimeout(timer);
    });
	
	
});

