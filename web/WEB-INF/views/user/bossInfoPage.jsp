<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/28
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人资料的修改</title>
    <link href="<%=path%>/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/style.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/bootstrapValidator.css" rel="stylesheet">
    <base target="_blank">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">
                修改个人资料
            </a>
        </li>
        <li><a href="#ios" data-toggle="tab">修改密码</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="home">
            <div class="form-group">
            </div>
            <form class="form-horizontal" role="form" id="updateInfo">
                <input type="hidden" value="" name="id" id="id"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">手机号码：</label>
                    <div class="col-sm-4">
                        <input id="phone" name="phone" class="form-control" required  type="text" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户昵称：</label>
                    <div class="col-sm-4">
                        <input id="name" name="name" class="form-control" required type="text" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">真实名字：</label>
                    <div class="col-sm-4">
                        <input id="realname" name="realname" class="form-control" required type="text"  >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户年龄：</label>
                    <div class="col-sm-4">
                        <input id="age" name="age"  class="form-control" type="number">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">用户性别：</label>
                    <div class="col-sm-4">
                        <select class="form-control m-b" name="sex" id="sex">
                            <option value="0" id="gender0">男</option>
                            <option value="1" id="gender1">女</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3">
                        <button class="btn btn-primary col-sm-12" type="submit">修改</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="ios">
            <div class="form-group">
            </div>
            <form class="form-horizontal" role="form" id="updatePassWord">
                <div class="form-group">
                    <label class="col-sm-3 control-label">旧密码：</label>
                    <div class="col-sm-4">
                        <input id="password" name="password" placeholder="请输入旧密码" class="form-control" type="password" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">新密码：</label>
                    <div class="col-sm-4">
                        <input id="newpassword" name="newpassword" class="form-control" placeholder="请输入新密码" type="password"  >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">确认密码：</label>
                    <div class="col-sm-4">
                        <input id="qnewpassword" name="qnewpassword" class="form-control" type="password" placeholder="再次输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-3">
                        <button class="btn btn-primary  col-sm-12" type="submit">修改</button>
                        <a class="btn btn-primary col-sm-12" id="exit" style="display: none"  href="<%=path%>/user/exit">退出</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<%--<jsp:include page="../common/js.jsp"></jsp:include>--%>
<script src="<%=path%>/static/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/static/js/bootstrap.min.js?v=3.3.5"></script>
<script type="text/javascript" src="<%=path%>/static/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.formautoFill.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/pageJs/valid.js"></script>
</html>