
//popout
$(function () {
    var popH = $(".popout1").height();
    $(".popout1").css('marginTop', -popH / 2);
    $(".close div.popout1 .cancel").click(function () {
        $(this).parents('.popout1').fadeOut();
    })
})


$(function () {
    var popH = $(".popout").height();
    $(".popout").css('marginTop', -((popH / 2) + 150));
    $(".close,div.popout .cancel").click(function () {
        $(this).parents('.popout').fadeOut();
    })
})

//sidebar
$(function () {
    var screenH = $(window).height();
    $(window).resize(function () {
        screenH = $(window).height();
    });
    var sTop = (screenH - 493) / 2;
    $(".sidebar").css('top', sTop);
    $(window).scroll(function () {
        var top = $(window).scrollTop();
        var sTop = (screenH - 493) / 2 + top;
        $(".sidebar").css('top', sTop);
    })

    $(".sideform").hover(function () {
        $(this).find('dd').animate({ 'width': '153' }, 300, function () { $(this).css('z-index', '1') });
    }, function () {
        $(this).find('dd').animate({ 'width': '0' }, 300, function () { $(this).css('z-index', '-1') });
    })
})


//新版banner
var sw2 = 0;
$(function () {
    $(".homekv .picshow .module:eq(0)").show().siblings('.module').hide();
    $(".homekv .curtxt2 li:eq(0)").addClass('on');

    //第一次加载页面执行动画效果
    myShow2(sw2)
    sw2++
    var tmpCount = $(".homekv .curtxt2").find("li").length - 1;
    //动画效果执行完毕开始进行自动加载，设置sw2为1
    var myTime = setInterval(function () {
        myShow2(sw2)
        sw2++;
        if (sw2 > tmpCount) { sw2 = 0; }
    }, 4500);

    $(".homekv .curtxt2 li").mouseover(function () {
        sw2 = $(".curtxt2 li").index(this);
        myShow2(sw2);
    });

});

function myShow2(i) {
    $(".homekv .curtxt2 li").eq(i).addClass("on").siblings("li").removeClass("on");
    $(".homekv .picshow .module").eq(i).stop(true, true).fadeIn('slow').siblings(".module").fadeOut('slow')
    $($(".homekv .picshow .module")[i]).addClass("on").siblings(".module").removeClass("on");
    //    });
};

/***********/

//document.onkeypress = function (e) {
//    var code;
//    if (!e) {
//        var e = window.event;
//    }
//    if (e.keyCode) {
//        code = e.keyCode;
//    }
//    else if (e.which) {
//        code = e.which;
//    }
//    if (code == 13) {
//        Search();
//    }
//}

//function Search() {
//    var keyword = document.getElementById("keyword").value;
//    if (keyword != "" && keyword != "输入产品名或编号")
//        window.location.href = "/products/.html?keyword=" + escape(keyword);
//    return false;
//}


/************/
function logout() {

    $.ajax({
        url: "/Ajax/Logout.ashx",
        type: "POST",
        dataType: "text",
        data: "date=" + new Date().getTime(),
        success: function (strurl) {
            //alert(strurl);
            window.location = strurl;
        }
    });
}

function isLogIn() {
    var a = "0"; //默认值0：未登录；1：登录成功
    $.ajax({
        type: "GET",
        url: "/Ajax/logout.ashx",
        async: false,
        dataType: "text",
        data: "datestamp=" + new Date().getTime() + "&para=isLogin",
        success: function (d) {
            a = d;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert("操作失败!");
        }
    });
    return a;
}

function getUserId() {
    var a = ""; //默认值0：未登录；1：登录成功
    $.ajax({
        type: "GET",
        url: "/Ajax/logout.ashx",
        async: false,
        dataType: "text",
        data: "datestamp=" + new Date().getTime() + "&para=userid",
        success: function (d) {
            a = d;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
            alert("操作失败!");
        }
    });
    return a;
}


function isLogined() {
    if (isLogIn() == "0") {
        $("#xCommodityDetail").find(".pop-title").find("strong").html("您尚未登录！"); //
        $("#xCommodityDetail").find("a.popout-btn").hide();
        $("#xCommodityDetail").show();
        //alert("请先登录");
        return false;
    }
    else {
        return true;
    }
}

function addToFavor(commodityId) {
    if (isLogined()) {
        G.app.itemDetail.addToFavor1(commodityId);
    }
}

$(function () {
    $("#tabcouple tr").each(function (key, value) {
        //              alert($(value).hasClass("trclass"))
        if ($(value).hasClass("trclass")) {
            var td = $(value).children('td')[2];
            // alert(td.innerHTML.toLowerCase())
            if (td.innerHTML.toLowerCase().indexOf('<dt>') < 0) {
                $(value).hide();
            }
        }
        //alert($(value).attr("style")!="display:none;")
        //                  if ($(value).attr("style")!="display:none;" && key%2==0) {
        //                      $(value).attr("style","background:#efefef;")
        //}
    })
    //           $("#tabcouple tr:odd").attr("style","background:#efefef;")
})


/***********/

var res = "";
var clikccount = 0;
$(function () {
    $("#customerser").click(function () {
       alert("暂时不能使用，给您带您带来不便请谅解！");
return;
        //      alert(res+"--"+count);
        if (clikccount == 0) {
            $.post("/Ajax/CallCenter.ashx", function (result) {
                res = result;
            })

        }
        clikccount++;
        var id = setInterval(function () {
            if (res != "") {
                ShowDialog(res, '1');
                clearInterval(id);
            }
        }, 500)

    })
})


function ShowDialogLogout() {
    $.post("/Ajax/CallCenter.ashx", function (result) {
        ShowDialog(result, '0');
    })

}

function ShowDialog_ChuZhiKa() {
    if (isLogIn() == "0") {

        $("#form2").attr("action", "https://login.yiguo.com/ub/StatisticsLogin.aspx?url=http://utaste.yiguo.com/accounts/ChuZhiKa.aspx");
        $("#xCommodityDetail").find(".pop-title").find("strong").html("您尚未登录！"); //
        $("#xCommodityDetail").find("a.popout-btn").hide();
        $("#xCommodityDetail").show();
        return false;
    } else {
        window.location = "http://utaste.yiguo.com/accounts/ChuZhiKa.aspx";
    }
}
function ShowDialog(url, NeedLogin) {

    if (isLogIn() == 0 && NeedLogin == "1") {

        $("#xCommodityDetail").find(".pop-title").find("strong").html("请登录后联系会员专享客服！"); //
        $("#xCommodityDetail").find("a.popout-btn").show();
        $("#xCommodityDetail").show();
        return;
    }
    else {
        if (isLogIn() == 0) {
            $("#xCommodityDetail").hide();
        }
        var iWidth = 650; //窗口宽度
        var iHeight = 500; //窗口高度
        var iTop = (window.screen.height - iHeight) / 2;
        var iLeft = (window.screen.width - iWidth) / 2;
        window.open(
                    url, "Detail", "Scrollbars=no,Toolbar=no,Location=no,Direction=no,Resizeable=yes, Width=" + iWidth + " ,Height=" + iHeight + ",top=" + iTop + ",left=" + iLeft);

    }
}

$(function () {
    //获取城市站点
    if (getCookie('CityCSS') != null) {
        var cityname = getCookie('CityCSS');
        if (cityname != null) {
            var name = cityname.split('&')[2].split('=')[1];
            $(".header-select .change").html(decodeURIComponent(name));
        }
    }
    else {
        $.ajax({
            type: "GET",
            url: "/Ajax/SetAreaCookie.ashx",
            async: false,
            dataType: "text",
            data: "datestamp=" + new Date().getTime() + "&operate=getareaname",
            success: function (d) {
                $(".header-select .change").html(d);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                //alert("操作失败!");
            }
        });
    }


    //判断用户是登陆


    if (getCookie('uin') != null) {
        var UserName = G.app.User.GetUserName();
        if (UserName != null && UserName != "") {
            G.app.itemDetail.freshTopCartJ();
            $("#divLogin1").hide();
            $("#divLogin2").hide();

            $("#divLogout1").show();
            $("#divLogout2").show();
            $("#divLogout3").show();

            $("#divLogout1 a").html(UserName);

            if (getCookie('uic') != null) {
                $("#spancoupon").html(getCookie('uic'));
            }
            else {
                var couponcount = G.app.User.GetUserEcoupon();
                $("#spancoupon").html(couponcount);
            }

        }
        else {
            //刷新购物车
            if (getCookie('Products') != null) {
                G.app.itemDetail.freshTopCartJ();
            }
            else {
                var htmlCart = "";
                htmlCart += "<dt><a href='" + G.logic.constants.urlShoppingCart + "'><span>" + 0 + "</span></a></dt>";
                htmlCart += "<dd><div class=\"cartbox\"><div class=\"cartbox-list\"><p class=\"total\">目前选购商品共<b>" + 0 + "</b>件</p>";
                htmlCart += "<p class=\"price-total\"><a href='" + G.logic.constants.urlShoppingCart + "' class=\"settleup\">立刻结算</a> 共<b>" + 0 + "</b>件商品 &nbsp;&nbsp;&nbsp;&nbsp;总计<b>" + 0.00 + "</b>元</p></div></div></dd>";

                $("dl.cartbar").html(htmlCart);
            }

            $("#divLogin1").show();
            $("#divLogin2").show();

            $("#divLogout1").hide();
            $("#divLogout2").hide();
            $("#divLogout3").hide();
        }
    }
    else {
        //刷新购物车
        if (getCookie('Products') != null) {
            G.app.itemDetail.freshTopCartJ();
        }
        else {
            var htmlCart = "";
            htmlCart += "<dt><a href='" + G.logic.constants.urlShoppingCart + "'><span>" + 0 + "</span></a></dt>";
            htmlCart += "<dd><div class=\"cartbox\"><div class=\"cartbox-list\"><p class=\"total\">目前选购商品共<b>" + 0 + "</b>件</p>";
            htmlCart += "<p class=\"price-total\"><a href='" + G.logic.constants.urlShoppingCart + "' class=\"settleup\">立刻结算</a> 共<b>" + 0 + "</b>件商品 &nbsp;&nbsp;&nbsp;&nbsp;总计<b>" + 0.00 + "</b>元</p></div></div></dd>";

            $("dl.cartbar").html(htmlCart);
        }

        $("#divLogin1").show();
        $("#divLogin2").show();

        $("#divLogout1").hide();
        $("#divLogout2").hide();
        $("#divLogout3").hide();
    }


    //优惠代码
    if (getCookie('ShowCoupon') == null && getCookie('uin') != null) {
        $.ajax({
            url: "/Ajax/UserLotteryCommodity.aspx?n=" + Math.random(),
            success: function (html) {
                if (getCookie('ShowCoupon') != null) {
                    $("#divlottery").html(html);
                    $("#divlotterypop").show();
                }
            }
        });
    }
})

/* 易果分析 2012-09-13  */


function addweblog(AdId) {
    $.ajax({
        async: true,
        url: httpUrlOneStats + '/Ajax/YGAnalytics.ashx?AdId=' + encodeURIComponent(AdId)
    });
}

//$(function () {
//    y = new Date()
//    diff = y.getTime() - yg_x.getTime();
//    var Tags = (diff / 1000);
//    var pageurl = window.location.href;
//    var referrerurl = document.referrer;
//    $.getJSON(httpUrlOneStats + '/Ajax/YGAnalytics.ashx?operate=view&site=yiguo&pageurl=' + encodeURIComponent(pageurl) + '&referrerurl=' + encodeURIComponent(referrerurl) + "&Tags=" + Tags + "&datestamp=" + new Date().getTime() + "&jsoncallback=?");
//    //$.getJSON('http://localhost:2264/BehaviorStats.aspx?operate=view&site=yiguo&pageurl=' + encodeURIComponent(pageurl) + '&referrerurl=' + encodeURIComponent(referrerurl) + "&Tags=" + Tags);
//});

$(function () {
    $('.areabox .close').click(function () {
        $('.areabox,.overlay').fadeOut();
    })
})




///***********/ 当vpn断掉时网站会打不开

//var IsShowLotteryCommodity = $("#ctl00_hidIsShowLotteryCommodity").val();
//var userid = getUserId();

//if (IsShowLotteryCommodity == "yes" && userid) {
//    $.getJSON("/Ajax/GetUserInfoByFiled.ashx", "userid=" + userid + "&filed=USERLEVEL", function (d) {
//        if (d) {
//            if (d == "4" || d == "5" || d == "6") {

//                var img = "<img src='/images/v2.png'/>";
//                //$("#tabcouple").find("tr.tag1 td.tag12").prepend(img);
//                $("#tabcouple").find("tr.tag1 td.tag12").each(function (i, obj) {
//                    if ($(obj).parent("tr.tag1").next().find("dl.coupon_pro").find("dd a").first().size() > 0) {
//                        var commoditycode = $(obj).parent("tr.tag1").next().find("dl.coupon_pro").find("dd a").first().attr("href");
//                        if (commoditycode.indexOf("10832") >= 0 || commoditycode.indexOf("10297") >= 0) {
//                            $(obj).prepend(img);
//                        }
//                    }
//                });
//            }
//        }
//    });
//}
///***********/