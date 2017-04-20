<%--suppress JSAnnotator --%>
<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/21 0021
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>群体评测题目</title>
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
                url: '../question/list',
                datatype: "json",
                colModel: [
                    {label: '题目', name: 'title',sortable : false, width: 80},
                    {label: '题目的详细内容', name: 'titleDetail',sortable : false, width: 80},
                    {label: '出题人', name: 'ownerId', width: 80},
                    {label: '出题时间', name: 'createTime', width: 80, editable:false,formatter:"date",formatoptions: {srcformat:'u',newformat:'Y-m-d H:i:s'}},
                    {label: '共享状态', name: 'isShare', width: 80},
                    {label: '所属课程', name: 'courseGroupId', width: 80},
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
        //编辑按钮事件
        $("#btnUpdate").unbind("click");
        $('#btnUpdate').click(function () {
            var data = datatable.rows('.selectedRow').data();

            if (data.length == 1) {
                window.location.href = "/teacher/testPaper/edit/" + data[0].Id;
            }
            else {
                //bootbox.setDefaults("locale", "zh_CN");
                //bootbox.alert("请选择一条数据！");
                layer.msg("请选择一条数据!");
            }
        });


        //创建副本
        $("#btnCreateCopy").unbind("click");
        $("#btnCreateCopy").click(function () {
            FunCopy();
        });

//        //删除按钮
//        $("#btnDelete").unbind("click");
//        $("#btnDelete").click(function () {
//            del();
//        });
        //开启共享
        $("#btnOpenShare").unbind("click");
        $("#btnOpenShare").click(function () {
            FunOperateShare("Open");
        });

        //关闭共享
        $("#btnCloseShare").unbind("click");
        $("#btnCloseShare").click(function () {
            FunOperateShare("Close");
        });
        //下拉框点击事件
        function dropdownClick(dropdown) {
            $("#dropdownMenuItem>li>a").removeClass('active');
            $(dropdown).addClass('active');

            var id = $(dropdown).attr("value");
            var name = $(dropdown).text();

            $('#lbCourseName').html("<strong>" + name + "</strong>");

        }
        //导航的单击事件
        function navClick(navObject) {
            $("#nav li").removeClass("active");
            $(navObject).addClass("active");
            initBtn();
        }

        //搜索栏字段
        function initSearchParameter(d) {
            var name = $("#key").val();
            var cousegroup = $("#dropdownMenuItem .active").attr("value");
            var shareFlag = $("#nav .active").attr("value");

            d.Name = name;
            d.CouseGroupId = cousegroup;
            d.ShareFlag = shareFlag;
        }
        // 编辑

//        function edit(event){
//            var id = getSelectedRow();
//            if(id == null){
//                return ;
//            }
//            window.location.href = "/question/edit/" + id;
//        }

        //删除
        function del(event){
            var ids = getSelectedRow();
            if(ids == null){
                return ;
            }
                $.ajax({
                    type: "POST",
                    url: "../question/edit",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
        }
        //创建副本
        function copy(event) {
            var id = getSelectedRow();
            if(id == null){
                return ;
            }
            data = new Array();
            $.ajax({
                url : '/question/copyEvalQuestion.action?id='+data[0] + '&type=' + type,
                type : 'POST',
                dataType : 'text',
                success : function(data) {
                    if(data != 'true'){
                        alert(r.msg);
                    }else{
                        alert('操作成功', function(index){
                            $("#jqGrid").trigger("reloadGrid");
                        });
                    }
                }
            });
        }

        //按钮初始化
        function initBtn() {
            if ($("#nav .active").attr("value") == 1) {
                $("#btnDelete").hide();
                $("#btnUpdate").hide();
                $("#btnCreate").hide();
                $("#btnOpenShare").hide();
                $("#btnCloseShare").hide();
            }
            else {
                $("#btnDelete").show();
                $("#btnUpdate").show();
                $("#btnCreate").show();
                $("#btnOpenShare").show();
                $("#btnCloseShare").show();
            }
        }

    </script>
</head>
<body>
<div>
    <ul class="nav nav-tabs" role="tablist" id="nav">
        <li role="presentation" onclick="navClick(this)" type="public" value="1">
            <a href="javascript:void(0);">共享评测题库</a>
        </li>
        <li role="presentation" onclick="navClick(this)" type="private" value="0">
            <a href="javascript:void(0);">个人评测题库</a>
        </li>
    </ul>
</div>

<!---课程组层--->
<div style="height:60px;margin:auto">
    <form action="${pageContext.request.contextPath }/question/course" method="post">
        <c:forEach items="${coursegroupList}" var="courses">
            <!--选择按钮-->
            <div class="dropdown" style="margin:15px 5px 0 35px;float:left;">
                <button class="btn dropdown-toggle btn-sm" type="button" id="dropdownMenu1"
                        data-toggle="dropdown">
                    <span class="caret"></span>
                    选择课程
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1"
                    id="dropdownMenuItem">
                    <li><a tabindex="-1" href="javascript:void(0);" value="5"
                           onclick="dropdownClick(this)">${courses.name }</a></li>
                </ul>
            </div>
            <!--选择结果-->
            <div style="height:20px;margin-top:20px;text-align:center;float:left;">
                <label style="font-size:11px"><b>&nbsp;&nbsp;当前课程:</b></label>
                <label id="lbCourseName"
                       style="color:#4bd083;font-size:11px"><strong>${courses.name }</strong></label>
            </div>
        </c:forEach>
    </form>
</div>

<!--搜索栏-->
<div style="margin:auto;margin-top:20px;">
    <div class="input-group" style="width:400px;margin:auto;">
        <input type="text" name="key" id="key" class="form-control" placeholder="请输入关键字"
               style="border-color:#4bd083">
        <span class="input-group-btn">
                                  <button class="btn btnSelect" onclick="DoSearch()">
                                         <span class="glyphicon glyphicon-search"></span>
                                  </button>
                             </span>
    </div>
</div>

<!--按钮组-->
<div style="margin:auto;margin-top:20px;margin-left:35px;">
    <a class="btn btn-sm" href="/question/create" id="btnCreate">添加</a>
    <a class="btn btn-sm" onclick="edit()" id="btnUpdate" style="display: inline-block;">编辑</a>
    <a class="btn btn-sm" onclick="del();" id="btnDelete" style="display: inline-block;">删除</a>
    <a class="btn btn-sm" href="javascript:void(0);" id="btnCreateCopy" style="display: inline-block;">创建副本</a>
    <a class="btn btn-sm" href="javascript:void(0);" id="btnOpenShare" style="display: inline-block;">开启共享</a>
    <a class="btn btn-sm" href="javascript:void(0);" id="btnCloseShare" style="display: inline-block;">关闭共享</a>
</div>
<table id="jqGrid"></table>
<div id="jqGridPager"></div>

</body>

</html>
