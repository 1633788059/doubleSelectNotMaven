//document.oncontextmenu=new Function("return false;");
var menucode_current="";
function KAF_WinOnLoad(theobj)
{	
	//��Ӧȫ���¼�
	//document.body.onselectstart=new Function("return false;");
	fillMenucode_current();
	//����˽�к���
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
	}else if(strID=="theRptArea") // ��һ��������Լ��ID(theRptArea)Ĭ��Ϊ��99%
		strWidth="99%";
			
	$(strID).style.width=strWidth;	
}

function SetReportHeight(strHeight,strXZZ,strID)
{
	strID=IsNull(strID,'frmReport');
	strHeight=IsNull(strHeight,'auto');	
	strXZZ=IsNull(strXZZ,"0");
	var h=strHeight;
	var xzz=parseInt(strXZZ)-25; //����ֵ

	if(strHeight.toLowerCase()==="auto"){
		h=document.documentElement.offsetHeight; //scrollHeight;
		h=h-$("theRptArea").offsetTop;  
		h=h+xzz;
	}
	
	var obj=$(strID);
	var isElement=obj && obj.nodeType && obj.nodeType==1;  
	if(!isElement) strID="theRptArea"; //��iframe�����ڣ���ת�Ƶ�div
	$(strID).style.height=h; //alert(h);
}

//����������ж�
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
// ����ַ������ֽ���
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
//ȥ���������˵Ŀո�
function Trim(str){return str.replace(/(^\s*)|(\s*$)/g,"");}
//ȥ����ߵĿո�
function LTrim(str){return str.replace(/(^\s*)/g,"");}
//ȥ���ұߵĿո�
function RTrim(str){return str.replace(/(\s*$)/g,"");}
//�ж��Ƿ�Ϊ��
function IsEmpty(str){return (Trim(str) == "");}
//---------------
// ��escape�������Ӣ��/���ֵ��ַ���
String.prototype.kingoEscape = function() {
    return escape(escape(this));
}

///////////////////////////////////////////////////////////////////////////////////////////////
// Util.js
// ����ʱ�䣺2008-5-15

//����idΪ���������һ����һ��ڵ����
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

//�������յ��ĵ����Ĳ���ת����һ��Array����
function $A(iterable) {
  if (!iterable) return [];
  if (iterable.toArray) return iterable.toArray();
  var length = iterable.length, results = new Array(length);
  while (length--) results[length] = iterable[length];
  return results;
}

//������ת���ɼ�ֵ���ַ���
function $H(object) {
	var cache = [];
	for(var key in object) {
		var type = typeof object[key];
		if(type == "string" || type == "number" || type == "boolean") cache.push(key + "=" + object[key]);
	}
	return cache.join("&");
}

//���ر�ǩΪ���������һ��ڵ����
function $T(element) {return document.getElementsByTagName(element);}

//����nameΪ���������һ��ڵ����
function $N(element) {return document.getElementsByName(element);}

//���ش���������ݿհ��ַ���ֺ������
function $w(string) {
  if (!Object.isString(string)) return [];
  string = string.strip();
  return string ? string.split(/\s+/) : [];
}

//���ڶ�����������еļ�ֵ����ӵ���һ����������в����䷵��
Object.extend = function(destination, source) {
  for (var property in source)
    destination[property] = source[property];
  return destination;
};

Object.extend(Object, {
  //������Ķ�������ļ��ļ��������鷽ʽ����
  keys: function(object) {
    var keys = [];
    for (var property in object)
      keys.push(property);
    return keys;
  },

  //��������������ֵ�ļ���������ķ�ʽ����
  values: function(object) {
    var values = [];
    for (var property in object)
      values.push(object[property]);
    return values;
  },

  //���ƴ���Ķ�����������䷵��
  clone: function(object) {
    return Object.extend({ }, object);
  },

  //�жϴ�������Ƿ��ǽڵ�Ԫ�أ��Ƿ���true,����Ϊfalse
  isElement: function(object) {
    return object && object.nodeType && object.nodeType == 1;
  },

  //�жϴ�������Ƿ���Array���ͣ��Ƿ���true,����Ϊfalse
  isArray: function(object) {
    return object && object.constructor === Array;
  },

  //�жϴ�������Ƿ���Function���ͣ��Ƿ���true,����Ϊfalse
  isFunction: function(object) {
    return typeof object == "function";
  },

  //�жϴ�������Ƿ���String���ͣ��Ƿ���true,����Ϊfalse
  isString: function(object) {
    return typeof object == "string";
  },

  //�жϴ�������Ƿ���Number���ͣ��Ƿ���true,����Ϊfalse
  isNumber: function(object) {
    return typeof object == "number";
  },

  //�жϴ�������Ƿ���Undefined���ͣ��Ƿ���true,����Ϊfalse
  isUndefined: function(object) {
    return typeof object == "undefined";
  }
});

Object.extend(String.prototype, {
  //���˵��ַ�����λ�Ŀհ��ַ�
  strip: function() {
    return this.replace(/^\s+/, '').replace(/\s+$/, '');
  },

  //���˵��ַ����еı�ǩ
  stripTags: function() {
    return this.replace(/<\/?[^>]+>/gi, '');
  },

  //���˵��ַ����е�script��ǩ�����е�script���
  stripScripts: function() {
    return this.replace(new RegExp('<script[^>]*>([\\S\\s]*?)<\/script>', 'img'), '');
  },

  //���ַ����ж��script��ǩ�е�script��������鷽ʽ����
  extractScripts: function() {
    var matchAll = new RegExp('<script[^>]*>([\\S\\s]*?)<\/script>', 'img');
    var matchOne = new RegExp('<script[^>]*>([\\S\\s]*?)<\/script>', 'im');
    return (this.match(matchAll) || []).map(function(scriptTag) {
      return (scriptTag.match(matchOne) || ['', ''])[1];
    });
  },
  
  //���ַ����ж��script��ǩ�е�script���ִ�У�����ִ�н�������鷽ʽ����
  evalScripts: function() {
    return this.extractScripts().map(function(script) { return eval(script) });
  },

  //���ַ�����ֳ��ַ�����
  toArray: function() {
    return this.split('');
  },

  //���ַ����ĵ�һ����ĸת���ɴ�д����������ĸת����Сд
  capitalize: function() {
    return this.charAt(0).toUpperCase() + this.substring(1).toLowerCase();
  },

  //�ж��ַ����Ƿ����������ַ������������򷵻�true������δfalse
  include: function(pattern) {
    return this.indexOf(pattern) > -1;
  },

  //�ж��ַ����Ƿ��Դ�����ַ���������ͷ�����򷵻�true������δfalse
  startsWith: function(pattern) {
    return this.indexOf(pattern) === 0;
  },

  //�ж��ַ����Ƿ��Դ�����ַ���������ͷ�����򷵻�true������δfalse
  endsWith: function(pattern) {
    var d = this.length - pattern.length;
    return d >= 0 && this.lastIndexOf(pattern) === d;
  },

  //�ж��ַ����Ƿ�δ�գ����򷵻�true������Ϊfalse
  empty: function() {
    return this == '';
  },

  //�ж��ַ����Ƿ�ֻ�����հ��ַ������򷵻�true������Ϊfalse
  blank: function() {
    return /^\s*$/.test(this);
  }
});

Object.extend(Array.prototype, {
  //���������е�Ԫ�أ���������Ϊ������������iterator
  each: function(iterator) {
    for (var i = 0, length = this.length; i < length; i++)
      iterator(this[i]);
  },
  
  //�ж������Ƿ��������Ĳ��������򷵻�true������Ϊfalse
  include: function(object) {
  	var found = false, len = this.size();
  	for(var i = 0; i < len; i++) {
  		if(this[i] == object) return found = true;
  	}
  	return found;
  },

  //���������
  clear: function() {
    this.length = 0;
    return this;
  },

  //��������ĵ�һ��Ԫ��
  first: function() {
    return this[0];
  },

  //������������һ��Ԫ��
  last: function() {
    return this[this.length - 1];
  },

  //���˵������е�null��undefinedԪ��
  compact: function() {
    var results = [];
    this.each(function(value) {
    	if(value) results.push(value);
    });
    return results;
  },

  //���˵��������ظ���Ԫ��
  uniq: function(sorted) {
    var results = [];
    this.each(function(value) {
    	if(!results.include(value)) results.push(value);
    });
    return results;
  },

  //��������ĸ�����
  clone: function() {
    return [].concat(this);
  },

  //��������ĳ���
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
var TIP_Mode = "0"; // ��Ϣģʽ��0,��Ƕ��1,������2,������

var MustBeSelected_Text = "��ѡ��%d��";
var MustBeInput_Text = "��¼��%d��";

var ErrFormat_Text = "�����%d��ʽ�����������룡";
var ErrDate_Beg2End_Text = "������������ڵ�����ʼ���ڣ�";
var ErrTime_Beg2End_Text = "����ʱ������ڵ�����ʼʱ�䣡";

var ConfirmSave_Text = "�Ƿ񱣴��¼��";
var ConfirmDelete_Text = "�Ƿ�ɾ��ѡ����¼��";

var DB_SaveFail_Text = "���ݴ洢ʧ�ܣ�";
var DB_RefuseDelete_Text = "%d�ѱ�����������ɾ����";

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
function MK_AlertSaveOK(pMode){_ShowTip("����ɹ���",pMode);}
function MK_AlertSaveOK(pMode){_ShowTip("����ʧ�ܣ�",pMode);}
function ts_getInnerText(el){
    if(el.innerText)return el.innerText;//IE�»�ȡ
    if(el.textContent)return el.textContent;//FF�»�ȡ
}
 //�������ӷ�����  
function FloatAdd(arg1,arg2){   //js�ӷ�
	var r1,r2,m;   
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}   
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}  
	m=Math.pow(10,Math.max(r1,r2));
	return FloatDiv((FloatMul(arg1, m) + FloatMul(arg2, m)), m);  
 }
 //��������������   
function FloatSub(arg1,arg2){   
	var r1,r2,m,n;   
	try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}   
	try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}   
	m=Math.pow(10,Math.max(r1,r2));   
	//��̬���ƾ��ȳ���   
	n=(r1>=r2)?r1:r2;   
	return (FloatDiv((FloatMul(arg1, m) - FloatMul(arg2, m)), m)).toFixed(n);     
}   
  
//�������˷�����   
function FloatMul(arg1,arg2){   
	var m=0,s1=arg1.toString(),s2=arg2.toString();   
	try{m+=s1.split(".")[1].length;}catch(e){}   
	try{m+=s2.split(".")[1].length;}catch(e){}   
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);   
}   
  
//��������������   
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
function kgonlyNumberAndDecimalAllowable(obj, e){//ֻ�����������ֺ�С���� 2012-06-21��
	if((e.keyCode<48||e.keyCode>57)&&e.keyCode!=46){
		e.returnValue=false;
	}
}
function kgonlyNumberAndDecimalAllowableTWO(obj, e){//ֻ�����������ֺ�С�������λ 2014-05-15��
	if((e.keyCode<48||e.keyCode>57)&&e.keyCode!=46){
		e.returnValue=false;
	}
	if((e.keyCode<48||e.keyCode>57)&&e.keyCode!=46){
		e.returnValue=false;
	}
	//������λ��Ч����
	if(obj.value.indexOf(".")!=-1){
		if(obj.value.length-obj.value.indexOf(".")>2){
			e.returnValue=false;
		}
	}
}
function kgonlyNumberAllowable(obj, e){//ֻ������������ 2012-06-21��
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
//����ǰ�����Ĳ˵�id��Main_index.jsp��ȡ�����õ���ҳ����е�����input��
function fillMenucode_current(){
	if(document.getElementsByTagName("form").length>0){
		var input = document.createElement("input");
		input.setAttribute("id", "menucode_current");
		input.setAttribute("name", "menucode_current");
		input.setAttribute("type", "hidden");
		menucode_current="";//���
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