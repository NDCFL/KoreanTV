<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/19
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>员工列表</title>
    <jsp:include page="../common/bootstraptablecss.jsp"></jsp:include>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>员工列表</h5>
        </div>
        <div class="ibox-content">
            <div class="panel panel-default">
                <div class="panel-heading">
                    查询条件
                </div>
                <div class="panel-body form-group" style="margin-bottom:0px;">
                    <%--<div class="panel-body form-group" style="margin-bottom:0px;">--%>
                        <%--<label class="col-sm-1 control-label" style=" margin-top:5px">创建时间</label>--%>
                        <%--<div class="col-sm-2" >--%>
                            <%--<input type="text" class="form-control" placeholder="注册时间"  name="createTime" id="test_2"/>--%>
                        <%--</div>--%>
                        <%--<label class="col-sm-1 control-label" style="padding-top: 7px">昵称</label>--%>
                        <%--<div class="col-sm-2">--%>
                            <%--<input type="text" name="name" id="userName" placeholder="昵称" class="form-control" />--%>
                        <%--</div>--%>
                        <%--<label class="col-sm-1 control-label" style="padding-top: 7px">内容</label>--%>
                        <%--<div class="col-sm-2">--%>
                            <%--<input type="text" name="content" placeholder="内容" id="content" class="form-control" />--%>
                        <%--</div>--%>
                        <%--<label class="col-sm-1 control-label" style="padding-top: 7px">用户</label>--%>
                        <%--<div class="col-sm-2">--%>
                            <%--<select id="userId" name="userId"  class="form-control">--%>
                                <%--<option value="">全部</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body form-group" style="margin-bottom:0px;">--%>
                        <%--<label class="col-sm-1 control-label" style="padding-top: 7px">状态</label>--%>
                        <%--<div class="col-sm-2">--%>
                            <%--<select id="isActive" name="isActive"  class="form-control">--%>
                                <%--<option value="">全部</option>--%>
                                <%--<option value="0">启用</option>--%>
                                <%--<option value="1">禁用</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <%--<div class="col-sm-2">--%>
                            <%--<button class="btn btn-primary col-sm-12 " id="search_btn">查询</button>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <table id="mytab" name="mytab" class="table table-hover"></table>
                    <div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
                        <button id="btn_delete" onclick="deleteMany();" type="button" class="btn btn-default" style="display: block;">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true" ></span>批量修改状态
                        </button>
                        <button id="btn_add" type="button" class="btn btn-default" data-toggle="modal" data-target="#webAdd">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true" ></span>新增
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%--网站数据的新增--%>
<!-- 模态框（Modal） -->
<div class="modal fade" id="webAdd" tabindex="-1" role="dialog" aria-labelledby="webAddLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="webAddTitle">
                    新增员工
                </h4>
            </div>
            <form class="form-horizontal" method="post" id="formadd">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录账号：</label>
                        <div class="col-sm-8">
                            <input  name="phone" minlength="2" maxlength="20" type="number" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录密码：</label>
                        <div class="col-sm-8">
                            <input  name="password" minlength="2" maxlength="20" type="password" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">真实姓名：</label>
                        <div class="col-sm-8">
                            <input  name="realname" minlength="2" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="add" class="btn btn-primary">
                        确认新增
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<input type="hidden" value=""  id="deleteId"/>
<%--网站新增结束--%>
<div class="modal fade" id="updateStatus" tabindex="-1" role="dialog" aria-labelledby="webAddLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" >
                    批量修改状态
                </h4>
            </div>
            <form class="form-horizontal" method="post" id="update_status">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">员工状态：</label>
                        <div class="col-sm-8">
                            <select class="form-control"  id="status" required name="status">
                                <option value="0">启用</option>
                                <option value="1">停用</option>
                            </select>
                        </div>
                        <input id="statusId" type="hidden" name="manyId" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="updateSta" class="btn btn-primary"  data-dismiss="modal">
                        确认修改
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%--网站信息的修改--%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    员工的修改
                </h4>
            </div>
            <form class="form-horizontal" id="updateform" >
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">登录账号：</label>
                        <div class="col-sm-8">
                            <input  name="phone" minlength="2" id="phones" maxlength="20" type="number" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">真实姓名：</label>
                        <div class="col-sm-8">
                            <input  name="realname" minlength="2" id="real" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="update" class="btn btn-primary">
                        确认修改
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%--网站信息的修改--%>
<jsp:include page="../common/bootstraptablejs.jsp"></jsp:include>
<script src="<%=path%>/static/js/pageJs/user.js"></script>
</body>
<script>
    laydate.render({
        elem: '#test_2'//指定元素，
        ,type: 'date'
        ,range: true
    });
    $.post(
        "/appUser/getAppUserList",
        function(data){
            $("#userId").select2({
                data: data
            });
            $("#select2-userId-container").remove();
        },
        "json"

    );
</script>
</html>
