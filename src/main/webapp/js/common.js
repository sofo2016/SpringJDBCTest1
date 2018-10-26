$("body > div > div.layui-side > div > ul > li:nth-child(1)").click(function () {
    window.location.href = "smartDevice.html";
});
$("body > div > div.layui-side > div > ul > li:nth-child(2)").click(function () {
    window.location.href = "configControl.html";
});
$("body > div > div.layui-side > div > ul > li:nth-child(3)").click(function () {
    window.location.href = "hotelInfo.html";
});

$("body > div > div.layui-header > div.layui-logo").click(function () {
    window.location.href = "ecloudB.html?s=true";
});


(function () {
    if ("WebSocket" in window) {
        console.log("WebSocket is supported by your Browser!");
        var windowPathName = window.location.pathname;
        var lastIndexOfSlash = windowPathName.lastIndexOf("/");
        var wsprex;

        if (windowPathName.lastIndexOf("/") > 1) {
            wsprex = windowPathName.substring(0, lastIndexOfSlash);
        } else {
            wsprex = '';
        }

        // Let us open a web socket
        var ws = new WebSocket("ws:" + window.location.origin.substring(5) + wsprex +
            "/socketServer/" + windowPathName.substring(lastIndexOfSlash + 1, windowPathName.indexOf(".html")));

        ws.onopen = function () {

            // Web Socket is connected, send data using send()
            ws.send("Message to send");

            console.log("Message is sent...");
        };

        ws.onmessage = function (evt) {
            var received_msg = evt.data;
            //console.log(received_msg);
            console.log("received_msg");
            $("#warningRow > button:nth-child(1)").css("background-color", "red");
        };

        ws.onclose = function () {
            console.log("Connection is closed...");
        };
    }

}());


layui.use(['element', 'laypage', 'laytpl', 'laytpl', 'layedit'], function () {
    var laypage = layui.laypage
        , laytpl = layui.laytpl
        , $ = layui.$;

    laypage.smartDeviceCommon = {
        getDivNode: function (e) {
            var divNode;
            var target = e.target;
            if (target.nodeName === "DIV") {
                divNode = target;
            } else if (target.nodeName === "I") {
                divNode = target.parentElement;
            } else if (target.nodeName === "EM") {
                divNode = target.parentElement;
            } else {
            }
            return divNode;
        },
        switchDevice: function (e) {
            var divNode = laypage.smartDeviceCommon.getDivNode(e);
            divNode.classList.toggle('layui-form-onswitch');
            if(divNode.parentNode.tagName =="TD"){
                var rmno = divNode.parentNode.children[1].innerText;
                var action = "jdqoff";
                //toggle lamp
                var PowerBacParam = {};
                PowerBacParam["rmno"]=rmno;
                PowerBacParam["action"]=action;
                $.ajax({
                    url: 'portal/electric/power/operate',
                    type: 'POST',
                    headers: {
                        'content-type': 'application/json;charset=utf-8'
                    },
                    data :JSON.stringify(PowerBacParam),
                    dataType: "json",        //返回数据形式为json
                    success: function(result){
                        console.log();
                    },
                    error: function(errorMsg){
                        console.log("error");
                    }
                });
            }
        }
    }


    var active = {
        offset: function (othis) {
            //杭电警告信息
            $.ajax({
                type: 'get',
                url: 'portal/electric/power/list',//请求数据的地址
                dataType: "json",        //返回数据形式为json
                success: function (result) {
                    var electwarningData = result.data;

                    for (var j = 0, len = electwarningData.length; j < len; j++) {
                        var date = new Date(electwarningData[j].createTime);
                        var Y = date.getFullYear() + '-';
                        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                        var D = date.getDate() + ' ';
                        var h = date.getHours() + ':';
                        var m = date.getMinutes() + ':';
                        var s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
                        electwarningData[j]["createTime"] = Y + M + D + h + m + s;
                    }
                    var type = othis.data('type')
                        , text = othis.text();
                    var content;
                    var getTpl = warning.innerHTML;
                    thisData = electwarningData.concat();
                    laytpl(getTpl).render(thisData, function (html) {
                        content = html;
                    });
                    layer.open({
                        title: false,
                        skin: 'electric-warning-class',
                        area: ['600px', '400px'],
                        skin: 'electricity-class',
                        type: 1
                        , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        , id: 'electricityLayer' //防止重复弹出
                        , content: content
                        , yes: function () {
                            layer.closeAll();
                        }
                    });
                    $(".layui-layer-move").remove();
                    $("#electricityLayer > table > tbody > tr > td:nth-child(5) > div").click(laypage.smartDeviceCommon.switchDevice);
                    $("#electricityLayer > div.layui-layer-title > img").click(function () {
                        location.reload();
                    });

                }
            });
        },
        FacialRecog: function (othis) {
            //刷脸失败警告信息
            $.ajax({
                type: 'post',
                url: 'portal/doorAccess/getAccessMessage',//请求数据的地址
                headers: {
                    'content-type': 'application/json;charset=utf-8'
                },
                dataType: "json",        //返回数据形式为json
                success: function (result) {
                    var securityData = result.data;
                    var content;
                    var getTpl = warning4FacialRecognition.innerHTML;
                    var thisData = securityData.concat().slice(0,6);
                    laytpl(getTpl).render(thisData, function (html) {
                        content = html;
                    });
                    layer.open({
                        title: false,
                        skin: 'security-warning-class',
                        area: ['600px', '400px'],
                        skin: 'electricity-class',
                        type: 1
                        , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        , id: 'securityLayer' //防止重复弹出
                        , content: content
                        , yes: function () {
                            layer.closeAll();
                        }
                    });
                    $(".layui-layer-move").remove();
                    $("#securityLayer > div.layui-layer-title > img").click(function () {
                        location.reload();
                    });

                }
            });
        }
    };

    $('#warningRow > button:nth-child(1)').on('click', function () {
        $("#warningRow > button:nth-child(1)").css("background-color", "white");
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

    $('#warningRow > button:nth-child(2)').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});

