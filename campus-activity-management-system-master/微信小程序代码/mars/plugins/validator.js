
//数字
function isDigits(str) {
    return /^\d+$/.test(str)
}

//最多保留两位小数的数字
function fmtDigits(str) {
    return /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(str)
}

//字母
function isLetters(str) {
    return /^[a-z]+$/i.test(str)
}

//日期（yyyyyyy-mm-dd）
function isDate(str) {
    return /^\d{4}-\d{2}-\d{2}$/.test(str)
}

//时间 00:00到23:59
function isTime(str) {
    return /^([01]\d|2[0-3])(:[0-5]\d){1,2}$/.test(str)
}

//邮箱
function isEmail(str) {
    return /^[\w\+\-]+(\.[\w\+\-]+)*@[a-z\d\-]+(\.[a-z\d\-]+)*\.([a-z]{2,4})$/i.test(str)
}

//Url地址
function isUrl(str) {
    return /^(https?|s?ftp):\/\/\S+$/i.test(str)
}

//QQ号码
function isQQ(str) {
    return /^[1-9]\d{4,}$/.test(str)
}

//身份证号
function isIDCard(str) {
    return /^[1-9]\d{4,}$/.test(str)
}

//座机号
function isTel(str) {
    return /^(?:(?:0\d{2,3}[\- ]?[1-9]\d{6,7})|(?:[48]00[\- ]?[1-9]\d{6}))$/.test(str)
}

//手机号
function isMobile(str) {
    return /^1[3-9]\d{9}$/.test(str)
}

//邮政编码
function isZipCode(str) {
    return /^\d{6}$/.test(str)
}

//中文字符
function isChinese(str) {
    return /^[\u0391-\uFFE5]+$/.test(str)
}

//用户名 3-12位数字、字母、下划线
function isUsrUsrName(str) {
    return /^\w{3,12}$/.test(str)
}

//密码 6-16位字符，不能包含空格
function isPassWd(str) {
    return /^[\S]{6,16}$/.test(str)
}

//6-16个由数字、字母组成的用户名
function isUsrName(str) {
    return /^[A-Za-z0-9]{6,16}$/.test(str)
}

//校验返回true/false
module.exports = {
    isDigits: isDigits,  //数字
    isLetters: isLetters, //字母
    isDate: isDate, //日期
    isTime: isTime, //时间
    isEmail: isEmail, //邮箱
    isUrl: isUrl, //URL地址
    isQQ: isQQ, //QQ
    isTel: isTel, // 固定号码
    isIDCard: isIDCard, //身份证号
    isMobile: isMobile, //手机号
    isZipCode: isZipCode, //邮编
    isChinese: isChinese, //中文字符
    isUsrUsrName: isUsrUsrName, //用户名
    isPassWd: isPassWd, //密码
    fmtDigits: fmtDigits,//最多保留两位小数的正数
    isUsrName: isUsrName,//6-16个由数字、字母组成的用户名
}
