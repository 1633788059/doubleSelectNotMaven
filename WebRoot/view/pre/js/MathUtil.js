//浮点数相加

function dcmAdd(arg1, arg2) {

    var r1, r2, m;

    try { r1 = arg1.toString().split(".")[1].length; } catch (e) { r1 = 0; }

    try { r2 = arg2.toString().split(".")[1].length; } catch (e) { r2 = 0; }

    m = Math.pow(10, Math.max(r1, r2));

    return (dcmMul(arg1, m) + dcmMul(arg2, m)) / m;

}

//浮点数相减

function dcmSub(arg1, arg2) {

    return dcmAdd(arg1, -arg2);

}

//浮点数相乘

function dcmMul(arg1, arg2) {

    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();

    try { m += s1.split(".")[1].length; } catch (e) { }

    try { m += s2.split(".")[1].length; } catch (e) { }

    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);

}

//浮点数除

function dcmDiv(arg1, arg2) {

    return dcmMul(arg1, 1 / arg2);

}


Number.prototype.add = function (arg) {

    return dcmAdd(this, arg);

}

Number.prototype.sub = function (arg) {

    return dcmSub(this, arg);

}


Number.prototype.mul = function (arg) {

    return dcmMul(this, arg);

}

Number.prototype.div = function (arg) {

    return dcmDiv(this, arg);

}

//功能：保留2位小数:将浮点数四舍五入，取小数点后2位，如果不足2位则补0,这个函数返回的是字符串的格式 
//用法：changeTwoDecimal(3.1415926) 返回 3.14 
//changeTwoDecimal(3.1) 返回 3.10 
function changeTwoDecimal_f(x) {
    var f_x = parseFloat(x);
    if (isNaN(f_x)) {
        alert('function:changeTwoDecimal->parameter error');
        return false;
    }
    var f_x = Math.round(x * 100) / 100;
    var s_x = f_x.toString();
    var pos_decimal = s_x.indexOf('.');
    if (pos_decimal < 0) {
        pos_decimal = s_x.length;
        s_x += '.';
    }
    while (s_x.length <= pos_decimal + 2) {
        s_x += '0';
    }
    return s_x;
} 

