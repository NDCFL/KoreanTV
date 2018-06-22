//生成用户数据
$('#mytab').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/vedioSection/vedioSectionList",//要请求数据的文件路径
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
            title: '剧名称',
            field: 'vedioName',
            align: 'center',
            sortable: true
        },
        {
            title: '集名称',
            field: 'title',
            align: 'center',
            sortable: true
        },
        {
            title: '视频地址',
            field: 'url',
            align: 'center',
            sortable: true,
            formatter: function (value, row, index) {
                if(value==null || value==''){
                    return '<a title="无视频点击上传" alt="无视频点击上传"  style="color: red" onclick="return play_s(' + row.id+ ')">无视频点击上传</a>';
                }else{
                    return '<a title="播放" alt="播放" style="color: #0d9a2b" onclick="return plays(' + row.id+ ')">播放</a>';
                }

            }
        },
        {
            title: '观看次数',
            field: 'lookTimes',
            align: 'center',
            sortable: true,
            formatter: function (value, row, index) {
                if(parseInt(value)>10000){
                    return '<span>'+parseFloat(value/10000)+'(万次)</span>';
                }else{
                    return '<span>'+value+'(次)</span>';
                }

            }
        },
        {
            title: '是否免费',
            field: 'isVip',
            align: 'center',
            sortable: true,
            formatter: function (value, row, index) {
                if(value==0){
                    return '<span style="color: green">免费</span>';
                }else{
                    return '<span style="color:red">会员</span>';
                }
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
                var e = '<a title="编辑" href="javascript:void(0);" id="vedioSection"  data-toggle="modal" data-id="\'' + row.id + '\'" data-target="#myModal" onclick="return edit(\'' + row.id + '\')"><i class="glyphicon glyphicon-pencil" alt="修改" style="color:green">修改</i></a> ';
                var d = '<a title="删除" href="javascript:void(0);" onclick="del(' + row.id + ',' + row.isActive + ')"><i class="glyphicon glyphicon-trash" alt="删除" style="color:red">删除</i></a> ';
                var f = '';
                if (row.isActive == 1) {
                    f = '<a title="启用" href="javascript:void(0);" onclick="updatestatus(' + row.id + ',' + 0 + ')"><i class="glyphicon glyphicon-ok-sign" style="color:green">启用</i></a> ';
                } else if (row.isActive == 0) {
                    f = '<a title="停用" href="javascript:void(0);" onclick="updatestatus(' + row.id + ',' + 1 + ')"><i class="glyphicon glyphicon-remove-sign"  style="color:red">停用</i></a> ';
                }

                return e+d + f;
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
function  plays(val) {
    $.post("/vedioSection/findVedioSection/" + val,
        function (data) {
            var $url = data.url;
             if($url.indexOf("http")>-1){
                 $("#play").html('<video width="320" height="240" controls  ><source src="'+$url+'" type="video/'+$url.toString().substring($url.indexOf(".")+1,$url.length)+'"></video><input type="hidden" value="'+val+'" id="ids" />');
             }else{
                 if($url.indexOf("mp4")>-1 || $url.indexOf("ogg")>-1 || $url.indexOf("webm")>-1){
                     $("#play").html('<video width="320" height="240" controls  ><source src="'+$url+'" type="video/'+$url.toString().substring($url.indexOf(".")+1,$url.length)+'"></video><input type="hidden" value="'+val+'" id="ids" />');
                 }else{
                     $("#play").html('<p style="text-align: center">改视频格式不支持播放</p><input type="hidden" value="'+val+'" id="ids" />');
                 }

             }
             $("#play_modal").modal('show');
        },
        "json"
    );

}
function play_s(val) {
    if(val==-1){
        play_s($("#ids").val());
    }else{
        $("#play").html('<button type="button" class="layui-btn" id="test5"><i class="layui-icon"></i>上传视频</button>');
        var load = "";
        layui.use('upload', function(){
            var $ = layui.jquery
                ,upload = layui.upload;
            upload.render({
                elem: '#test5'
                ,url: '/file/upVedio'
                ,accept: 'video' //视频
                ,exts: 'mp4|ogg|webm' //只允许这三种格式
                ,size: 488281 //限制文件大小，单位 KB
                ,choose:function(){//选择文件回调函数

                }
                ,before:function(){//准备上传回调函数
                    load=layer.load(0, {shade: false});
                }
                ,done: function(res,index,upload){
                    layer.close(load);
                    $.post("/vedioSection/updateVedio",
                        {
                            id:val,
                            url:  res.data.src
                        },
                        function (data) {
                            plays(val);
                            refush();
                        },
                        "json"
                    );
                }
            });
        });
    }
    $("#play_modal").modal('show');
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
function del(vedioSectionid, status) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/vedioSection/deleteVedioSection/' + vedioSectionid,
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
    $.post("/vedioSection/findVedioSection/" + name,
        function (data) {
            $("#updateform").autofill(data);
        },
        "json"
    );
}
function updatestatus(id, status) {
    $.post("/vedioSection/updateStatus/" + id + "/" + status,
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
    var minVal = $("#min").val();
    var maxVal = $("#max").val();
    if(minVal!=null || minVal!='' || maxVal!=null || maxVal!=''){
        if(minVal.indexOf(".")>-1 || maxVal.indexOf(".")>-1){
            layer.alert("粉丝数量只能输入整数", {icon: 2});
            return;
        }
        if(parseInt(maxVal)<parseInt(minVal)){
            layer.alert("粉丝数量最大值不能小于最小值", {icon: 2});
            return;
        }
    }
    $('#mytab').bootstrapTable('refresh', {
        url: '/vedioSection/findVedioSectionLists',
        query:{
            createTime:start,
            endTime:end,
            vedioId:$("#vedioId").val(),
            title:$("#title").val(),
            isVip:$("#is_vip").val(),
            isActive:$("#is_last").val(),
            min:$("#min").val(),
            max:$("#max").val()
        }
    });
})
function refush() {
    $('#mytab').bootstrapTable('refresh', {url: '/vedioSection/vedioSectionList'});
}
$("#update").click(function () {
    $.post(
        "/vedioSection/vedioSectionUpdateSave",
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
    $.post(
        "/vedioSection/vedioSectionAddSave",
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
            "/vedioSection/deleteManyVedioSection",
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
            "/vedioSection/deleteManyVedioSection",
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
