<%--
  Created by IntelliJ IDEA.
  User: jb9
  Date: 2018/4/1
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>websocket client</title>
</head>
<script type="text/javascript">
    var ws;
    function hello(){
        ws = new WebSocket("ws://localhost:8089/send");
        ws.onopen = function(evn){
            console.log(evn);
        };
        ws.onmessage = function(evn){
            console.log(evn.data);
            var dv = document.getElementById("dv");
            dv.innerHTML+=evn.data;
        };
        ws.onclose = function(){
            console.log("关闭");
        };

    };
    function subsend(){
        var msg = document.getElementById("msg").value;
        ws.send(msg);
        document.getElementById("msg").value = "";
    }
</script>
<body>
<h2>Hello World!</h2>
<div id="dv" />
<input type="button" value="连接" onclick="hello()" />
<input type="text" id ="msg" /><input type="button" onclick="subsend()" value="发送" />
</body>
</html>