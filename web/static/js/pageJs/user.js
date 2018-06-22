//生成用户数据
$('#mytab').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/user/userList",//要请求数据的文件路径
    toolbar: '#toolbar',//指定工具栏
    striped: true, //是否显示行间隔色
    dataField: "res",
    sortable: true, //是否启用排序 sortOrder: "ID asc",
    sortOrder: "ID asc",
    pagination: true,//是否分页
    queryParamsType: 'limit',//查询参数组织方式
    queryParams: queryParams,//请求服务器时所传的参数
    sidePagination: 'server',//指定服务器端分页
    pageNumber: 1, //初始化加载第一页，默认第一页
    pageSize: 10,//单页记录数
    pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],//分页步进值
    showRefresh: true,//刷新按钮
    showColumns: true,
    clickToSelect: true,//是否启用点击选中行
    toolbarAlign: 'right',//工具栏对齐方式
    buttonsAlign: 'right',//按钮对齐方式
    toolbar: '#toolbar',
    uniqueId: "id",                     //每一行的唯一标识，一般为主键列
    showExport: true,
    exportDataType: 'all',
    columns: [
        {
            title: '全选',
            field: 'select',
            //复选框
            checkbox: true,
            width: 25,
            align: 'center',
            valign: 'middle'
        },
        {
            title: '员工头像',
            field: 'faceImg',
            align: 'center',
            sortable: true,
            formatter: function (value, row, index) {
                return '<img src="'+value+'" class="viewer-item" style="width: 80px;height: 80px;border-radius: 100%"/>';

            }
        },
        {
            title: '联系方式',
            field: 'phone',
            align: 'center',
            sortable: true
        },
        {
            title: '员工昵称',
            field: 'name',
            align: 'center',
            sortable: true
        },
        {
            title: '真实姓名',
            field: 'realname',
            align: 'center',
            sortable: true
        },
        {
            title: '员工性别',
            field: 'sex',
            align: 'center',
            sortable: true,
            formatter: function (value, row, index) {
                if(value==0){
                    return '<span style="color: green">男</span>';
                }else{
                    return '<span style="color: red">女</span>';
                }
            }
        },
        {
            title: '员工年龄',
            field: 'age',
            align: 'center',
            sortable: true
        },
        {
            title: '创建时间',
            field: 'createTime',
            align: 'center',
            sortable: true,
            formatter: function (value) {
                var date = new Date(value);
                var y = date.getFullYear();
                var m = date.getMonth() + 1;
                var d = date.getDate();
                var h = date.getHours();
                var mi = date.getMinutes();
                var ss = date.getSeconds();
                return y + '-' + (parseInt(m)<10?"0"+m:m) + '-' + (parseInt(d)<10?"0"+d:d) + ' ' + (parseInt(h)<10?"0"+h:h) + ':' + (parseInt(mi)<10?"0"+mi:mi) + ':' + (parseInt(ss)<10?"0"+ss:ss);
            }
        }
        ,
        {
            title: '当前状态',
            field: 'status',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 0) {
                    //表示启用状态
                    return '<span style="color: green">启用</span>';
                } else {
                    //表示启用状态
                    return '<span style="color: red">停用</span>';
                }
            }
        }
        ,
        {
            title: '操作',
            align: 'center',
            field: '',
            formatter: function (value, row, index) {
                var e = '<a title="编辑" href="javascript:void(0);" id="user"  data-toggle="modal" data-id="\'' + row.id + '\'" data-target="#myModal" onclick="return edit(\'' + row.id + '\')"><i class="glyphicon glyphicon-pencil" alt="修改" style="color:green">修改</i></a> ';
                var d = '<a title="删除" href="javascript:void(0);" onclick="del(' + row.id + ',' + row.isActive + ')"><i class="glyphicon glyphicon-trash" alt="删除" style="color:red">删除</i></a> ';
                var f = '';
                if (row.status == 1) {
                    f = '<a title="启用" href="javascript:void(0);" onclick="updatestatus(' + row.id + ',' + 0 + ')"><i class="glyphicon glyphicon-ok-sign" style="color:green">启用</i></a> ';
                } else if (row.status == 0) {
                    f = '<a title="停用" href="javascript:void(0);" onclick="updatestatus(' + row.id + ',' + 1 + ')"><i class="glyphicon glyphicon-remove-sign"  style="color:red">停用</i></a> ';
                }
                var p = '<a title="编辑" href="javascript:void(0);" id="user"  data-toggle="modal" data-id="' + row.id + '"  onclick="return reset(' + row.id + ')"><i class="glyphicon glyphicon-cog" alt="重置密码" style="color:green">重置密码</i></a> '
                var g = '<a title="消息推送" href="javascript:void(0);" id="user"    onclick="return push(' + row.id + ')"><i class="glyphicon glyphicon-cog" alt="消息推送" style="color:green">消息推送</i></a> '
                return d + f+e +p+g;
            }
        }
    ],
    locale: 'zh-CN',//中文支持,
    responseHandler: function (res) {
        if (res) {
            return {
                "res": res.rows,
                "total": res.total
            };
        } else {
            return {
                "rows": [],
                "total": 0
            };
        }
    }
})
function  remarks(val) {
    $("#remarks").html(val);
}
function push(val) {
    $.post("/user/sendMsg",
        {
            id:val
        },
        function (data) {
            alert(data.message);
        },
        "json"
    );
}
function  reset(val) {
    layer.confirm('确认要重置密码吗？', function (index) {
        $.post("/user/resetPassword",
            {
              id:val,
              password:"dt888888"
            },
            function (data) {
                if(data.result=="success"){
                    layer.alert(data.message+"新密码为:dt888888", {icon:1});
                }else{
                    layer.alert(data.message, {icon:2});
                }
                refush();
            },
            "json"
        );
    });
}
//请求服务数据时所传参数
function queryParams(params) {
    var title = "";
    $(".search input").each(function () {
        title = $(this).val();
    });
    return {
        //每页多少条数据
        pageSize: this.pageSize,
        //请求第几页
        pageIndex: this.pageNumber,
        id: $("#ids").val()
    }
}
function del(userid, status) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/user/deleteUser/' + userid,
            dataType: 'json',
            success: function (data) {
                if (data.message == '删除成功!') {
                    layer.alert(data.message, {icon: 6});
                } else {
                    layer.alert(data.message, {icon: 6});
                }
                refush();
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}
function edit(name) {
    $.post("/user/findUser/" + name,
        function (data) {
            $("#updateform").autofill(data);
        },
        "json"
    );
}
function updatestatus(id, status) {
    $.post("/user/updateStatus/" + id + "/" + status,
        function (data) {
            if(status==0){
                if(data.message=="ok"){
                    layer.alert("已启用", {icon:6});
                }else{
                    layer.alert("操作失败", {icon:6});
                }
            }else{
                if(data.message=="ok"){
                    layer.alert("已停用", {icon:5});
                }else{
                    layer.alert("操作失败", {icon:5});
                }
            }
            refush();
        },
        "json"
    );
}
//查询按钮事件
$('#search_btn').click(function () {
    var times = $("#test_2").val();
    var start,end;
    if(!times){
        start = null;
        end = null;
    }else {
        start = times.substring(0,11)+"00:00:00";
        end = times.substring(13,times.length)+" 23:59:59";
    }
    $('#mytab').bootstrapTable('refresh', {
        url: '/user/findUserList',
        query:{
            createTime:start,
            endTime:end,
            userName:$("#userName").val(),
            content:$("#content").val(),
            userId:$("#userId").val(),
            isActive:$("#isActive").val()
        }
    });
})
function refush() {
    $('#mytab').bootstrapTable('refresh', {url: '/user/userList'});
}
$("#update").click(function () {
    var phone = $("#phones").val();
    var realname = $("#real").val();
    if(!phone){
        layer.alert("登录账号不能为空", {icon: 2});
        return;
    }
    if(phone.length!=11 || phone.indexOf(".")>-1){
        layer.alert("登录账号格式错误", {icon: 2});
        return;
    }
    if(!realname){
        layer.alert("真实姓名不能为空", {icon: 2});
        return;
    }
    $.post(
        "/user/userUpdateSave",
        $("#updateform").serialize(),
        function (data) {
            if (data.result == "success") {
                layer.alert(data.message, {icon: 1});
            } else {
                layer.alert(data.message, {icon: 2});
            }
            refush();
            $("#myModal").modal("hide");
        }, "json"
    );
});
$("#add").click(function () {
    var phone = $("#phone").val();
    var password = $("#password").val();
    var realname = $("#realname").val();
    if(!phone){
        layer.alert("登录账号不能为空", {icon: 6});
        return;
    }
    if(phone.length!=11 || phone.indexOf(".")>-1){
        layer.alert("登录账号格式错误", {icon: 2});
        return;
    }
    if(!password){
        layer.alert("登录密码不能为空", {icon: 2});
        return;
    }
    if(!realname){
        layer.alert("真实姓名不能为空", {icon: 2});
        return;
    }
    $.post(
        "/user/userAddSave",
        $("#formadd").serialize(),
        function (data) {
            if (data.message == "新增成功!") {
                layer.alert(data.message, {icon: 1});
                refush();
            } else {
                layer.alert(data.message, {icon: 2});
                refush();
            }
            $("#webAdd").modal("hide");
        }, "json"
    );
});
function deleteMany11() {
    var isactivity = "";
    var row = $.map($("#mytab").bootstrapTable('getSelections'), function (row) {
        if (row.isActive == 0) {
            isactivity += row.isActive;
        }
        return row.id;
    });
    if (row == "") {
        layer.msg('删除失败，请勾选数据!', {
            icon: 2,
            time: 2000
        });
        return;
    }
    if (isactivity != "") {
        layer.msg('删除失败，已经启用的不允许删除!', {
            icon: 2,
            time: 2000
        });
        return;

    }
    $("#deleteId").val(row);
    layer.confirm('确认要执行批量删除网站信息数据吗？', function (index) {
        $.post(
            "/user/deleteManyUser",
            {
                "manyId": $("#deleteId").val()
            },
            function (data) {
                if (data.message == "删除成功!") {
                    layer.alert(data.message, {icon: 6});
                    refush();
                } else {
                    layer.alert(data.message, {icon: 6});
                    refush();
                }
            }, "json"
        );
    });
}
function deleteMany(){
    var isactivity="";
    var row=$.map($("#mytab").bootstrapTable('getSelections'),function(row){
        if(row.isActive==0){
            isactivity+=row.isActive;
        }
        return row.id ;
    });
    if(row==""){
        layer.msg('修改失败，请勾选数据!', {
            icon : 2,
            time : 3000
        });
        return ;
    }
    $("#statusId").val(row);
    $("#updateStatus").modal('show');

}
$("#updateSta").click(function () {
    layer.confirm('确认要执行批量修改用户朋友圈评论状态吗？',function(index){
        $.post(
            "/user/deleteManyUser",
            {
                "manyId":$("#statusId").val(),
                "status":$("#status").val()
            },
            function(data){
                if(data.message=="修改成功!"){
                    layer.alert(data.message, {icon:6});
                    refush();
                }else{
                    layer.alert(data.message, {icon:6});
                    refush();
                }
            },"json"
        );
    });
});