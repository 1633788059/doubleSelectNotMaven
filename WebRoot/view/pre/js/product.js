var t = n = 0, count;
$(document).ready(function () {
    count = $(".thumb_pic span").length;
    $(".picshow a:gt(0)").hide();
    $(".thumb_pic span").mouseover(function () {
        var i = $(this).index();
        n = i;
        if (i >= count) return;
        $(this).css('border-color', '#727071').siblings().css('border-color', '#ffffff');
        $(".picshow a:eq(" + $(this).index() + ")").fadeIn(300).siblings().fadeOut(300);
    });
    t = setInterval("showAuto()", 10000);
})

function showAuto() {
    n = n >= (count - 1) ? 0 : ++n;
    $(".thumb_pic span").eq(n).trigger('mouseover');
}



//²úÆ·Í¼Æ¬ÇÐ»»
$(function () {

    $('.cont-tabs li').click(function () {
        $(this).addClass('on').siblings().removeClass('on');
        $('.content-box .details:eq(' + $(this).index() + ')').show().siblings('.details').hide();
    })
    $('.match-tab li').click(function () {
        $(this).addClass('on').siblings().removeClass('on');
        $('.match-box table:eq(' + $(this).index() + ')').show().siblings('table').hide();
    })

    $(".prod_js img:gt(0)").hide();
    $(".select_pic li").hover(function () {
        $(".prod_js img:eq(" + $(this).index() + ")").fadeIn(300).siblings().fadeOut(300);
    });


})













