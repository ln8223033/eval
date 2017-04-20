<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>班级列表</title>
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
                url: '/class/classList',
                datatype: "json",
                styleUI: 'Bootstrap', //设置jqgrid的全局样式为bootstrap样式
                colModel: [
                    { label: 'id', name: 'id', width: 150, key: true },
                    { label: '班级名称', name: 'name', width: 180 },
                    { label: '年级', name: 'grade', width: 180 },
                    { label: '学校ID', name: 'schoolid', width: 180 },
                    { label: '所属学院', name: 'departmentid', width: 180 },
                    { label: '所属专业', name: 'professionid', width: 180 }
                ],
                viewrecords: true,
                height: 385,
                rowNum: 10,//每页显示记录数
                rowList : [10,30,50],//用于改变显示行数的下拉列表框的元素数组
                rownumbers: false,//添加左侧行号
                rownumWidth: 25,
                autowidth:true,
                multiselect: true,
                pager: "#jqGridPager",
                /*设置分页显示的导航条信息*/
                jsonReader : {
                    root: "page.list",
                    page: "page.currPage",
                    total: "page.totalPage",
                    records: "page.totalCount"
                },
                /*像后台请求的参数信息*/
                prmNames : {
                    page:"page",
                    rows:"limit",
                    order: "order"
                },
                gridComplete:function(){
                    //隐藏grid底部滚动条
                    $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
                }
            });
        });
        new Vue({
            el: '#app',
            data: {
                test:111,
            },
            methods: {
                test1:function(){
                },
                test2:function(){
                    alert("this is test2")
                    alert(this.test) //test3调用时弹出undefined
                },
                test3:function(){
                    //this.$options.methods.test2();
                    this.$options.methods.test2.bind(this)();
                }
            }
        })
    </script>


</head>
<body>

<table id="jqGrid"></table>
<div id="jqGridPager"></div>

</body>

</html>


