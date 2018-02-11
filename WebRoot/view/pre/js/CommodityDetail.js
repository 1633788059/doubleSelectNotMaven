// JavaScript Document
$(function () {
    $('.cont-tabs li').click(function () {
        $(this).addClass('on').siblings().removeClass('on');
        $('.content-box .details:eq(' + $(this).index() + ')').show().siblings('.details').hide();
    })
    $(".cont-tabs li#comment").one("click", function () {
        GetData(0, 1);
    });
    $('.match-tab li').click(function () {
        $(this).addClass('on').siblings().removeClass('on');
        $('.match-box table:eq(' + $(this).index() + ')').show().siblings('table').hide();
    })
    //picshow
    $("#imgdiv2 img:gt(0)").hide();
    $("#imgdiv ul li").hover(function () {
        var i = $("#imgdiv ul li").index(this);
        $(this).addClass('on').siblings().removeClass('on');
        $("#imgdiv2 img").eq(i).stop(true, true).fadeIn(600).siblings("img").fadeOut(300);
    })
    //border none
    $(".sidelist").find('li:last').css('border-bottom', 'none');
    //score
    $(".slip li span").each(function () {
        var o = $(this);
        var w = o.attr('w');
        $(".cont-tabs li:eq(1)").click(function () {
            o.animate({ 'width': w }, 600);
        })
    })
    //stars score
    $("#commentBtn").click(function () {
        //$(".starsCom").fadeIn('slow');
        if (isLogined()) {
            window.location = G.DOMAIN.MEMBER_YIGUO_COM + "/MyBuy.aspx";
        }
    });
    $(".starsSel .starsOn").hover(function () {
        $(".starsCur").hide();
        $(this).removeClass('starsOn').addClass('starsCur');
    }, function () {
        $(this).removeClass('starsCur').addClass('starsOn');
        $(".starsCur").show();
    })
	.click(function () {
	    var count = $(this).width();
	    $(".starsCur").width(count);
	    switch (count) {
	        case 24:
	            $(".comTips").html('非常差');
	            $("#score").val("1");
	            break;
	        case 48:
	            $(".comTips").html('差');
	            $("#score").val("2");
	            break;
	        case 72:
	            $(".comTips").html('一般');
	            $("#score").val("3");
	            break;
	        case 96:
	            $(".comTips").html('满意');
	            $("#score").val("4");
	            break;
	        case 120:
	            $(".comTips").html('非常满意');
	            $("#score").val("5");
	            break;
	    }
	});

    $("#commentSubmit").click(function () {
        if ($(".starsSel #score").size() <= 0) {
            alert("请选择：评价！");
            return;
        }
        var productEvaluate = $("#score").val();
        var commodityId = $("#commodity").val();
        var userId = $("#uid").val();
        var userName = $("#uname").val();
        $.ajax({
            type: "POST",
            url: "/Ajax/HandlerComments.ashx",
            data: "action=AddComment&CommodityId=" + commodityId + "&UserId=" + userId + "&UserName=" + userName + "&ProductEvaluate=" + productEvaluate + "&deliveryEvaluate=5&serviceEvaluate=5",
            datatype: "json",
            success: function (data) {
                jsonData = eval("(" + data + ")");
                if (jsonData.Success == "2") {
                    alert("评价操作成功！");
                } else if (jsonData.Success == 1) {
                    alert("您已经评论过了！");
                } else {
                    alert("评价操作失败！");
                }
            },
            error: function (error) {
                alert(error.responseText);
            }
        });
    });


    if (getCookie('Viewed') != null) {
        $.ajax({
            url: "/Ajax/ViewedCommodity.aspx?n=" + Math.random(),
            success: function (html) {
                $("#divhistory").html(html);

            }
        });
    }
})



var screenWidth = window.screen.width;
if (screenWidth < 1280) {
    $(function () {
        $('.yswrap').removeClass('yswrap').addClass('yswrap1024');
        $('.yswrap1024 span').eq(0).css('padding-left', '0px');
        $('.yswrap1024 span').eq(1).css('padding-left', '0px');
    });
};

$(function () {
    $('.yswrap span').eq(5).css('padding-left', '2px');
});

$(function () {
    var sWidth = $(".ysfocus").width();
    var len = $(".ysfocus ul li").length;
    var index = 0;
    var picTimer;

    $(".ysfocus .btn span").mouseenter(function () {
        index = $(".ysfocus .btn span").index(this);
        showPics(index);
    }).eq(0).trigger("mouseenter");
    $(".ysfocus ul").css("width", sWidth * (len + 1));


    $(".ysfocus").hover(function () {
        clearInterval(picTimer);
    }, function () {
        picTimer = setInterval(function () {
            if (index == len) {
                showFirPic();
                index = 0;
            } else {
                showPics(index);
            }
            index++;
        }, 1500);
    }).trigger("mouseleave");

    function showPics(index) {
        var nowLeft = -index * sWidth;
        $(".ysfocus ul").stop(true, false).animate({ "left": nowLeft }, 500);
        $(".ysfocus .btn span").removeClass("on").eq(index).addClass("on");
    }

    function showFirPic() {
        $(".ysfocus ul").append($(".ysfocus ul li:first").clone());
        var nowLeft = -len * sWidth;
        $(".ysfocus ul").stop(true, false).animate({ "left": nowLeft }, 500, function () {
            $(".ysfocus ul").css("left", "0");
            $(".ysfocus ul li:last").remove();
        });
        $(".ysfocus .btn span").removeClass("on").eq(0).addClass("on");
    }
});
