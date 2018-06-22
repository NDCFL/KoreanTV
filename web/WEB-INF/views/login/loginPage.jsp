<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/26
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6"><![endif]-->
<!--[if IE 7 ]><html class="ie ie7"><![endif]-->
<!--[if IE 8 ]><html class="ie ie8"><![endif]-->
<!--[if IE 9 ]><html class="ie9"><![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--><html class="standard"><!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0, minimal-ui" />
    <meta name="format-detection" content="email=no" />
    <meta name="format-detection" content="address=no" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="copyright" content="Copyright (c) zhipin.com" />
    <title>韩剧</title>
    <link href="https://login.zhipin.com/v2/web/geek/css/main.css" type="text/css" rel="stylesheet">
    <link rel="shortcut icon" href="<%=path%>/static/img/favicon.ico" >
    <link rel="icon" href="<%=path%>/static/img/favicon.ico">
</head>
<body class="page-sign">
<div id="wrap">
    <div id="header">
        <h1 class="logo"><a  ka="header-home-logo" title="韩剧"><img src="<%=path%>/static/img/logo.png" style="border-radius: 10px;width: 150px;height: 150px;margin-top: -10px" /></a></h1>
    </div>
    <div class="sign-wrap">
        <!--密码登录-->
        <div class="sign-form sign-pwd" id="pwd" style="display:block;">
            <h3 class="title">韩剧管理系统</h3>
            <div class="tip-error" style="margin-top:20px"></div>
            <!--<div class="sign-tab"><span class="link-signin cur" onclick="showlogin(0);">密码登录</span><span class="link-sms" onclick="showlogin(1);">短信登录</span><span class="link-scan" onclick="showlogin(2);">扫码登录</span></div>-->
            <form action="/login/account.json" method="post" style="margin-top:-35px">
                <div class="form-row">
                    <span class="ipt-wrap"><i class="icon-sign-pwd"></i><input type="text" class="ipt ipt-pwd required" ka="signin-text" placeholder="手机号/姓名/昵称" id="account" name="account" /></span>
                </div>
                <div class="form-row">
                    <span class="ipt-wrap"><i class="icon-sign-pwd"></i><input type="password" class="ipt ipt-pwd required" ka="signin-password" placeholder="密码" id="loginpassword" name="password" /></span>
                </div>
                <div class="form-row row-code">

                </div>
                <div class="form-btn">
                    <button type="button" id="login" class="btn">登录</button>
                </div>
                <div class="text-tip">没有账号 <a href="javascript:;" onclick="register();" class="link-signup">立即注册</a></div>
            </form>
        </div>
        <!--短信登录
        <div class="sign-form sign-sms" id="sms" style="display:none;">
          <h3 class="title">登录韩剧</h3>
          <div class="tip-error"></div>
          <div class="sign-tab"><span class="link-signin" onclick="showlogin(0);">密码登录</span><span class="link-sms cur" onclick="showlogin(1);">短信登录</span><span class="link-scan" onclick="showlogin(2);">扫码登录</span></div>
          <form action="/login/phone.json" method="post">
            <div class="form-row row-select">
              <span class="dropdown-select"><i class="icon-select-arrow"></i><em class="text-select">+86</em><input type="hidden" name="regionCode" value="+86" /></span>
              <span class="ipt-wrap"><i class="icon-sign-phone"></i><input type="tel" class="ipt ipt-phone required" ka="signin-account" placeholder="手机号" name="phone" /></span>
              <div class="dropdown-menu">
                <ul>
                  <li data-val="+86"><span class="num">+86</span>中国大陆</li>
                  <li data-val="+1"><span class="num">+1</span>美国</li>
                  <li data-val="+852"><span class="num">+852</span>香港</li>
                  <li data-val="+81"><span class="num">+81</span>日本</li>
                  <li data-val="+886"><span class="num">+886</span>台湾</li>
                  <li data-val="+44"><span class="num">+44</span>英国</li>
                  <li data-val="+82"><span class="num">+82</span>韩国</li>
                  <li data-val="+33"><span class="num">+33</span>法国</li>
                  <li data-val="+7"><span class="num">+7</span>俄罗斯</li>
                  <li data-val="+39"><span class="num">+39</span>意大利</li>
                  <li data-val="+65"><span class="num">+65</span>新加坡</li>
                  <li data-val="+63"><span class="num">+63</span>菲律宾</li>
                  <li data-val="+60"><span class="num">+60</span>马来西亚</li>
                  <li data-val="+64"><span class="num">+64</span>新西兰</li>
                  <li data-val="+34"><span class="num">+34</span>西班牙</li>
                  <li data-val="+95"><span class="num">+95</span>缅甸</li>
                  <li data-val="+230"><span class="num">+230</span>毛里求斯</li>
                  <li data-val="+263"><span class="num">+263</span>津巴布韦</li>
                  <li data-val="+20"><span class="num">+20</span>埃及</li>
                  <li data-val="+92"><span class="num">+92</span>巴基斯坦</li>
                  <li data-val="+880"><span class="num">+880</span>孟加拉国</li>
                </ul>
              </div>
            </div>
            <div class="form-row row-code">
              <span class="cell-wrap"><i class="icon-sign-code"></i><input type="text" class="ipt ipt-code" name="captcha" ka="signin-code" maxlength="4" placeholder="验证码" data-url="/captcha/randkey.json" /></span><span class="cell-wrap"><img src="/captcha/?randomKey=LQixG7KhX1hmM8xFloaNEgY4jgYmvJ6O" class="verifyimg"/></span>
              <input type="hidden" name="randomKey" class="randomkey" value="LQixG7KhX1hmM8xFloaNEgY4jgYmvJ6O"/>
            </div>
            <div class="form-row">
              <span class="ipt-wrap"><i class="icon-sign-sms"></i><input type="text" class="ipt ipt-sms required" ka="signup-sms" placeholder="短信验证码" name="phoneCode" maxlength="4" /><input type="hidden" name="smsType" value="1" /><button type="button" class="btn btn-sms" data-url="/registe/sendSms.json">发送验证码</button></span>
            </div>
            <div class="form-btn">
              <button type="submit" class="btn">登录</button>
            </div>
            <div class="text-tip">没有账号 <a href="javascript:;" onclick="register();" class="link-signup">立即注册</a></div>
          </form>
        </div>
        扫码登录
        <div class="sign-form sign-scan" id="scan" style="display:none;">
          <h3 class="title">登录韩剧</h3>
          <div class="tip-error"></div>
          <div class="sign-tab"><span class="link-signin" onclick="showlogin(0);">密码登录</span><span class="link-sms" onclick="showlogin(1);">短信登录</span><span class="link-scan cur" onclick="showlogin(2);">扫码登录</span></div>
          <div class="qrcode-box">
            <input type="hidden" class="uuid" value="bosszp-6f97bdfd-95ab-4e3b-b959-ac055f4368e9" />
            <p><span>使用 韩剧 APP 扫码登录</span><em>扫码帮助</em></p>
            <img src="https://login.zhipin.com/qrcode/bosszp-6f97bdfd-95ab-4e3b-b959-ac055f4368e9" data-url="https://www.zhipin.com/qrscan/dispatcher?qrId="/>
            <div class="qrcode-tip"><span class="gray">知道了</span>Boss现在也可以使用密码和短信登录了</div>
            <div class="hover-range-left"></div>
            <div class="hover-range-right"></div>
            <div class="sign-scan-help animation">
              <div class="scan-help-top">
                <ul>
                  <li class="active">我是BOSS</li>
                  <li>我是牛人</li>
                </ul>
              </div>
              <ul class="scan-help-down">
                <li><img src="https://login.zhipin.com/v2/web/geek/images/icon-help-boss.png"/></li>
                <li><img src="https://login.zhipin.com/v2/web/geek/images/icon-help-geek.png"/></li>
              </ul>
            </div>
          </div>
          <div class="text-tip">没有账号 <a href="javascript:;" onclick="register();" class="link-signup">立即注册</a></div>
        </div>-->
        <!--注册-->
        <div class="sign-form sign-register" id="register" style="display:none;">
            <h3 class="title">注册韩剧</h3>
            <div class="tip-error"></div>
            <div class="sign-tab">
                <span>使用手机号注册</span>
            </div>
            <form action="/registe/save.json" method="post">
                <input type="hidden" name="act" value="0">
                <input type="hidden" name="purpose" value="0">
                <div class="form-row row-select">
                    <span class="dropdown-select"><i class="icon-select-arrow"></i><em class="text-select">+86</em><input type="hidden" name="regionCode" value="+86" /></span>
                    <span class="ipt-wrap"><i class="icon-sign-phone"></i><input type="tel" class="ipt ipt-phone required" ka="signin-account" id="registerphone" placeholder="手机号" name="phone" /></span>
                    <div class="dropdown-menu">
                        <ul>
                            <li data-val="+86"><span class="num">+86</span>中国大陆</li>
                        </ul>
                    </div>
                </div>
                <div class="form-row row-code">
                    <span class="cell-wrap"><i class="icon-sign-code"></i><input type="text" class="ipt ipt-code" name="captcha" id="captcha" ka="signin-code" maxlength="4" placeholder="验证码" data-url="/captcha/randkey.json" /></span><span class="cell-wrap"><img src="${pageContext.request.contextPath }/code.jsp" id="codes" onclick="refresh();"  class="verifyimg"/></span>
                </div>
                <div class="form-row">
                    <span class="ipt-wrap"><i class="icon-sign-sms"></i><input type="text" class="ipt ipt-sms required" ka="signup-sms"  placeholder="短信验证码" name="dxode" id="dxcode" maxlength="4" /><button type="button" id="send"  class="btn btn-sms" onclick="settime(this)">发送验证码</button></span>
                </div>
                <div class="form-btn">
                    <button type="button" class="btn" id="registerbtn">注册</button>
                </div>
                <div class="text-tip"><div class="agreement-tip"><label><input type="checkbox" checked="checked" />同意韩剧 <a href="https://www.zhipin.com/register/protocol/introduce" target="_blank">用户协议及隐私政策</a></label></div>已有账号 <a href="javascript:;" onclick="login();" class="link-signin">直接登录</a></div>
            </form>
        </div>
    </div>
</div>
<script src="https://login.zhipin.com/v2/web/geek/js/lib/jquery-1.12.2.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<input type="hidden" id="page_key_name" value="cpc_user_sign_up" />
<script type="text/javascript">
    function register(){
        document.getElementById("pwd").style.display="none";
        document.getElementById("register").style.display="block";
        $(".tip-error").html("");
        //layer.alert('该功能暂时不开放', {icon: 6});
    }
    function login(){
        document.getElementById("pwd").style.display="block";
        document.getElementById("register").style.display="none";
        $(".tip-error").html("");
    }
    $("#login").click(function(){
        var loginphone = $("#account").val();
        var loginpassword = $("#loginpassword").val();
        if(loginphone==''){
            $(".tip-error").html("账户不能为空");
            return;
        }
        if(loginpassword==''){
            $(".tip-error").html("密码不能为空");
            return;
        }
        var load=layer.load(0, {shade: false});
        $.post(
            "/user/getInfo",
            {
                phone:loginphone,
                password:loginpassword
            },
            function(data) {
                layer.close(load);
                $(".tip-error").html(data.message);
                if(data.message.indexOf("成功")>-1){
                    location.href="<%=path%>/index";
                }
            },
            "json"
        );
    });
    $("#registerbtn").click(function(){
        var registerphone = $("#registerphone").val();
        var code = $("#captcha").val();
        var dxcode = $("#dxcode").val();
        if(!registerphone){
            $(".tip-error").html("账户不能为空");
            return;
        }
        if(!code){
            $(".tip-error").html("图形验证码不能为空");
            return;
        }
        if(!dxcode){
            $(".tip-error").html("短信验证码不能为空");
            return;
        }
    });
    var countdown=60;
    function settime(val) {
        var registerphone = $("#registerphone").val();
        var code = $("#captcha").val();
        if(!registerphone){
            $(".tip-error").html("账户不能为空");
            return;
        }
        if(!code){
            $(".tip-error").html("图形验证码不能为空");
            return;
        }
        $.post(
            "/user/getImgCode/"+$("#captcha").val(),
            function (data) {
                if(data.result=="fail"){
                    $(".tip-error").html("图形验证码输入错误");
                    document.getElementById("codes").src= '${pageContext.request.contextPath }/code.jsp?'+Math.random();
                    return;
                }else{
                    if (countdown == 0) {
                        val.removeAttribute("disabled");
                        val.value="发送验证码";
                        countdown = 60;
                    } else {
                        val.setAttribute("disabled", true);
                        val.value="重新发送(" + countdown + "s)";
                        countdown--;
                    }
                    setTimeout(function() {
                        settime(val)
                    },1000)
                }
            },
            "json"
        );
    }
</script>
<script type="text/javascript">
    function refresh(){
        document.getElementById("codes").src= '${pageContext.request.contextPath }/code.jsp?'+Math.random();
    }
</script>
</body>
</html>