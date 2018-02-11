// JavaScript Document
$(function () {
    $(".kv .picshow a:first").show().siblings('a').hide();
    var sw = 0;
    var time = 6000;
    $(".curtxt li").mouseover(function () {
        sw = $(".curtxt li").index(this);
        myShow(sw);
    });
    function myShow(i) {
        $(".curtxt li").eq(i).addClass("on").siblings("li").removeClass("on");
        $(".picshow a").eq(i).stop(true, true).fadeIn(600).siblings("a").fadeOut(600);
    }
    //滑入停止动画，滑出开始动画
    $(".kv").hover(function () {
        if (myTime) {
            clearInterval(myTime);
            myTime = null;
        }
    }, function () {
        myTime = setInterval(show, time);
    });
    //自动开始
    var myTime = setInterval(Show, time);

    function Show() {
        myShow(sw)
        sw++;

        if (sw == 6) {
            sw = 0;
        }
    }
})