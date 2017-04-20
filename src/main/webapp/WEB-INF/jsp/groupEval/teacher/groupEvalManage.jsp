<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>群体评测管理</title>
    <link rel="stylesheet" href="/css/statics/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/statics/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/statics/plugins/jqgrid/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="/css/statics/plugins/ztree/css/metroStyle/metroStyle.css">
    <link rel="stylesheet" href="/css/statics/css/main.css">
    <script src="/js/statics/libs/jquery.min.js"></script>
    <script src="/js/statics/plugins/layer/layer.js"></script>
    <script src="/js/statics/libs/bootstrap.min.js"></script>
    <script src="/js/statics/libs/vue.min.js"></script>
    <script src="/js/statics/plugins/jqgrid/grid.locale-cn.js"></script>
    <script src="/js/statics/plugins/jqgrid/jquery.jqGrid.min.js"></script>
    <script src="/js/statics/plugins/ztree/jquery.ztree.all.min.js"></script>
    <script src="/js/common.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#jqGrid").jqGrid({
                url: '../eval/list',
                datatype: "json",
                colModel: [
                    {label: 'id', name: 'id', width: 50, key: true},
                    {label: '评测题目', name: 'evalTitle', width: 80},
                    {label: '发布状态', name: 'evalTitleDTO', width: 80},
                    {label: '作品结束上传时间', name: 'upload Time', width: 80},
                    {label: '评测结束时间', name: 'endTime', width: 80},
                    {label: '每组人数', name: 'groupSize', width: 80 },
                    {label: '开始时间', name: 'startTime', width: 80},
                    {label: '老师', name: 'ownerId', width: 80},
                    {label: '使用班级', name: 'teachingClassId', width: 80},
                    {label: '分组结束时间', name: 'groupTime', width: 80},
                    {label: '是否已分组', name: 'assignState', width: 80},
                    {label: '状态', name: 'status', width: 80}
                ],

                viewrecords: true,
                height: 385,
                rowNum: 10,//每页显示记录数
                rowList: [10, 30, 50],//用于改变显示行数的下拉列表框的元素数组
                rownumbers: false,//添加左侧行号
                sortname: "createTime" , //默认排序的列名
                sortorder: "desc" , //默认排序的顺序
                scroll: true , //鼠标滚动翻页
                rownumWidth: 25,
                autowidth: true,
                multiselect: true,
                pager: "#jqGridPager",
                    /*设置分页显示的导航条信息*/
                jsonReader: {
                root: "page.list",
                    page: "page.pageNum",
                    total: "page.pages",
                    records: "page.total"
            },
            /*像后台请求的参数信息*/
            prmNames: {
                page: "page",
                    rows: "pageSize",
                    order: "order"
            },
                gridComplete: function () {
                    //隐藏grid底部滚动条
                    $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
                }
            });
        });

    </script>
</head>
<body>
<td class="main" valign="top">
    <div class="main-box">
        <div style="background-color:ghostwhite; height: 40px;">
            <center><h4 class="fixie"><b>群体式评审题目列表</b></h4></center>
            <hr style="height:5px;border:none;border-top:2px solid #555555;">
        </div>

<!--搜索栏-->
<div style="margin:auto;margin-top:20px;">
    <div class="input-group" style="width:400px;margin:auto;">
        <input type="text" name="key" id="key" class="form-control"
               placeholder="请输入评测关键字"
               style="border-color:#4bd083">
        <span class="input-group-btn">
                    <button class="btn btnSelect" onclick="DoSearch()"><span class="glyphicon glyphicon-search"></span></button>
                </span>
    </div>
</div>

<!--按钮组-->
<div style="margin:auto;margin-top:20px;margin-left:35px;">
    <a class="btn btn-sm" href="/eval/create">添加新评测</a>
    <a class="btn btn-sm" href="#" id="update" style="display: inline-block;">编辑</a>
    <a class="btn btn-sm" href="#" id="Delete" style="display: inline-block;">删除</a>
    <a class="btn btn-sm" href="#" id="btnDele" style="display: inline-block;">分组</a>
    <a class="btn btn-sm" href="#" id="btnDel" style="display: inline-block;">评分任务</a>
    <a class="btn btn-sm" href="#" id="btnDeleteTestPr" style="display: inline-block;">批量导出学生作业</a>
    <a class="btn btn-sm" href="#" id="btnDeleteTe" style="display: inline-block;">导出所有成绩</a>
</div>
    </div>
</td>
<table id="jqGrid"></table>
<div id="jqGridPager"></div>

</body>
</html>
