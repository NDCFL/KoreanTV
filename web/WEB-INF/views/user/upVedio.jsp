<%--
  Created by IntelliJ IDEA.
  User: jb9
  Date: 2018/3/28
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="<%=path%>/static/js/layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<button type="button" class="layui-btn" id="test5"><i class="layui-icon"></i>上传视频</button>
<script src="<%=path%>/static/js/layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    var load = "";
    layui.use('upload', function(){
        var $ = layui.jquery
            ,upload = layui.upload;
        upload.render({
            elem: '#test5'
            ,url: '/file/upVedio'
            ,accept: 'video' //视频
            ,choose:function(){//选择文件回调函数

            }
            ,before:function(){//准备上传回调函数
                load=layer.load(0, {shade: false});
            }
            ,done: function(res,index,upload){
                layer.close(load);
                console.log(res)
            }
        });
    });
</script>

</body>
</html>
