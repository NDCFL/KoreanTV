<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/23
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
<script src="<%=path%>/static/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/static/js/bootstrap.min.js?v=3.3.5"></script>
<script src="<%=path%>/static/js/content.min.js?v=1.0.0"></script>
<script src="<%=path%>/static/js/plugins/validate/jquery.validate.min.js"></script>
<script src="<%=path%>/static/js/plugins/validate/messages_zh.min.js"></script>
<script src="<%=path%>/static/js/demo/form-validate-demo.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script src="<%=path%>/static/js/jquery.formautoFill.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/plugins/datapicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/plugins/datapicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/select2.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/plugins/laydate/laydate.js"></script>
<script src="<%=path%>/static/js/imgViewer.js"></script>
<script src="<%=path%>/static/js/layui/layui.js"></script>
<script>
    $.bindViewer(".viewer-item");  //支持绑定到动态生成的元素
</script>
</html>
