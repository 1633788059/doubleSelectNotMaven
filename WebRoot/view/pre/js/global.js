//v1.0 2015-1-14 Time Lee 
//通用js存放处，修改请添加备注

var G = {};
G.DOMAIN = {
    WWW_YIGUO_COM: "www.yiguo.com",
    IMG1_YIGUO_COM: "img1.yiguo.com",
    STATIC_YIGUO_COM: "static.yiguo.com",
    ACTIVITY_YIGUO_COM: "activity.yiguo.com",
    SEARCH_YIGUO_COM: "search.yiguo.com",
    LIST_YIGUO_COM: "list.yiguo.com",
    ITEM_YIGUO_COM: "item.yiguo.com",
    SHOPPING_YIGUO_COM: "shopping.yiguo.com",
    LOGIN_YIGUO_COM: "login.utaste.com",
    IMG03_YIGUO_COM: "http://img03.yiguo.com",
    MEMBER_YIGUO_COM: "http://utaste.yiguo.com"
};
G.prefix = {
    static: "http://static.yiguo.com/"
};
G.createFnQueue = function (a) {
    var b = [];
    return {
        add: function (c) {
            if ($.isFunction(c)) {
                b.push(c);
            }
        },
        exec: function (e) {
            if (a !== false) {
                while (b.length > 0) {
                    b.shift()(e);
                }
            } else {
                var c = b.length;
                for (var d = 0; d < c; d++) {
                    if (b[d](e) === false) {
                        return false;
                    }
                }
            }
        },
        clear: function () {
            b.length = 0;
        }
    };
};
G.app = {};
G.logic = {};
G.ui = {};
G.util = {};

$.extend($, {
    strlen: function (a) {
        if (typeof a != "string") {
            return 0;
        }
        return a.replace(/[^\x00-\xff]/gi, "xx").length;
    }
});

G.logic.constants = {
    urlAjaxHandle: httpUrlOneCart + "/Scripts/AjaxHandle.aspx",
    urlJsonpcallback: httpUrlOneCart + "/Scripts/JsonpCallback.aspx",
    urlAddShopCartHandler: httpUrlOneCart + "/Ajax/AddShopCartHandler.ashx",
    //    urlShoppingCart: "localhost:45361/shopcart.aspx",
    //    urlShoppingCartEn: "localhost:45361/shopcartEn.aspx",
    urlShoppingCart: httpUrlCart + "/ShopCart.aspx",
    // "http://cart.yiguo.com/shopcart.aspx",
    urlShoppingCartEn: "http://cart.yiguo.com/shopcartEn.aspx",
    urlMyBooks: httpUrlYouYueHui2012 + "/MyBooks.aspx",
    urlCommodityDetail: httpUrl + "/CommodityDetail.aspx",
    urlCheckout: "Orderinfo.aspx"
};

G.logic.login = {
    IsLogin: function () {
        //alert(document.cookie);
        return G.util.ajax.getValue(G.logic.constants.urlAjaxHandle + "?datestamp=" + new Date().getTime(), { operate: "IsLogin" });
        //$.getJSON(G.logic.constants.urlAjaxHandle + "?datestamp=" + new Date().getTime() + "&jsoncallback=?"
    },
    LogOut: function (backUrl) {
        backUrl = backUrl || "";
        var strBackUrl = backUrl;
        var url = G.util.ajax.getValue(G.logic.constants.urlAjaxHandle + "?datestamp=" + new Date().getTime(), { operate: "IsLogout", backurl: strBackUrl });
        window.location.href = url;
    },
    LogOutWithBackUrl: function () {
        var strBackUrl = window.location.href;
        window.location = G.util.ajax.getValue(G.logic.constants.urlAjaxHandle + "?datestamp=" + new Date().getTime(), { operate: "IsLogout", backurl: strBackUrl });
    }
};

G.util.validation = {
    inputNumber: function (t, defaultValue) {
        var str = t.value;
        defaultValue = defaultValue || 0;
        if ($.trim(str) == "") {
            t.value = defaultValue;
            return false;
        }
        var letters = "1234567890";
        var i;
        var c;
        for (i = 0; i < str.length; i++) {
            c = str.charAt(i);
            if (letters.indexOf(c) == -1) {
                t.value = defaultValue;
                return false;
            }
        }
        return true;
    },
    isNumber: function (str) {
        if ($.trim(str) == "") {
            return false;
        }
        var letters = "1234567890";
        var i;
        var c;
        for (i = 0; i < str.length; i++) {
            c = str.charAt(i);
            if (letters.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    },
    IsNumberReg: function (obj) {
        //alert(obj.value);
        var val = obj.value;
        var parten = /^\+?[1-9][0-9]*$/; //正整数
        if (val == null || val == "") {
            alert("商品个数不能为空");
            obj.value = "1";
            return false;
        }
        if (parten.test(val)) {
            return true;
        }
        else {
            alert("只能输入整数");
            obj.value = "1";
            return false;
        }
    }
};

G.util.ajax = {
    getValue: function (url, data) {
        var a = "0";
        $.ajax({
            type: "GET",
            url: url,
            async: false,
            data: data,
            //            crossDomain: true,
            //            dataType: "jsonp",
            //            jsonpCallback: "loadLogin",
            success: function (d) {
                //                alert(a);
                //                alert(d);
                a = d;
                //                alert(a);
            },
            error: function () {
                alert("操作失败！");
            }
        });
        //        alert(a);
        return a;
    }
};

G.ui.popup = {
    showMsg: function (msg) {
        var success = msg.Success;
        var code = msg.MsgCode;
        var Name = msg.MsgName;
        var value = msg.MsgValue;
        var title = msg.MsgTitle;
        var body = msg.MsgBody;
        alert(body);
    }
};

G.app.User = {
    GetUserName: function () {
        var UserName = "";
        $.ajax({
            type: "GET",
            url: "/Ajax/Logout.ashx",
            async: false,
            dataType: "text",
            data: "datestamp=" + new Date().getTime() + "&para=getusername",
            success: function (d) {
                UserName = d;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert("操作失败!");
            }
        });

        return UserName;

    },
    GetCKUserID: function () {
        var UserID = "";
        $.ajax({
            type: "GET",
            url: "/Ajax/Logout.ashx",
            async: false,
            dataType: "text",
            data: "datestamp=" + new Date().getTime() + "&para=getckuserid",
            success: function (d) {
                UserID = d;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert("操作失败!");
            }
        });

        return UserID;

    },
    GetUserEcoupon: function () {
        var couponcount = "0";
        $.ajax({
            type: "GET",
            url: "/Ajax/Logout.ashx",
            async: false,
            dataType: "text",
            data: "datestamp=" + new Date().getTime() + "&para=getuserecoupon",
            success: function (d) {
                couponcount = d;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
                alert("操作失败!");
            }
        });

        return couponcount;

    },
    GetUserId: function () {
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "GetUserId" },
         function (d) {
             if (d.MsgSuccess == "1") {
                 return d.MsgValue;
             } else {
                 return "";
             }
         });
    }
};

G.app.itemDetail = {
    addGroupBuy: function (groupbuyid, num) {
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddGroupBuy", "GroupBuyId": groupbuyid, "num": num },
         function (d) {
             if (d.MsgSuccess == "1") {
                 G.app.itemDetail.freshTopCartWithDetailPromptJ();
                 if ($("#ms_popout").length > 0) {
                     var values = d.MsgValue.split('|');
                     if (values.length >= 2) {
                         $("#ms_popout").find("#bnum").text(values[0]);
                         $("#ms_popout").find("#sprice").text("￥" + values[1]);
                         $("#ms_popout").show();
                     }
                 } else
                     G.ui.popup.showMsg(d);
             } else {
                 alert(d.MsgValue);
             }
         });
    },
    addTuangouBuy: function (groupbuyid, num) {
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddTuangou", "GroupBuyId": groupbuyid, "num": num },
         function (d) {
             if (d.MsgSuccess == "1") {
                 G.app.itemDetail.freshTopCartWithDetailPromptJ();

                 //   G.ui.popup.showMsg(d);
             } else {
                 alert(d.MsgValue);
             }
         });
    },
    addToFavor: function (a) {
        var i = G.logic.login.IsLogin();
        //alert(i);
        if (i == "0") {
            alert("请先登录！"); //做登录处理
            return;
        }
        $.getJSON(G.logic.constants.urlAjaxHandle + "?datestamp=" + new Date().getTime() + "&callback=?", {
            operate: "AddCollection",
            CommodityId: a
        }, function (c) {
            //alert(c);
            if (c.result == "1") {
                alert("收藏成功！");
            } else if (c.result == "3") {
                alert("您之前已经收藏过该商品！");
            } else {
                alert("收藏失败！");
            }
        });
    },
    addToFavor1: function (a) {
        $.getJSON(G.logic.constants.urlAjaxHandle + "?datestamp=" + new Date().getTime() + "&callback=?", {
            operate: "AddCollection",
            CommodityId: a
        }, function (c) {
            //alert(c);
            if (c.result == "1") {
                alert("收藏成功！");
            } else if (c.result == "3") {
                alert("您之前已经收藏过该商品！");
            } else {
                alert("收藏失败！");
            }
        });
    },
    addToFavor2: function (a, type) {
        var i = G.logic.login.IsLogin();
        //alert(i);
        if (i == "0") {
            alert("请先登录！"); //做登录处理
            return;
        }
        $.getJSON(G.logic.constants.urlAjaxHandle + "?datestamp=" + new Date().getTime() + "&callback=?", {
            operate: "AddCollection",
            CommodityId: a,
            type: type
        }, function (c) {
            //alert(c);
            if (c.result == "1") {
                alert("收藏成功！");
            } else if (c.result == "3") {
                alert("您之前已经收藏过该商品！");
            } else {
                alert("收藏失败！");
                //alert(c.result);
            }
        });
    },

    doPurchaseInDetailPageCallbackOld: function (data) {
        if (data.length <= 0) return;
        var s = "<a href=\"#\" class=\"cart-close\" onclick=\"$(this).parent('.cart-info').fadeOut();\">×</a>";
        s += "<h2>已成功添加到购物车!</h2>";
        s += "<p>购物车共有<b>" + data[0].Amount + "</b>种商品，合计：<b>" + data[0].AllTotal + "</b>元</p>";
        s += "<p><a href=\"" + G.logic.constants.urlShoppingCart + "\" title=\"去结算\" class=\"settle_btn\"></a></p>";
        if ($("div.cart-info").size() > 0) $("div.cart-info").html(s).show();
        //$(".cart-close").click(function() {$(this).parent('.cart-info').fadeOut();
    },
    doPurchaseInDetailPageCallback: function (data) {
        if (data.length <= 0) return;
        var count = $("div#popout_detail").size();
        if (count <= 0) return;

        if (count == 1) {
            $("div#popout_detail").fadeIn();
            $("div#popout_detail dl.cart-popout p b").html(data[0].Amount);
            $("div#popout_detail dl.cart-popout p strong span").html(data[0].AllTotal);
        } else {
            $($("div#popout_detail")[0]).fadeIn();
            $($("div#popout_detail")[0]).find("dl.cart-popout p b").html(data[0].Amount);
            $($("div#popout_detail")[0]).find("dl.cart-popout p strong span").html(data[0].AllTotal);
        }
    },
    doPurchaseInDetailPageCurr: function (o, commodityId, num, type) {//加入单件商品到购物车。[成功跳转到购物车]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCart", "commodityId": commodityId, "num": num, "type": type }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartWithDetailPromptJ(); //刷新站顶小购物车，同时弹出标签[div.cart-info]提示页面。
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseInDetailPage: function (o, commodityId, num, type) {//加入单件商品到购物车。[成功跳转到购物车]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCart", "commodityId": commodityId, "num": num, "type": type }, function (d) {
            if (d.MsgSuccess == "1") {
                //商品详细页操作成功后处理直接跳转至购物车
                window.location = G.logic.constants.urlShoppingCart;
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    AddGanBingInShopCart: function (commodityId) {//InShopCart 加干冰。[成功跳转到购物车]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCart", "commodityId": commodityId, "num": 1, "type": 1 }, function (d) {
            if (d.MsgSuccess == "1") {
                //商品详细页操作成功后处理直接跳转至购物车
                window.location = G.logic.constants.urlShoppingCart;
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    AddEManInShopCart: function (o, PromotionId, num, type) {//InShopCart 加额满。[成功跳转到购物车]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCart", "commodityId": PromotionId, "num": 1, "type": 4 }, function (d) {
            if (d.MsgSuccess == "1") {
                window.location = G.logic.constants.urlShoppingCart;
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    AddManEHuanGou: function (o, PromotionId, num, type, directJump, funback) {//InShopCart 加额满。[成功跳转到购物车]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddManEHuanGou", "PromotionId": PromotionId, "num": num, "type": 4 }, function (d) {
            if (d.MsgSuccess == "1") {
                if (directJump)
                { window.location = G.logic.constants.urlShoppingCart; }
                else
                {
                    G.app.itemDetail.freshTopCartWithDetailPromptJ();
                    if (funback != "") {
                        funback();
                    }
                }

            } else {
                if (d.MsgBody == "购物车为空，请先添加商品！") {
                    alert('年中大促！请先购物满200元后，于此页面选择赠品！');
                }
                else {
                    G.ui.popup.showMsg(d);
                }
            }
        });
    },
    //GetManEHuanGou: function (obj) {// 加额满。[返回剩余换购金额]
    //    $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "GetManEHuanGou" }, function (d) {
    //       // return d;
    //        if (d.MsgSuccess == "1") { 
    //         // G.ui.popup.showMsg(d);
    //         $("#"+obj).val(MsgBody);

    //        } else {
    //          //  G.ui.popup.showMsg(d);
    //           $("#"+obj).val(MsgBody);
    //        }
    //    });
    //},
    doPurchaseInDetailPageJ: function (o, commodityId, num, type) {//加入单件商品到购物车。[成功弹出提示]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCart", "commodityId": commodityId, "num": num, "type": type }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartJ();
                G.ui.popup.showMsg(d);
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseInDetailPageJ2: function (o, commodityId, num, type) {//加入单件商品到购物车。[成功弹出提示]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCart", "commodityId": commodityId, "num": num, "type": type }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartWithDetailPromptJ(); //刷新站顶小购物车，同时弹出标签[div.popout]提示页面。
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseJ: function (o, commodityId, num, type, directJump, refreshTopCart, noPrompt) {//加入单件商品到购物车。【全站通用】
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCart", "commodityId": commodityId, "num": num, "type": type }, function (d) {
            if (d.MsgSuccess == "1") {
                if (directJump) {
                    window.location = G.logic.constants.urlShoppingCart;
                } else {
                    if (refreshTopCart) G.app.itemDetail.freshTopCartJ();
                    if (noPrompt) {
                    } else {
                        G.ui.popup.showMsg(d);
                    }
                }
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseJ2: function (o, commodityId, num, type, directJump, refreshTopCart, noPrompt, isenglish) {//特用于泡沫干冰的添加
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCart", "commodityId": commodityId, "num": num, "type": type }, function (d) {
            if (d.MsgSuccess == "1") {
                if (directJump) {
                    if (isenglish == "1") {
                        window.location.reload();
                        //                        window.location = G.logic.constants.urlShoppingCartEn+"?stamp="+ new Date().getTime();
                    } else {
                        window.location.reload();
                        //                        window.location = G.logic.constants.urlShoppingCart + "?stamp=" + new Date().getTime();
                    }
                } else {
                    if (refreshTopCart) G.app.itemDetail.freshTopCartJ();
                    if (noPrompt) {
                    } else {
                        G.ui.popup.showMsg(d);
                    }
                }
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseListJ: function (o, commodityId, num) {//列表页加入商品到购物车，弹出提示。【普通,特惠，买即送：共用方法】|页面必须含有弹出标签：div.cart-info
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCartList", "commodityId": commodityId, "num": num }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartWithDetailPromptJ();
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseMultiListJ: function (commodityIds) {//列表页加入商品到购物车，弹出提示。【普通,特惠，买即送：共用方法】|页面必须含有弹出标签：div.cart-info
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMultiToCartList", "commodityId": commodityIds }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartWithDetailPromptJ();
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseListJ2: function (o, commodityId, num) {//列表页加入商品到购物车，弹出提示。【普通,特惠，买即送：共用方法】|页面不含有弹出标签：div.cart-info
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCartList", "commodityId": commodityId, "num": num }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartJ();
                G.ui.popup.showMsg(d);
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseListJ3: function (o, commodityCode, num) {//列表页加入商品到购物车，弹出提示。【普通,特惠，买即送：共用方法】|页面不含有弹出标签：div.cart-info 传CommodityCode
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCartList", "commoditycode": commodityCode, "num": num }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartJ();
                G.ui.popup.showMsg(d);
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseListJ4: function (o, groupBuyId) {//试吃 传groupBuyId
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddGroupBuy4Shichi", "groupBuyId": groupBuyId }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartJ();
                G.ui.popup.showMsg(d);
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseListJ5: function (o, commodityCode, num, couponcode) {//列表页加入商品到购物车，弹出提示。【普通,特惠，买即送：共用方法】|页面不含有弹出标签：div.cart-info 传CommodityCode
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCartList", "commoditycode": commodityCode, "num": num }, function (d) {
            if (d.MsgSuccess == "1") {
               // window.location = httpUrlCart + "/orderinfo.aspx" + "?ECouponCode=" + couponcode;  //"http://cart.yiguo.com/orderinfo.aspx" + "?ECouponCode=" + couponcode;
                window.location = httpUrlCart + "/orderinfo.aspx?hd=1";
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseListJ6: function (o, commodityCode, num, couponcode) {//列表页加入商品到购物车，弹出提示。【普通,特惠，买即送：共用方法】|页面不含有弹出标签：div.cart-info 传CommodityCode
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToCartList", "commoditycode": commodityCode, "num": num }, function (d) {
            if (d.MsgSuccess == "1") {
                window.location = httpUrlCart + "/orderinfo.aspx" + "?ECouponCode=" + couponcode;  //"http://cart.yiguo.com/orderinfo.aspx" + "?ECouponCode=" + couponcode;
               // window.location = httpUrlCart + "/orderinfo.aspx?hd=1";
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMultiplePurchaseInDetailPageCurr: function (commodityIds) {//一次加入多个商品入购物车。[成功弹出指定提示]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMultipleToCart1", "commodityIds": commodityIds }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartWithDetailPromptJ();
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMultiplePurchaseInDetailPage: function (commodityIds) {//一次加入多个商品入购物车。[成功跳转到购物车]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMultipleToCart1", "commodityIds": commodityIds }, function (d) {
            if (d.MsgSuccess == "1") {
                //商品详细页操作成功后处理直接跳转至购物车
                window.location = G.logic.constants.urlShoppingCart;
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMultiplePurchaseInDetailPageJ: function (commodityIds) {//一次加入多个商品入购物车。[成功弹出alert提示]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMultipleToCart1", "commodityIds": commodityIds }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartJ();
                G.ui.popup.showMsg(d);
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMultiplePurchaseJ: function (o, commodityIds, directJump, refreshTopCart, noPrompt) {//一次加入多件商品到购物车。【全站通用】。格式：commodity|type|num,commodity|type|num,commodity|type|num
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMultipleToCart1", "commodityIds": commodityIds }, function (d) {
            if (d.MsgSuccess == "1") {
                if (directJump) {
                    window.location = G.logic.constants.urlShoppingCart;
                } else {
                    if (refreshTopCart) G.app.itemDetail.freshTopCartJ();
                    if (noPrompt) {
                    } else {
                        G.ui.popup.showMsg(d);
                    }
                }
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMultiplePurchaseJ2: function (o, commodityIds, directJump, refreshTopCart, noPrompt) {//一次加入多件商品到购物车。【全站通用】。格式：commodity|type|num,commodity|type|num,commodity|type|num
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMultipleToCart1", "commodityIds": commodityIds }, function (d) {
            if (d.MsgSuccess == "1") {
                if (directJump) {
                    window.location = G.logic.constants.urlCheckout;
                } else {
                    if (refreshTopCart) G.app.itemDetail.freshTopCartJ();
                    if (noPrompt) {
                    } else {
                        G.ui.popup.showMsg(d);
                    }
                }
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMjsPurchaseInDetailPageCurr: function (o, commodityId, num, type, promotionsId) {//加入买即送到购物车。[成功弹出指定提示]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMjsToCart", "commodityId": commodityId, "num": num, "type": type, "promotionsId": promotionsId }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartWithDetailPromptJ();
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMjsPurchaseInDetailPage: function (o, commodityId, num, type, promotionsId) {//加入买即送到购物车。[成功跳转到购物车]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMjsToCart", "commodityId": commodityId, "num": num, "type": type, "promotionsId": promotionsId }, function (d) {
            if (d.MsgSuccess == "1") {
                //商品详细页操作成功后处理直接跳转至购物车
                window.location = G.logic.constants.urlShoppingCart;
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMjsPurchaseInDetailPageJ: function (o, commodityId, num, type, promotionsId) {//加入买即送到购物车。[成功弹出alert提示]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMjsToCart", "commodityId": commodityId, "num": num, "type": type, "promotionsId": promotionsId }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartJ();
                G.ui.popup.showMsg(d);
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doMjsPurchaseJ: function (o, commodityId, num, type, promotionsId, directJump, refreshTopCart, noPrompt) {//加入买即送到购物车。[全站通用]
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMjsToCart", "commodityId": commodityId, "num": num, "type": type, "promotionsId": promotionsId }, function (d) {
            if (d.MsgSuccess == "1") {
                if (directJump) {
                    window.location = G.logic.constants.urlShoppingCart;
                } else {
                    if (refreshTopCart) G.app.itemDetail.freshTopCartJ();
                    if (noPrompt) {
                        G.app.itemDetail.freshTopCartWithDetailPromptJ();
                    } else {
                        G.ui.popup.showMsg(d);
                    }
                }
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doBookOneJ: function (o, commodityId, num) {//预订商品【单件】
        //        if (G.logic.login.IsLogin() == "0") {
        //            alert("请先登录！"); //做登录处理.非易果网站删除登录部分，分别放在各自的网站自己处理。
        //            return;
        //        }
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToBookCart", "commodityId": commodityId, "num": num }, function (d) {
            if (d.MsgSuccess == "1") {
                //商品详细页操作成功后处理直接跳转至后台预订页面
                window.location = G.logic.constants.urlMyBooks;
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doBookOneJ1: function (o, commodityId, num) {//预订商品【单件】
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddToBookCart", "commodityId": commodityId, "num": num }, function (d) {
            if (d.MsgSuccess == "1") {
                //商品详细页操作成功后处理直接跳转至后台预订页面
                window.location = G.logic.constants.urlMyBooks;
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doBookMultipleJ: function (commodityIds) {//预订商品【多件】
        if (G.logic.login.IsLogin() == "0") {
            alert("请先登录！"); //做登录处理
            return;
        }
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddMultipleToBookCart", "commodityIds": commodityIds }, function (d) {
            if (d.MsgSuccess == "1") {
                //商品详细页操作成功后处理直接跳转至购物车
                window.location = G.logic.constants.urlMyBooks;
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doExchangedJ: function (o, pointExchangeId, num, directJump, refreshTopCart, noPrompt) {//积分兑换|换购
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddExchangedToCart", PointExchangeId: pointExchangeId, Num: num }, function (d) {
            if (d.MsgSuccess == "1") {
                if (directJump) {
                    window.location = G.logic.constants.urlShoppingCart;
                } else {
                    if (refreshTopCart) G.app.itemDetail.freshTopCartJ();
                    if (noPrompt) {
                    } else {
                        G.ui.popup.showMsg(d);
                    }
                }
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doExchangedJWithPromp: function (o, pointExchangeId, num) {//积分兑换|换购 弹出指定提示（商品详细页）
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddExchangedToCart", PointExchangeId: pointExchangeId, Num: num }, function (d) {
            if (d.MsgSuccess == "1") {
                G.app.itemDetail.freshTopCartWithDetailPromptJ();
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    doPurchaseZHTH: function (o, promotionsId, num, directJump, refreshTopCart, noPrompt) {//组合特惠
        $.getJSON(G.logic.constants.urlJsonpcallback + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { operate: "AddZHTHToCart", PromotionsId: promotionsId, Num: num }, function (d) {
            if (d.MsgSuccess == "1") {
                if (directJump) {
                    window.location = G.logic.constants.urlShoppingCart;
                } else {
                    if (refreshTopCart) G.app.itemDetail.freshTopCartJ();
                    if (noPrompt) {
                        G.app.itemDetail.freshTopCartWithDetailPromptJ();
                    } else {
                        G.ui.popup.showMsg(d);
                    }
                }
            } else {
                G.ui.popup.showMsg(d);
            }
        });
    },
    AddInShopCart: function (commodityId, sOperate, sType, func) {//在购物车页面+-update商品
        $.getJSON(G.logic.constants.urlAddShopCartHandler + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { CommodityId: commodityId, Operate: sOperate, Type: sType }, function (carts) {
            func(carts);
        });
    },
    UpdateEManInShopCart: function (o, PromotionId, num, func) {//修改购物车页面的额满商品数量。ajax刷新页面
        $.getJSON(G.logic.constants.urlAddShopCartHandler + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { "CommodityId": PromotionId, "Operate": num, "Type": 3 }, function (carts) {
            func(carts);
        });
    },
    DeleteInShopCart: function (o, commodityId, func) {//购物车删除商品。ajax刷新页面
        $.getJSON(G.logic.constants.urlAddShopCartHandler + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { "CommodityId": commodityId, "Type": 2 }, function (carts) {
            func(carts);
        });
    },
    deleteTopCart: function (commodityId) {//删除易果小购物车商品。【已作废】
        $.getJSON(G.logic.constants.urlAddShopCartHandler + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { CommodityId: commodityId, Type: '2' }, function (carts) { G.app.itemDetail.freshTopCart(carts); });
    },
    deleteTopCartJ: function (commodityId) {//删除易果小购物车商品
        $.getJSON(G.logic.constants.urlAddShopCartHandler + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { CommodityId: commodityId, Type: '2' }, function (carts) {
            G.app.itemDetail.freshTopCart(carts);
            $(".cartbox").show(); //删除后然后显示下拉的内容.
        });
    },
    freshTopCartJ: function () {//显式刷新易果小购物车
        $.getJSON(G.logic.constants.urlAddShopCartHandler + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { Type: 'show' }, function (carts) { G.app.itemDetail.freshTopCart(carts); });
    },
    freshTopCartWithDetailPromptJ: function () {//显式刷新易果小购物车+商品详细页提示
        $.getJSON(G.logic.constants.urlAddShopCartHandler + "?datestamp=" + new Date().getTime() + "&jsoncallback=?", { Type: 'show' }, function (carts) {
            G.app.itemDetail.doPurchaseInDetailPageCallback(carts);
            G.app.itemDetail.freshTopCart(carts);
        });
    },
    freshTopCart: function (carts) { //易果小购物车刷新[前提：保持易果和优悦会的头部小购物车样式html相同]
        if ($("dl.cartbar").size() <= 0) return;
        var htmlCart = "";

        if (carts != "" && carts != null) {
            htmlCart += "<dt><a href='" + G.logic.constants.urlShoppingCart + "'><span>" + carts[0].Amount + "</span></a></dt>";
            htmlCart += "<dd><div class=\"cartbox\"><div class=\"cartbox-list\"><p class=\"total\">目前选购商品共<b>" + carts[0].Amount + "</b>件</p>";

            $.each(carts, function (i, cart) {
                htmlCart += "<dl>";
                htmlCart += "<dt class=\"pic\"><a href='" + G.logic.constants.urlCommodityDetail + "?CommodityId=" + cart.PointExchangeId + "'><img alt=\"商品名称\" width=\"50px\" height=\"50px\" src='" + cart.PictureName + "'/></a></dt>";
                htmlCart += "<dd class=\"name\"><a href='" + G.logic.constants.urlCommodityDetail + "?CommodityId=" + cart.PointExchangeId + "'>" + cart.CommodityName + "</a></dd>";
                htmlCart += "<dd class=\"price\"><b>￥：" + cart.Price + " * " + cart.Num + "</b>&nbsp;&nbsp;<b>悠币：" + cart.UsedPoint + "</b></dd>";
                htmlCart += "<dd class=\"del\"><a href=\"javascript:void(0);\" onclick=\"G.app.itemDetail.deleteTopCartJ('" + cart.CommodityId + "');\" id='" + cart.CommodityId + "'>删除</a></dd>";
                htmlCart += "</dl>";
            });

            htmlCart += "<p class=\"price-total\"><a href='" + G.logic.constants.urlShoppingCart + "' class=\"settleup\">立刻结算</a> 共<b>" + carts[0].Amount + "</b>件商品 &nbsp;&nbsp;&nbsp;&nbsp;总计<b>" + carts[0].AllTotal + "</b>元</p></div></div></dd>";
        } else {
            htmlCart += "<dt><a href='" + G.logic.constants.urlShoppingCart + "'><span>" + 0 + "</span></a></dt>";
            htmlCart += "<dd><div class=\"cartbox\"><div class=\"cartbox-list\"><p class=\"total\">目前选购商品共<b>" + 0 + "</b>件</p>";
            htmlCart += "<p class=\"price-total\"><a href='" + G.logic.constants.urlShoppingCart + "' class=\"settleup\">立刻结算</a> 共<b>" + 0 + "</b>件商品 &nbsp;&nbsp;&nbsp;&nbsp;总计<b>" + 0.00 + "</b>元</p></div></div></dd>";
        }
        $("dl.cartbar").html(htmlCart);
    }
};