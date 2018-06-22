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
    <title>剧列表</title>
    <jsp:include page="../common/bootstraptablecss.jsp"></jsp:include>
    <link href="<%=path%>/static/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="<%=path%>/static/js/layui/css/layui.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <div class="ibox-title">
            <h5>剧列表</h5>
        </div>
        <div class="ibox-content">
            <div class="panel panel-default">
                <div class="panel-heading">
                    查询条件
                </div>
                <div class="panel-body form-group" style="margin-bottom:0px;">
                    <div class="panel-body form-group" style="margin-bottom:0px;">
                        <label class="col-sm-1 control-label" style="padding-top: 7px">所属模块</label>
                        <div class="col-sm-2">
                            <select id="vedioModuleId_" name="vedioModuleId"  class="form-control">
                                <option value="">全部</option>
                            </select>
                        </div>
                        <label class="col-sm-1 control-label" style="padding-top: 7px">所属类型</label>
                        <div class="col-sm-2">
                            <select id="vedioTypeId" name="vedioTypeId"  class="form-control">
                                <option value="">全部</option>
                            </select>
                        </div>
                        <label class="col-sm-1 control-label" style="padding-top: 7px">剧名称</label>
                        <div class="col-sm-2">
                            <input type="text" name="title" id="title" class="form-control" />
                        </div>
                        <label class="col-sm-1 control-label" style="padding-top: 7px">剧评分</label>
                        <div class="col-sm-2">
                            <input type="text" name="rate" id="rate" class="form-control" />
                        </div>
                    </div>
                    <div class="panel-body form-group" style="margin-bottom:0px;">
                        <label class="col-sm-1 control-label" style="padding-top: 7px">是否收费</label>
                        <div class="col-sm-2">
                            <select id="isVip" name="isVip"  class="form-control">
                                <option value="">全部</option>
                                <option value="0">免费</option>
                                <option value="1">VIP</option>
                            </select>
                        </div>
                        <label class="col-sm-1 control-label" style="padding-top: 7px">是否完结</label>
                        <div class="col-sm-2">
                            <select id="isLast" name="isLast"  class="form-control">
                                <option value="">全部</option>
                                <option value="0">已完结</option>
                                <option value="1">连载中</option>
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
                    新增剧
                </h4>
            </div>
            <form class="form-horizontal" method="post" id="formadd">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所属模块：</label>
                        <div class="col-sm-8">
                            <select name="vedioModuleId" id="vedioModuleId" class="form-control" >

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">所属类型：</label>
                        <div class="col-sm-8">
                            <input  name="vedioTypeId" minlength="2" placeholder="所属类型" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">T V 名称：</label>
                        <div class="col-sm-8">
                            <input  name="title" minlength="2" placeholder="剧名称" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">剧缩略图</label>
                        <div class="col-sm-8">
                            <div class="layui-upload-list">
                                <img class="layui-upload-img" style="width: 100px;height: 100px" src="<%=path%>/static/img/load.png" id="demo1">
                                <input type="hidden" value="" name="faceImg" id="faceImg" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">T V 评分：</label>
                        <div class="col-sm-8">
                            <input  name="rate" minlength="2" placeholder="剧评分" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">剧总集数：</label>
                        <div class="col-sm-8">
                            <input  name="jishu" minlength="2" placeholder="剧总集数" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否VIP：</label>
                        <div class="col-sm-8">
                            <select name="isVip" class="form-control" >
                                <option value="0">普通剧</option>
                                <option value="1">VIP</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否完结：</label>
                        <div class="col-sm-8">
                            <select name="isLast" class="form-control" >
                                <option value="0">已完结</option>
                                <option value="1">连载中</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">T V 说明：</label>
                        <div class="col-sm-8">
                            <textarea  name="description" class="form-control" required="" aria-required="true"></textarea>
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
                        <label class="col-sm-3 control-label">剧状态：</label>
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
                    剧的修改
                </h4>
            </div>
            <form class="form-horizontal" id="updateform" >
                <div class="modal-body">
                    <input type="hidden" name="id" id="id" value="">
                    <div class="modal-body">
                        <div class="form-group">
                            <label class="col-sm-3 control-label">所属模块：</label>
                            <div class="col-sm-8">
                                <select name="vedioModuleId" id="vedioModule_Id" class="form-control">
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">所属类型：</label>
                            <div class="col-sm-8">
                                <input  name="vedioTypeId" minlength="2" placeholder="所属类型" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">T V 名称：</label>
                            <div class="col-sm-8">
                                <input  name="title" minlength="2" placeholder="剧名称" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">剧缩略图</label>
                            <div class="col-sm-8">
                                <div class="layui-upload-list">
                                    <img class="layui-upload-img" style="width: 100px;height: 100px" src="<%=path%>/static/img/load.png" id="demo_1">
                                    <input type="hidden" value="" name="faceImg" id="face_Img" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">T V 评分：</label>
                            <div class="col-sm-8">
                                <input  name="rate" minlength="2" placeholder="剧评分" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">剧总集数：</label>
                            <div class="col-sm-8">
                                <input  name="jishu" minlength="2" placeholder="剧总集数" maxlength="20" type="text" class="form-control" required="" aria-required="true">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否VIP：</label>
                            <div class="col-sm-8">
                                <select name="isVip" class="form-control" >
                                    <option value="0">普通剧</option>
                                    <option value="1">VIP</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">是否完结：</label>
                            <div class="col-sm-8">
                                <select name="isLast" class="form-control" >
                                    <option value="0">已完结</option>
                                    <option value="1">连载中</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">T V 说明：</label>
                            <div class="col-sm-8">
                                <textarea  name="description" class="form-control" required="" aria-required="true"></textarea>
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
                    剧详情
                </h4>
            </div>
            <div class="modal-body" id="remarks">

            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
<%--网站信息的修改--%>
<jsp:include page="../common/bootstraptablejs.jsp"></jsp:include>
<script src="<%=path%>/static/js/plugins/laydate/laydate.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/layui/layui.js" charset="utf-8"></script>
<script src="<%=path%>/static/js/plugins/iCheck/icheck.min.js"></script>
<script src="<%=path%>/static/js/pageJs/vedio.js"></script>
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
            "/vedioModule/getModuleList",
            function (data) {
                $("#vedioModuleId").select2({
                    data: data
                });
                $("#vedioModule_Id").select2({
                    data: data
                });
                $("#vedioModuleId_").select2({
                    data: data
                });
                $("#select2-vedioModuleId_-container").remove();
                $("#select2-vedioModuleId-container").remove();
                $("#select2-vedioModule_Id-container").remove();
            },
            "json"
        );
    });
    layui.use('upload', function() {
        var $ = layui.jquery
            , upload = layui.upload;
        //普通图片上传
        var uploadInst = upload.render({
            elem: '#demo1'
            , url: '/file/up'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }else{
                    $("#faceImg").val(res.data.src);
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
            elem: '#demo_1'
            , url: '/file/up'
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
                    $("#face_Img").val(res.data.src);
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
</script>
</html>
