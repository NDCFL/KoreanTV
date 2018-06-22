//生成用户数据
$('#mytab').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/downHistory/downHistoryList",//要请求数据的文件路径
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
    search:true,
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
            title: '用户头像',
            field: 'userVo.faceImg',
            align: 'center',
            sortable: true,
            formatter: function (value, row, index) {
                return '<img src="'+value+'" class="viewer-item" style="width: 80px;height: 80px;border-radius: 100%"/>';
            }
        },
        {
            title: '用户名称',
            field: 'userVo.name',
            align: 'center',
            sortable: true,
            formatter: function (value, row, index) {
                return '<span style="color: red">'+value+'</span>';
            }
        },
        {
            title: '剧名称',
            field: 'vedioVo.title',
            align: 'center',
            sortable: true
        },
        {
            title: '下载集数',
            field: 'vedioVo.current',
            align: 'center',
            sortable: true,
            formatter: function (value, row, index) {
                return '<a   data-toggle="modal" title="点击查看详情" alt="点击查看详情" data-id="' + row.id + '" data-target="#remark_modal" onclick="return remarks(' + row.vedioId + ','+row.userId+')">'+value+'集</a>';
            }
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
            field: 'isActive',
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
                var e = '<a title="编辑" href="javascript:void(0);" id="downHistory"  data-toggle="modal" data-id="\'' + row.id + '\'" data-target="#myModal" onclick="return edit(\'' + row.id + '\')"><i class="glyphicon glyphicon-pencil" alt="修改" style="color:green">修改</i></a> ';
                var d = '<a title="删除" href="javascript:void(0);" onclick="del(' + row.id + ',' + row.isActive + ')"><i class="glyphicon glyphicon-trash" alt="删除" style="color:red">删除</i></a> ';
                var f = '';
                if (row.isActive == 1) {
                    f = '<a title="启用" href="javascript:void(0);" onclick="updatestatus(' + row.id + ',' + 0 + ')"><i class="glyphicon glyphicon-ok-sign" style="color:green">启用</i></a> ';
                } else if (row.isActive == 0) {
                    f = '<a title="停用" href="javascript:void(0);" onclick="updatestatus(' + row.id + ',' + 1 + ')"><i class="glyphicon glyphicon-remove-sign"  style="color:red">停用</i></a> ';
                }

                return d + f;
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
function  remarks(vedioId,userId) {
    $.post("/downHistory/getSectionList",
        {
          vedioId:vedioId,
          userId:userId
        },
        function (data) {
            var allHtmls = "";
            var html = '<div class="list-group">\n' +
                '                    <a class="list-group-item">\n' +
                '                        <h4 class="list-group-item-heading">\n' +
                '                            ~\n' +
                '                        </h4>\n' +
                '                    </a>\n' +
                '                </div>';
            for(var i=0;i<data.length;i++){
                allHtmls = allHtmls + html.replace("~",data[i].title)
            }
            $("#remarks").html(allHtmls);
        },
        "json"
    );
    //$("#remarks").html(val);
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
        searchVal: title
    }
}
function del(downHistoryid, status) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/downHistory/deleteDownHistory/' + downHistoryid,
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
    $.post("/downHistory/findDownHistory/" + name,
        function (data) {
            $("#updateform").autofill(data);
        },
        "json"
    );
}
function updatestatus(id, status) {
    $.post("/downHistory/updateStatus/" + id + "/" + status,
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
    $('#mytab').bootstrapTable('refresh', {
        url: '/downHistory/findDownHistoryList'
        ,query:{
            vedioId:$("#vedioId").val(),
            userName:$("#userName").val()
        }
    });
})
function refush() {
    $('#mytab').bootstrapTable('refresh', {url: '/downHistory/downHistoryList'});
}
$("#update").click(function () {
    var title = $("#title_").val();
    var level = $("#level_").val();
    if(!title){
        layer.alert("剧模块名称不能为空!", {icon: 6});
        return false;
    }
    if(!level){
        layer.alert("剧模块级别不能为空!", {icon: 6});
        return false;
    }
    if(parseFloat(level)<0 || parseFloat(level)>10){
        layer.alert("剧模块级别范围位0-10!", {icon: 6});
        return false;
    }
    $.post(
        "/downHistory/downHistoryUpdateSave",
        $("#updateform").serialize(),
        function (data) {
            if (data.message == "修改成功!") {
                layer.alert(data.message, {icon: 6});
                refush();
            } else {
                layer.alert(data.message, {icon: 6});
                refush();
            }
        }, "json"
    );
});
$("#add").click(function () {
    var title = $("#title").val();
    var level = $("#level").val();
    if(!title){
        layer.alert("剧模块名称不能为空!", {icon: 6});
        return false;
    }
    if(!level){
        layer.alert("剧模块级别不能为空!", {icon: 6});
        return false;
    }
    if(parseFloat(level)<0 || parseFloat(level)>10){
        layer.alert("剧模块级别范围位0-10!", {icon: 6});
        return false;
    }
    $.post(
        "/downHistory/downHistoryAddSave",
        $("#formadd").serialize(),
        function (data) {
            if (data.message == "新增成功!") {
                layer.alert(data.message, {icon: 6});
                refush();
            } else {
                layer.alert(data.message, {icon: 6});
                refush();
            }
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
            "/downHistory/deleteManyDownHistory",
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
    layer.confirm('确认要执行批量修改收支科目状态吗？',function(index){
        $.post(
            "/downHistory/deleteManyDownHistory",
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