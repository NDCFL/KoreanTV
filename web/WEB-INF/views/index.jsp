<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/19
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>韩剧后台管理系统</title>
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <jsp:include page="common/css.jsp"></jsp:include>
    <link rel="shortcut icon" href="<%=path%>/static/img/favicon.ico" >
    <link rel="icon" href="<%=path%>/static/img/favicon.ico">
    <link href="<%=path%>/static/js/layui/css/layui.css" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element" style="text-align: center">
                        <span><img alt="${userVo.realname}" id="imgs" class="img-circle" src="${userVo.faceImg}" style="width: 64px;height: 64px" /></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs">${userRole.roleVo.title}</span>
                                <span class="text-muted text-xs block">${userVo.realname}<b class="caret"></b></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li>
                                <a id="img_s">修改头像</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="<%=path%>/user/bossInfoPage">个人资料</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="<%=path%>/user/exit">安全退出</a>
                            </li>
                        </ul>
                    </div>
                    <div class="logo-element">蓝
                    </div>
                </li>
                <li>
                    <a href="javascript:void(0);">
                        <i class="glyphicon glyphicon-home"></i>
                        <span class="nav-label">基本信息</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="<%=path%>/user/bossInfoPage">个人信息</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<%=path%>/user/exit">安全退出</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="glyphicon glyphicon-user"></i> <span class="nav-label">用户管理 </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="/appUser/appUserPage">用户列表</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/userNews/userNewsPage">用户朋友圈</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/userFriend/userFriendPage">用户关注</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="<%=path%>/advert/advertPage" class="J_menuItem" >
                        <i class="glyphicon glyphicon-list-alt"></i>
                        <span class="nav-label">广告管理</span>
                    </a>
                </li>
                <li>
                    <a href="#"><i class="glyphicon glyphicon-film"></i> <span class="nav-label">剧管理 </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="/vedioModule/vedioModulePage">剧模块管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/vedioType/vedioTypePage">剧类型管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/vedio/vedioPage">剧管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/vedioSection/vedioSectionPage">剧集管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/vedioDiscuss/vedioDiscussPage">剧评论</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/vedioLookHistory/vedioLookHistoryPage">剧观看历史</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/barrage/barragePage">剧弹幕管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/downHistory/downHistoryPage">下载管理</a>
                        </li>
                        <%--<li>--%>
                            <%--<a class="J_menuItem" href="/file/upPage">上传视频</a>--%>
                        <%--</li>--%>
                    </ul>
                </li>
                <shiro:hasAnyRoles name="管理员">
                <li>
                    <a href="#"><i class="glyphicon glyphicon-lock"></i> <span class="nav-label">权限管理 </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="J_menuItem" href="/user/userPage">员工管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/role/rolePage">角色管理</a>
                        </li>
                        <li>
                            <a class="J_menuItem" href="/userRole/userRolePage">用户角色管理</a>
                        </li>
                    </ul>
                </li>
                </shiro:hasAnyRoles>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                        </div>
                    </form>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="<%=path%>/user/main" class="active J_menuTab" target="iframe0"  data-id="main.jsp">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="<%=path%>/user/exit" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="<%=path%>/user/main" frameborder="0" data-id="main.jsp" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2017-2018 <a href="http://www.zi-han.net/" target="_blank">韩剧</a>
            </div>
        </div>
    </div>
    <input type="text" id="imgss"  />
    <!--右侧部分结束-->
<jsp:include page="common/js.jsp"></jsp:include>
<script type="text/javascript" src="<%=path%>/static/js/layui/layui.js" charset="utf-8"></script>
</body>
<script>

    if(localStorage.getItem("img")){
        document.getElementById("imgs").src=localStorage.getItem("img");
    }
    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#imgs'
            , url: '/user/up'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    console.log(result);
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }else{
                   localStorage.removeItem("img");
                   localStorage.setItem("img",res.data.src);
                   document.getElementById("imgs").src=res.data.src;
                   layer.alert("修改成功!", {icon: 1});
                }
                //上传成功
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    });
    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#img_s'
            , url: '/user/up'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo_1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }else{
                    localStorage.removeItem("img");
                    localStorage.setItem("img",res.data.src);
                    document.getElementById("imgs").src=res.data.src;
                    layer.alert("修改成功!", {icon: 1});
                }
                //上传成功
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    });
    var websocket;

    // 首先判断是否 支持 WebSocket
    if('WebSocket' in window) {
        websocket = new WebSocket("ws://192.168.1.5:8080/sendOne?user=${userVo.id}");
    } else if('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://192.168.1.5:8080/sendOne?user=${userVo.id}");
    } else {
        websocket = new SockJS("http://192.168.1.5:8080/sendAll?user=${userVo.id}");
    }

    // 打开连接时
    websocket.onopen = function(evnt) {
        console.log("  websocket.onopen  ");
    };

    // 收到消息时
    websocket.onmessage = function(evnt) {
        alert(evnt.data);
    };

    websocket.onerror = function(evnt) {
        console.log("  websocket.onerror  ");
    };

    websocket.onclose = function(evnt) {
        layer.alert("登录已失效，请重新登录!", {icon: 1},function () {
            location.href="/index";
        });
    };
</script>
</html>