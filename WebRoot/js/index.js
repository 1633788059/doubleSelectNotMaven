function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 170,
                resizable:false
            });
        }
        //关闭登录窗口
        function closePwd() {
            $('#w').window('close');
        }

        

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('updatepass?newpass=' + $newpass.val(), function(data) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + data.obj.pwd, 'info');
                $newpass.val('');
                $rePass.val('');
                closePwd();
                window.location.href="/doubleselect" ;
            },"json");
            
        }


$(function(){
	 $('#w').window('close');
	
	 $('#editpass').click(function() {
         $('#w').window('open');
     });

     $('#btnEp').click(function() {
         serverLogin();
     });

	$('#btnCancel').click(function(){closePwd();});
	
});

/**
 * 显示路径的页面
 * @param title
 * @param url
 */
function addTab(title, url){
	if ($('#tabs').tabs('exists', title)){
		$('#tabs').tabs('select', title);//选中并刷新
		var currTab = $('#tabs').tabs('getSelected');
		// 获取选中的标签页面板（tab panel）和它的标签页（tab）对象
		//var pp = $('#tt').tabs('getSelected');
		//var tab = pp.panel('options').tab; // 相应的标签页（tab）对象 
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != '导航主页') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			});
		}
	} else {
		var content = createFrame(url);
		$('#tabs').tabs('add',{
			title:title,
			content:content,
			closable:true
		});
	}
	tabClose();
}
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}
		
function tabClose() {
	/*双击关闭TAB选项卡*/
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close',subtitle);
	})
	/*为选项卡绑定右键*/
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tabs').tabs('select',subtitle);
		
		/**
		 * 实现“当前页右侧全部关闭”变灰不可用。
		 */
		var tabs = $('#tabs').tabs('tabs');   //获得所有的Tab选项卡 
		var tabcount = tabs.length; //Tab选项卡的个数 
		var lasttab = tabs[tabcount - 1]; //获得最后一个Tab选项卡 
		var lasttitle = lasttab.panel('options').tab.text(); //最后一个Tab选项卡的Title 
		var currtab_title = $('#mm').data("currtab"); //当前Tab选项卡的Title 
		  
		   if (lasttitle == currtab_title) { 
		     $('#mm-tabcloseright').attr("disabled", "disabled").css({ "cursor": "default", "opacity": "0.4" }); 
		   } 
		   else { 
		     $('#mm-tabcloseright').removeAttr("disabled").css({ "cursor": "pointer", "opacity": "1" }); 
		   }
		 
			/**
			 * 实现“当前页左侧全部关闭”变灰不可用。
			 */
		   var onetab = tabs[0]; //第一个Tab选项卡 
		   var onetitle = onetab.panel('options').tab.text(); //第一个Tab选项卡的Title 
		      if (onetitle == currtab_title) { 
		        $('#mm-tabcloseleft').attr("disabled", "disabled").css({ "cursor": "default", "opacity": "0.4" }); 
		      } 
		      else { 
		        $('#mm-tabcloseleft').removeAttr("disabled").css({ "cursor": "pointer", "opacity": "1" }); 
		      } 

		      /**
		       * 实现“除此之外全部关闭”变灰不可用。
		       */
		      var tabcount1 = $('#tabs').tabs('tabs').length; //tab选项卡的个数 
		      if (tabcount1 <= 1) { 
		        $('#mm-tabcloseother').attr("disabled", "disabled").css({ "cursor": "default", "opacity": "0.4" }); 
		      } 
		      else { 
		        $('#mm-tabcloseother').removeAttr("disabled").css({ "cursor": "pointer", "opacity": "1" }); 
		      } 

		
		return false;
	});
}		
//绑定右键菜单事件
function tabCloseEven() {
	//刷新
	$('#mm-tabupdate').click(function(){
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		if(url != undefined && currTab.panel('options').title != '导航主页') {
			$('#tabs').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url),
					closable:true
				}
			})
		}
		
	})
	//关闭当前
	$('#mm-tabclose').click(function(){
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close',currtab_title);
	})
	//全部关闭
	$('#mm-tabcloseall').click(function(){
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			if(t != '导航主页') {
				$('#tabs').tabs('close',t);
			}
		});
	});
	//关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		var nextall = $('.tabs-selected').nextAll();		
		if(prevall.length>0){
			prevall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != '导航主页') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		if(nextall.length>0) {
			nextall.each(function(i,n){
				var t=$('a:eq(0) span',$(n)).text();
				if(t != '导航主页') {
					$('#tabs').tabs('close',t);
				}
			});
		}
		return false;
	});
	//关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function(){
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length==0){
			msgShow('系统提示','后边没有啦~~','error');
			//alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});
	//关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function(){
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length==0){
			
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tabs').tabs('close',t);
		});
		return false;
	});

	//退出
	$("#mm-exit").click(function(){
		$('#mm').menu('hide');
	})
}

$(function() {
	tabCloseEven();//绑定右键鼠标事件

	$('.cs-navi-tab').click(function() {
		var $this = $(this);
		var href = $this.attr('src');
		var title = $this.text();
		addTab(title, href);
	});

	
	//点击设置主题
	var themes = {
		'bootstrap' : 'js/jslib/jquery-easyui-1.3.3/themes/bootstrap/easyui.css',
		'default' : 'js/jslib/jquery-easyui-1.3.3/themes/default/easyui.css',
		'sunny' : 'js/jslib/jquery-easyui-1.3.3/themes/sunny/easyui.css',
		'cupertino' : 'js/jslib/jquery-easyui-1.3.3/themes/cupertino/easyui.css',
	};

	var skins = $('.li-skinitem span').click(function() {
		var $this = $(this);
		if($this.hasClass('cs-skin-on')) return;
		skins.removeClass('cs-skin-on');
		$this.addClass('cs-skin-on');
		var skin = $this.attr('rel');
		
/*		js中获取json的值：
		var o = {"name":"value"}
		o.name和o["name"]都可以取到value的值

		var o = {"name":"value"},o.name和o["name"]都可以取到value的值
		ajax中处理返回的json字符串,将它转化为js对象：eval再加上两边的括号和引号，例：
		eval("("+xhr.responseText+")");
*/
		
		$('#swicth-style').attr('href', themes[skin]);
		setCookie('cs-skin', skin);
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
		window.location.href="/doubleselect";
	});

	if(getCookie('cs-skin')) {
		var skin = getCookie('cs-skin');
		$('#swicth-style').attr('href', themes[skin]);
		$this = $('.li-skinitem span[rel='+skin+']');
		$this.addClass('cs-skin-on');
		skin == 'dark-hive' ? $('.cs-north-logo').css('color', '#FFFFFF') : $('.cs-north-logo').css('color', '#000000');
	}
});


function setCookie(name,value) {//两个参数，一个是cookie的名子，一个是值
    var Days = 30; 				//此 cookie 将被保存 30 天
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {//取cookies函数        
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;
}

/*退出系统*/
function logout(){
	$.messager.confirm('对话框', '您确定要退出系统？', function(r){
		if (r){
			$.ajax({
	    		   type: "GET",
	    		   url: "logout",
	    		   success: function(result){
	    			   if(result){
	    				   window.location.href="/doubleselect";
	    			   }else {
	    		    	   $.messager.alert('提示',"操作失败！",'info');
	    			   }
	    		   },
	    		   error:function(result){
	    			   $.messager.alert('提示',"系统发生未知错误，请刷新再试！",'error');   	   
	    		   }
	    	});
		}
	});
}
