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
    <title>剧集列表</title>
    <jsp:include page="../common/bootstraptablecss.jsp"></jsp:include>
    <link rel="stylesheet" href="<%=path%>/static/js/layui/css/layui.css"  media="all">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>剧集列表</h5>
        </div>
        <div class="ibox-content">
            <div class="panel panel-default">
                <div class="panel-heading">
                    查询条件
                </div>
                <div class="panel-body form-group" style="margin-bottom:0px;">
                    <label class="col-sm-1 control-label" style=" margin-top:5px">创建时间</label>
                    <div class="col-sm-2" >
                        <input type="text" class="form-control" placeholder="注册时间"  name="createTime" id="test_2"/>
                    </div>
                    <label class="col-sm-1 control-label" style="padding-top: 7px">所属剧</label>
                    <div class="col-sm-2">
                        <select id="vedioId" name="vedioId" class="form-control">
                            <option value="">全部</option>
                        </select>
                    </div>
                    <label class="col-sm-1 control-label" style="padding-top: 7px">集名称</label>
                    <div class="col-sm-2">
                        <input type="text" name="title" id="title" class="form-control" />
                    </div>
                    <label class="col-sm-1 control-label" style="padding-top: 7px">观看次数</label>
                    <div class="col-sm-2">
                        <input type="text" name="min" id="min" placeholder="最小值" style="width: 40%" class=" form-control col-sm-5"/>
                        <label class="col-sm-2 control-label" style="padding-top: 7px">到</label>
                        <input type="text" name="max" id="max" placeholder="最大值" style="width: 43%" class=" form-control col-sm-5"/>
                    </div>
                </div>
                <div class="panel-body form-group" style="margin-bottom:0px;">
                    <label class="col-sm-1 control-label" style="padding-top: 7px">是否免费</label>
                    <div class="col-sm-2">
                        <select id="is_vip" name="isVip" class="form-control">
                            <option value="">全部</option>
                            <option value="0">免费</option>
                            <option value="1">会员</option>
                        </select>
                    </div>
                    <label class="col-sm-1 control-label" style="padding-top: 7px">状态</label>
                    <div class="col-sm-2">
                        <select id="is_last" name="isLast" class="form-control">
                            <option value="">全部</option>
                            <option value="0">启用</option>
                            <option value="1">禁用</option>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary col-sm-12 " id="search_btn">查询</button>
                    </div>
                </div>
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
                    新增剧集
                </h4>
            </div>
            <form class="form-horizontal" method="post" id="formadd">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所属剧：</label>
                        <div class="col-sm-8">
                            <select name="vedioId" id="vedioSectionId" class="form-control" >

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">剧集名称：</label>
                        <div class="col-sm-8">
                            <input  name="title" minlength="2" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">观看次数：</label>
                        <div class="col-sm-8">
                            <input  name="lookTimes" minlength="2" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否VIP：</label>
                        <div class="col-sm-8">
                            <select name="isVip" class="form-control" >
                                <option value="0">免费</option>
                                <option value="1">VIP</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="add" class="btn btn-primary" data-dismiss="modal">
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
                        <label class="col-sm-3 control-label">剧集状态：</label>
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
                    <button type="button" id="updateSta" class="btn btn-primary" data-dismiss="modal">
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
                    剧集的修改
                </h4>
            </div>
            <form class="form-horizontal" id="updateform" >
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">所属剧：</label>
                            <div class="col-sm-8">
                                <select name="vedioId" id="vedioSection_Id" class="form-control" >

                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">剧集名称：</label>
                            <div class="col-sm-8">
                                <input  name="title" minlength="2" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">观看次数：</label>
                            <div class="col-sm-8">
                                <input  name="lookTimes" minlength="2" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否VIP：</label>
                            <div class="col-sm-8">
                                <select name="isVip" class="form-control" >
                                    <option value="0">免费</option>
                                    <option value="1">VIP</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="update" class="btn btn-primary" data-dismiss="modal">
                        确认修改
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="remark_modal" tabindex="-1" role="dialog" aria-labelledby="remark_modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    剧集详情
                </h4>
            </div>
            <div class="modal-body" id="remarks">

            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</div>
<div class="modal fade" id="play_modal" tabindex="-1" role="dialog" aria-labelledby="remark_modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h3 class="modal-title">
                    视频播放<span style="color: #0d9a2b;font-size: 12px">（仅支持播放 [ogg、mp4 或 webm] 格式）</span>
                </h3>
            </div>
            <div class="modal-body" id="play">

            </div><!-- /.modal-content -->
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" onclick="play_s(-1)" class="btn btn-primary" >
                    修改视频
                </button>
            </div>
        </div><!-- /.modal -->
    </div>
</div>
<%--网站信息的修改--%>
<jsp:include page="../common/bootstraptablejs.jsp"></jsp:include>
<script src="<%=path%>/static/js/layui/layui.js" charset="utf-8"></script>
<script src="<%=path%>/static/js/pageJs/vedioSection.js"></script>
<script src="<%=path%>/static/js/plugins/laydate/laydate.js"></script>
</body>
<%--<script>--%>
    <%--$(function () {--%>
        <%--alert("asdflsfa");--%>
        <%--layer.msg('已发布', {icon:1,time:1000});--%>
        <%--layer.msg('已发布', {icon:2,time:1000});--%>
        <%--layer.msg('已发布', {icon:3,time:1000});--%>
        <%--layer.msg('已发布', {icon:4,time:1000});--%>
        <%--layer.msg('已发布', {icon:5,time:1000});--%>
        <%--layer.msg('已发布', {icon:6,time:1000});--%>
        <%--layer.msg('已发布', {icon:7,time:1000});--%>
        <%----%>
    <%--});--%>

<%--</script>--%>
<script>
    $(function () {
        $.post(
            "/vedio/getVedioList",
            function (data) {
                $("#vedioSectionId").select2({
                    data: data
                });
                $("#vedioSection_Id").select2({
                    data: data
                });
                $("#vedioId").select2({
                    data: data
                });
                $("#select2-vedioId-container").remove();
                $("#select2-vedioSectionId-container").remove();
                $("#select2-vedioSection_Id-container").remove();
            },
            "json"
        );

    });
    laydate.render({
        elem: '#test_2'//指定元素，
        ,type: 'date'
        ,range: true
    });
</script>
</html>
