//document.oncontextmenu=new Function("return false;");
var menucode_current="";
function KAF_WinOnLoad(theobj)
{	
	//响应全局事件
	//document.body.onselectstart=new Function("return false;");
	fillMenucode_current();
	//调用私有函数
	try{MyWinOnLoad(theobj);}catch(errWinOnLoad){}
}


function SetHtmlTitle(strTitle,strFlag,strID)
{
	strID=IsNull(strID,'divTitle');
	if(strFlag=="0"||strFlag=="2") document.title=strTitle;
	if(strFlag=="1"||strFlag=="2") $(strID).innerHTML=strTitle;
}

function SetReportWidth(strWidth,strID)
{	
	strID=IsNull(strID,'theRptArea');
	strWidth=IsNull(strWidth,'99%');
	
	if(strWidth.toLowerCase()==="center"){
		strWidth="760px";
	}else if(strID=="theRptArea") // 单一报表区的约定ID(theRptArea)默认为：99%
		strWidth="99%";
			
	$(strID).style.width=strWidth;	
}

function SetReportHeight(strHeight,strXZZ,strID)
{
	strID=IsNull(strID,'frmReport');
	strHeight=IsNull(strHeight,'auto');	
	strXZZ=IsNull(strXZZ,"0");
	var h=strHeight;
	var xzz=parseInt(strXZZ)-25; //修正值

	if(strHeight.toLowerCase()==="auto"){
		h=document.documentElement.offsetHeight; //scrollHeight;
		h=h-$("theRptArea").offsetTop;  
		h=h+xzz;
	}
	
	var obj=$(strID);
	var isElement=obj && obj.nodeType && obj.nodeType==1;  
	if(!isElement) strID="theRptArea"; //若iframe不存在，则转移到div
	$(strID).style.height=h; //alert(h);
}

//浏览器类型判断
var _browser=function(s){return navigator.userAgent.toLowerCase().indexOf(s)!=-1}; 
var isFF=(!document.all),isOpera=_browser('opera'),isIE=_browser('msie')!=-1&&(document.all&&!isOpera);

//-----------------------------------------------------
//[ex.] IKSetCookie("uid","1234",{expireDays:7});
IKSetCookie=function(name,value,option){
	var _path="/";
	var _domain="dev.kingosoft.com";

	var str=name+"="+escape(value);
	if(option){
		if(option.expireDays){var date=new Date();var ms=option.expireDays*24*3600*1000;date.setTime(date.getTime()+ms);str+="; expires="+date.toGMTString()}
		else if(option.expire){str+="; expires="+option.expire};

		if(option.path){str+="; path="+path;}
		else{str+="; path="+_path;}

		if(option.domain){str+="; domain="+domain;}
		else{str+="; domain="+_domain;}

		if(option.secure)str+="; true"
	};
	document.cookie=str;
}
IKGetCookie=function(name){
	var cookieArray=document.cookie.split("; ");
	var cookie=new Object();
	for(var i=0;i<cookieArray.length;i++){
		var arr=cookieArray[i].split("=");
		if(arr[0]==name)return unescape(arr[1]);
	};
	return"";
}	
IKMoveCookie=function(name){
IKSetCookie(name,"",{expireDays:-1});
}

//-------------------------------------------------------
var __file_initlist="" 
function AppendFile(fn, fid){
	var el;
	var ft='js';
	if (fn.indexOf('.css')!=-1) ft='css';

	if (ft=="css"){
   		el=document.createElement("link");
   		el.setAttribute("id",fid);
   		el.setAttribute("rel", "stylesheet");
   		el.setAttribute("type", "text/css");  
   		el.setAttribute("href", fn);
	}else{
   		el=document.createElement("script");
   		el.setAttribute("id",fid);
   		el.setAttribute("type","text/javascript");
   		el.setAttribute("src", fn);
	}

	if (typeof(el)!="undefined"){
    		if (__file_initlist.indexOf("["+fn+"]")==-1){
    		document.getElementsByTagName("head")[0].appendChild(el);
		__file_initlist+="["+fn+"]";
		}
	}
}

function AddStyle(obj,css,isAppend){
	var _isAppend=isAppend?isAppend:false;

	if(_isAppend){
	(obj.style)?obj.style.cssText+=css:obj.setAttribute("style",obj.getAttribute("style")+css);
	}else{
	(obj.style)?obj.style.cssText =css:obj.setAttribute("style",css);
	}
}

function BandingEvent(elm,evType, fnCall){
	var obj = document.getElementById(elm)||document;

	if (obj.attachEvent){
		return obj.attachEvent("on" + evType, fnCall);        	
    	}else if(obj.addEventListener){
		obj.addEventListener(evType, fnCall, false);
        	return true;
    	}
}

//function $(theid){return document.getElementById(theid);}
function IsNull(strID,defValue){
if(typeof(strID)=="undefined"||strID.replace(/(^\s*)|(\s*$)/g,"")=="") strID=defValue;return (strID);
}

//---------------
String.prototype.Trim=function(){return this.replace(/(^\s*)|(\s*$)/g,'');}
// 获得字符串的字节数
String.prototype.getByteCount = function() {
    var realLength = 0, len = this.length, charCode = -1;
    for (var i = 0; i < len; i++) {
        charCode = this.charCodeAt(i);
        if (charCode >= 0 && charCode <= 128) {
            realLength += 1;
        } else {
            realLength += 2;
        }
    }
    return realLength;
}
//去除左右两端的空格
function Trim(str){return str.replace(/(^\s*)|(\s*$)/g,"");}
//去除左边的空格
function LTrim(str){return str.replace(/(^\s*)/g,"");}
//去除右边的空格
function RTrim(str){return str.replace(/(\s*$)/g,"");}
//判断是否为空
function IsEmpty(str){return (Trim(str) == "");}
//---------------
// 用escape来处理非英文/数字的字符串
String.prototype.kingoEscape = function() {
    return escape(escape(this));
}

///////////////////////////////////////////////////////////////////////////////////////////////
// Util.js
// 更新时间：2008-5-15

//返回id为传入参数的一个或一组节点对象
function $(element) {
  if (arguments.length > 1) {
    for (var i = 0, elements = [], length = arguments.length; i < length; i++)
      elements.push($(arguments[i]));
    return elements;
  }
  if (Object.isString(element))
    element = document.getElementById(element);
  return element;
}

//将它接收到的单个的参数转换成一个Array对象
function $A(iterable) {
  if (!iterable) return [];
  if (iterable.toArray) return iterable.toArray();
  var length = iterable.length, results = new Array(length);
  while (length--) results[length] = iterable[length];
  return results;
}

//将对象转换成键值对字符串
function $H(object) {
	var cache = [];
	for(var key in object) {
		var type = typeof object[key];
		if(type == "string" || type == "number" || type == "boolean") cache.push(key + "=" + object[key]);
	}
	return cache.join("&");
}

//返回标签为传入参数的一组节点对象
function $T(element) {return document.getElementsByTagName(element);}

//返回name为传入参数的一组节点对象
function $N(element) {return document.getElementsByName(element);}

//返回传入参数根据空白字符拆分后的数组
function $w(string) {
  if (!Object.isString(string)) return [];
  string = string.strip();
  return string ? string.split(/\s+/) : [];
}

//将第二个对象参数中的键值对添加到第一个对象参数中并将其返回
Object.extend = function(destination, source) {
  for (var property in source)
    destination[property] = source[property];
  return destination;
};

Object.extend(Object, {
  //将传入的对象参数的键的集合以数组方式返回
  keys: function(object) {
    var keys = [];
    for (var property in object)
      keys.push(property);
    return keys;
  },

  //将传入对象参数的值的集合以数组的方式返回
  values: function(object) {
    var values = [];
    for (var property in object)
      values.push(object[property]);
    return values;
  },

  //复制传入的对象参数并将其返回
  clone: function(object) {
    return Object.extend({ }, object);
  },

  //判断传入参数是否是节点元素，是返回true,否则为false
  isElement: function(object) {
    return object && object.nodeType && object.nodeType == 1;
  },

  //判断传入参数是否是Array类型，是返回true,否则为false
  isArray: function(object) {
    return object && object.constructor === Array;
  },

  //判断传入参数是否是Function类型，是返回true,否则为false
  isFunction: function(object) {
    return typeof object == "function";
  },

  //判断传入参数是否是String类型，是返回true,否则为false
  isString: function(object) {
    return typeof object == "string";
  },

  //判断传入参数是否是Number类型，是返回true,否则为false
  isNumber: function(object) {
    return typeof object == "number";
  },

  //判断传入参数是否是Undefined类型，是返回true,否则为false
  isUndefined: function(object) {
    return typeof object == "undefined";
  }
});

Object.extend(String.prototype, {
  //过滤掉字符串首位的空白字符
  strip: function() {
    return this.replace(/^\s+/, '').replace(/\s+$/, '');
  },

  //过滤掉字符串中的标签
  stripTags: function() {
    return this.replace(/<\/?[^>]+>/gi, '');
  },

  //过滤掉字符串中的script标签及其中的script语句
  stripScripts: function() {
    return this.replace(new RegExp('<script[^>]*>([\\S\\s]*?)<\/script>', 'img'), '');
  },

  //将字符串中多个script标签中的script语句以数组方式返回
  extractScripts: function() {
    var matchAll = new RegExp('<script[^>]*>([\\S\\s]*?)<\/script>', 'img');
    var matchOne = new RegExp('<script[^>]*>([\\S\\s]*?)<\/script>', 'im');
    return (this.match(matchAll) || []).map(function(scriptTag) {
      return (scriptTag.match(matchOne) || ['', ''])[1];
    });
  },
  
  //将字符串中多个script标签中的script语句执行，并将执行结果以数组方式返回
  evalScripts: function() {
    return this.extractScripts().map(function(script) { return eval(script) });
  },

  //将字符串拆分成字符数组
  toArray: function() {
    return this.split('');
  },

  //将字符串的第一个字母转换成大写，其它的字母转换成小写
  capitalize: function() {
    return this.charAt(0).toUpperCase() + this.substring(1).toLowerCase();
  },

  //判读字符串是否包含传入的字符串参数，是则返回true，否则未false
  include: function(pattern) {
    return this.indexOf(pattern) > -1;
  },

  //判断字符串是否以传入的字符串参数开头，是则返回true，否则未false
  startsWith: function(pattern) {
    return this.indexOf(pattern) === 0;
  },

  //判断字符串是否以传入的字符串参数开头，是则返回true，否则未false
  endsWith: function(pattern) {
    var d = this.length - pattern.length;
    return d >= 0 && this.lastIndexOf(pattern) === d;
  },

  //判断字符串是否未空，是则返回true，否则为false
  empty: function() {
    return this == '';
  },

  //判断字符串是否只包含空白字符，是则返回true，否则为false
  blank: function() {
    return /^\s*$/.test(this);
  }
});

Object.extend(Array.prototype, {
  //遍历数组中的元素，并将其作为参数传给函数iterator
  each: function(iterator) {
    for (var i = 0, length = this.length; i < length; i++)
      iterator(this[i]);
  },
  
  //判断数组是否包含传入的参数，是则返回true，否则为false
  include: function(object) {
  	var found = false, len = this.size();
  	for(var i = 0; i < len; i++) {
  		if(this[i] == object) return found = true;
  	}
  	return found;
  },

  //将数组清空
  clear: function() {
    this.length = 0;
    return this;
  },

  //返回数组的第一个元素
  first: function() {
    return this[0];
  },

  //返回数组的最后一个元素
  last: function() {
    return this[this.length - 1];
  },

  //过滤掉数组中的null或undefined元素
  compact: function() {
    var results = [];
    this.each(function(value) {
    	if(value) results.push(value);
    });
    return results;
  },

  //过滤掉数组中重复的元素
  uniq: function(sorted) {
    var results = [];
    this.each(function(value) {
    	if(!results.include(value)) results.push(value);
    });
    return results;
  },

  //返回数组的复制体
  clone: function() {
    return [].concat(this);
  },

  //返回数组的长度
  size: function() {
    return this.length;
  }
});


var Try = {
  these: function() {
    var returnValue;

    for (var i = 0, length = arguments.length; i < length; i++) {
      var lambda = arguments[i];
      try {
        returnValue = lambda();
        break;
      } catch (e) { }
    }

    return returnValue;
  }
};


///////////////////////////////////////////////////////////////////////////////////////////////////////
var TIP_Mode = "0"; // 信息模式：0,内嵌；1,弹出；2,悬浮。

var MustBeSelected_Text = "需选定%d！";
var MustBeInput_Text = "须录入%d！";

var ErrFormat_Text = "错误的%d格式，请重新输入！";
var ErrDate_Beg2End_Text = "结束日期需大于等于起始日期！";
var ErrTime_Beg2End_Text = "结束时间需大于等于起始时间！";

var ConfirmSave_Text = "是否保存记录？";
var ConfirmDelete_Text = "是否删除选定记录？";

var DB_SaveFail_Text = "数据存储失败！";
var DB_RefuseDelete_Text = "%d已被关联，不能删除！";

function FormatText(pMarkID, pItemName) {
	var s = '';
	eval("s=" + pMarkID + "_Text");
	s = s.replace('%d', pItemName);
	return s;
}

function Alert_MustBeInput(l_strLabel, l_objInput) {
	var s = FormatText('MustBeInput', l_strLabel);
	_ShowTip(s);
	l_objInput.select();
}

function Alert_ErrFormat(pItemName, pTheObj) {
	var s = FormatText('ErrFormat', pItemName);
	_ShowTip(s);
	pTheObj.select();
	return false;
}

function _ShowTip(pMsg,pMode) {
	if (TIP_Mode == "1") {
		alert(pMsg);
	} else {
		document.getElementById("divTip").innerHTML = pMsg;
		// setTimeout("_clearTip()",3000);
	}
}
function _clearTip() {
	document.getElementById("divTip").innerHTML = '';
}

function IKWin_ShowTip(pMsg,pMode) {_ShowTip(pMsg,pMode);}
function MK_AlertSaveOK(pMode){_ShowTip("保存成功！",pMode);}
function MK_AlertSaveOK(pMode){_ShowTip("保存失败！",pMode);}
function ts_getInnerText(el){
    if(el.innerText)return el.innerText;//IE下获取
    if(el.textContent)return el.textContent;//FF下获取
}
 //浮点数加法运算  
function FloatAdd(arg1,arg2){   //js加法
	var r1,r2,m;   
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}   
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}  
	m=Math.pow(10,Math.max(r1,r2));
	return FloatDiv((FloatMul(arg1, m) + FloatMul(arg2, m)), m);  
 }
 //浮点数减法运算   
function FloatSub(arg1,arg2){   
	var r1,r2,m,n;   
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}   
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}   
	m=Math.pow(10,Math.max(r1,r2));   
	//动态控制精度长度   
	n=(r1>=r2)?r1:r2;   
	return (FloatDiv((FloatMul(arg1, m) - FloatMul(arg2, m)), m)).toFixed(n);     
}   
  
//浮点数乘法运算   
function FloatMul(arg1,arg2){   
	var m=0,s1=arg1.toString(),s2=arg2.toString();   
	try{m+=s1.split(".")[1].length;}catch(e){}   
	try{m+=s2.split(".")[1].length;}catch(e){}   
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);   
}   
  
//浮点数除法运算   
function FloatDiv(arg1,arg2){   
	var t1=0,t2=0,r1,r2;   
	try{t1=arg1.toString().split(".")[1].length;}catch(e){}   
	try{t2=arg2.toString().split(".")[1].length;}catch(e){}   
	with(Math){   
		r1=Number(arg1.toString().replace(".",""));   
		r2=Number(arg2.toString().replace(".",""));   
		return (r1/r2)*pow(10,t2-t1);   
	}   
}
function kgonlyNumberAndDecimalAllowable(obj, e){//只允许输入数字和小数点 2012-06-21加
	if((e.keyCode<48||e.keyCode>57)&&e.keyCode!=46){
		e.returnValue=false;
	}
}
function kgonlyNumberAndDecimalAllowableTWO(obj, e){//只允许输入数字和小数点后两位 2014-05-15加
	if((e.keyCode<48||e.keyCode>57)&&e.keyCode!=46){
		e.returnValue=false;
	}
	if((e.keyCode<48||e.keyCode>57)&&e.keyCode!=46){
		e.returnValue=false;
	}
	//保留两位有效数字
	if(obj.value.indexOf(".")!=-1){
		if(obj.value.length-obj.value.indexOf(".")>2){
			e.returnValue=false;
		}
	}
}
function kgonlyNumberAllowable(obj, e){//只允许输入数字 2012-06-21加
	if((e.keyCode<48||e.keyCode>57)){
		e.returnValue=false;
	}
}
function request(paras) { 
	var url = location.href; 
	var paraString = url.substring(url.indexOf("?")+1,url.length).split("&"); 
	var paraObj = {} 
	for (i = 0; j = paraString[i]; i++) { 
	    paraObj[j.substring(0,j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=")+1,j.length); 
	} 
	var returnValue = paraObj[paras.toLowerCase()]; 
	if(typeof(returnValue) == "undefined") { 
	    return ""; 
	} else { 
	    return returnValue; 
	} 
}
//将当前操作的菜单id从Main_index.jsp中取出放置到本页面表单中的隐藏input中
function fillMenucode_current(){
	if(document.getElementsByTagName("form").length>0){
		var input = document.createElement("input");
		input.setAttribute("id", "menucode_current");
		input.setAttribute("name", "menucode_current");
		input.setAttribute("type", "hidden");
		menucode_current="";//清空
		if(parent.window.document.getElementById("menucode_current")){
			menucode_current=parent.window.document.getElementById("menucode_current").value;
			
		}
		else if(parent.parent.window.document.getElementById("menucode_current")){
			menucode_current=parent.parent.window.document.getElementById("menucode_current").value;
			//input.setAttribute("value", parent.parent.window.document.getElementById("menucode_current").value);
		}
		input.setAttribute("value", menucode_current);
		document.getElementsByTagName("form")[0].appendChild(input);
	}
}