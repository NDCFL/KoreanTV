<%--
  Created by IntelliJ IDEA.
  User: jb9
  Date: 2018/2/28
  Time: 11:38
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>数据统计</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="<%=path%>/static/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="<%=path%>/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path%>/static/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
</head>
<body class="gray-bg">
<div class="row  border-bottom white-bg dashboard-header">
    <div class="col-sm-12">
        <h3 align="center">点图韩剧统计信息 </h3>
    </div>
</div>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>折线图</h5>
                </div>
                <div class="ibox-content">
                    <div class="echarts" id="echarts-line-chart"></div>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>柱状图</h5>
                </div>
                <div class="ibox-content">
                    <div class="echarts" id="echarts-bar-chart"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=path%>/static/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/static/js/bootstrap.min.js?v=3.3.5"></script>
<script src="<%=path%>/static/js/plugins/echarts/echarts-all.js"></script>
<script src="<%=path%>/static/js/content.min.js?v=1.0.0"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echarts-line-chart'));
    var option = ""
    // 指定图表的配置项和数据
    $.post(
        "/appUser/getCount",
        function (data) {
            option = {
                title : {
                    text: '点图韩剧',
                    subtext: '每月用户注册量'
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['人数']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value} 人'
                        }
                    }
                ],
                series : [
                    {
                        name:'注册人数',
                        type:'line',
                        data:[0,0,3,0,0,0,0,0,0,0,0,0],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };
            myChart.setOption(option);
        },
        "text"
    );
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    // 基于准备好的dom，初始化echarts实例
    var myChart1 = echarts.init(document.getElementById('echarts-bar-chart'));
    // 指定图表的配置项和数据
    var option1 = {
        title : {
            text: '韩剧本月日访问量',
            subtext: '韩剧本月日访问量'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['人数']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ['1','2','3','4','5','6','7','8','9','10','11','12','1','2','3','4','5','6','7','8','9','10','11','12','1','2','3','4','5','6','7','8','9','10','11','12']
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value} 人'
                }
            }
        ],
        series : [
            {
                name:'韩剧本月日访问量',
                type:'line',
                data:[11, 11, 15, 13, 12, 13, 10,10,10,10,10,10,11, 11, 15, 13, 12, 13, 10,10,10,10,10,10,11, 11, 15, 13, 12, 13, 10,10,10,10,10,10],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option1);
</script>
</body>
</html>