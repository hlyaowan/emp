function randomNextInt(lowerValue, upperValue){
    var choices = upperValue - lowerValue + 1;
    return Math.floor(Math.random()*choices + lowerValue);
}

//整除，相当于java中的/
function zhengchu(temp1,temp2){
    return Math.floor(temp1/temp2);
}

//微信分享
function shareFriend() {
    WeixinJSBridge.invoke("sendAppMessage", {appid: appid, img_url: imgUrl, img_width: "321", img_height: "306", link: lineLink, desc: descContent, title: shareTitle}, function (e) {
    })
}
function shareTimeline() {
    WeixinJSBridge.invoke("shareTimeline", {img_url: imgUrl, img_width: "321", img_height: "306", link: lineLink, desc: descContent, title: shareTitle}, function (e) {
    })
}
function shareWeibo() {
    WeixinJSBridge.invoke("shareWeibo", {img_url: imgUrl, content: shareTitle + " " + descContent, url: lineLink}, function (e) {
    })
}

function changeShareTitle(isWin, costTime){
    if(isWin){
        shareTitle = "我脱裤子只要"+costTime+"秒，你行吗？";
    }
    else{
        shareTitle = "谁能帮我脱掉这2B的裤子，我以身相许。";
    }
}

var imgUrl = 'http://182.254.141.150:9001/catchpangci/images/icon.png';
var lineLink = 'http://182.254.141.150:9001/catchpangci/index.html';
var descContent = "抓住溅男，脱掉他的裤子，看看谁脱裤子的速度最快！";//朋友圈不显示      分享好友时为内容
var shareTitle = "我脱裤子只要XX秒，你行吗？";//朋友圈在内容区显示     分享好友时为标题
var appid = "";
document.addEventListener("WeixinJSBridgeReady", function () {
    WeixinJSBridge.on("menu:share:appmessage", function (e) {
        shareFriend()
    });
    WeixinJSBridge.on("menu:share:timeline", function (e) {
        shareTimeline()
    });
    WeixinJSBridge.on("menu:share:weibo", function (e) {
        shareWeibo()
    });
//    if (HORIZONTAL == true) {
//        WeixinJSBridge.call("hideToolbar")
//    }
}, false);

window.addEventListener("resize", function (a) {
    LGlobal.resize();
}, !1);
//window.addEventListener("orientationchange", function (a) {
//    LGlobal.resize();
//}, !1);




