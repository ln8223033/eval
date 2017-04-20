<%@ page language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>添加群体评测题目</title>
    <link href="/css/Content/bootstrap.min.css" rel="stylesheet">
    <link href="/css/assets/dataTables.bootstrap.css" rel="stylesheet">
    <link href="/css/assets/font-awesome.min.css" rel="stylesheet">
    <link href="/css/assets/beyond.min.css" rel="stylesheet">
    <link href="/css/assets/animate.min.css" rel="stylesheet">
    <link href="/css/assets/darkblue.min.css" rel="stylesheet">
    <link href="/css/Content/site.css" rel="stylesheet">
    <script type="text/javascript" src="/js/common/bootstrap.js"></script>
    <script src="/js/statics/plugins/layer/layer.js"></script>
    <script type="text/javascript" src="/js/plugins/WdatePicker.js"></script>
    <script type="text/javascript" src="/js/plugins/plupload.full.js"></script>
    <script type="text/javascript" src="/js/plugins/browserplus-min.js"></script>
    <script type="text/javascript" src="/js/evaluate.js"></script>
    <script type="text/javascript" src="/js/common/jquery.form.js"></script>
    <script type="text/javascript" src="/js/common/jquery.corners.js"></script>


    <script type="text/javascript">
        var dhxWins;
        var itemGrid;
        var subitemGrid;
        function doOnLoad() {
            dhxWins = new dhtmlXWindows();
            dhxWins.enableAutoViewport(false);
            dhxWins.attachViewportTo("addItemDiv");
        }


        //删除附件
        function deleteFile(attachmentId){
            $.ajax({
                url:"interact/eval-question!deleteAttachment.action?id=<s:property value="entity.id"/>&attachmentId="+attachmentId,
                dataType:'text',
                success:function(data){
                    if(data=='true'){
                        var temp =$("#"+attachmentId);
                        $("#"+attachmentId).remove();
                    }else{
                        alert(data);
                    }
                }
            });
        }

    </script>
</head>
<body>

<div class="container-fluid" style="background:#fff;">
    <div class="row">
        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12">
            <div class="navigationTitle" style="z-index: 9999;position: fixed;top: 0;margin: 0 auto;">

            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12 col-md-12 col-xs-12 col-sm-12"
             style="padding:30px 30px; margin-top:30px; min-width: 1000px">

            <link href="/css/Content/backgroundStyle.css" rel="stylesheet">

            <form id="addEvaForm" action="${pageContext.request.contextPath }/question/create" method="post">
                <input type="hidden" name="id" value="${groupEvalQuestion.id}"/>
                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">评测题目：</label>
                    <div class="col-sm-11">
                        <input type="text" tip="输入评测题目(最多100字)" maxlength="100" required="required"
                               style="width:600px;height:40px;" name="evalTitle" size="60"
                               value="${groupEvalQuestion.title}" maxlength="30">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-1 control-label no-padding-right">题目详情</label>
                    <div class="col-sm-11">
                        <textarea tip="输入题目详细(最多200字)" maxlength="200" style="width:600px;height:100px;"
                                  name="evalDetail">${groupEvalQuestion.titleDetail}</textarea>
                    </div>
                </div>
                <div style="margin-bottom:10px;text-align:right">
                    <button class="btn btn-primary " style="width:100px;" type="submit" >保存</button>

                    <button class="btn " type="button" style="width:100px;" id="btnCancel">
                        <a href="<%=basePath%>question/manage">返回上一页</a>
                    </button>
                </div>
                <div class="setBar">
                    <div  class="setBar" style="background-color:darkturquoise;">
                        <form action="${pageContext.request.contextPath }/question/upload" method="post" enctype="multipart/form-data">
                            选择文件:<input type="file" name="file" width="120px">
                            <input type="submit" value="添加文件">
                        </form>
                    </div>
                </div>
                <hr class="hr-dotted-blue">


                <div>

                </div>
                <div id="addItemDiv">
                    <input type="button" class="btn btn-primary" value="查看本项目指标" onclick="seeSelfQ()"/>
                    <c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>

                    <input type="button" class="btn btn-primary" value="复制项目指标" onclick="copyQ()">

                    <select id="qselector" class="select">
                        <option value="0">----可选-----</option>
                    </select>
                    <script type="text/javascript">
                        $.ajax({
                            url: 'interact/eval-question!getQListXML2.action?id=${entity.id}',
                            dataType: 'xml',
                            success: function (data) {
                                var selector = document.getElementById("qselector");
                                var currentId = $("#evalq_id").val();
                                $(data).find("q").each(function () {
                                    var q = $(this);
                                    var id = q.find('id').text();
                                    if (id != currentId) {
                                        var name = q.find('name').text();
                                        var option = document.createElement("option");
                                        option.value = id;
                                        option.text = name;
                                        try {
                                            selector.add(option);
                                        } catch (e) {
                                            selector.add(option, null);
                                        }
                                    }
                                });
                            }
                        });
                    </script>

                    <table width=100% height=70%>
                        <tr>
                            <td width="100" valign="top">
                                <div style="border: #ccc 1px solid;420px">
                                    <div class="titleName">考查项目
                                        <a href="javascript:void(0)" onclick="createAddItemWin()">添加</a>
                                        <a href="javascript:void(0)" onclick="deleteItem()">删除</a>
                                        <a href="javascript:void(0)" onclick="modifyItem()">修改</a>
                                    </div>
                                    <div id="grid1" style="width:200px; height:400px; background-color:white;"></div>
                                    <script type="text/javascript">
                                        $(function () {
                                            itemInit();
                                        });
                                        function itemInit() {
                                            itemGrid = new dhtmlXGridObject('grid1');
                                            itemGrid.setImagePath("plugins/dhtmlx/dhtmlxGrid/codebase/imgs/");
                                            itemGrid.setHeader("检查项目名称");
                                            itemGrid.setInitWidths("*");
                                            itemGrid.setColAlign("left");
                                            itemGrid.setColTypes("ed");
                                            itemGrid.attachEvent("onRowSelect", doOnItemSelected);
                                            itemGrid.init();
                                            itemGrid.setSkin("dhx_skyblue");
                                            <s:if test="entity.getId()!=null">
                                            itemGrid.loadXML("interact/eval-question!getItemXML.action?id=${entity.id}");
                                            </s:if>
                                        }
                                    </script>
                                </div>
                            </td>
                            <td valign="top">
                                <div style="border: #ccc 1px solid;height:432px">
                                    <div class="titleName">考查指标:
                                        <span id="currentItem">请选择一个考查项目</span>

                                        <a href="javascript:void(0)" onclick="createAddSubitemWin()">添加</a>
                                        <a href="javascript:void(0)" onclick="deleteSubItem()">删除</a>
                                        <a href="javascript:void(0)" onclick="modifySubItem()">修改</a>

                                    </div>
                                    <div id="grid2" style="width:620px; height:400px; background-color:white;"></div>
                                    <script type="text/javascript">
                                        subitemGrid = new dhtmlXGridObject('grid2');
                                        subitemGrid.setImagePath("plugins/dhtmlx/dhtmlxGrid/codebase/imgs/");
                                        subitemGrid.setHeader("检查指标,标准分");
                                        subitemGrid.setInitWidths("*,100");
                                        subitemGrid.setColAlign("left,left");
                                        subitemGrid.setColTypes("ed,ed");
                                        subitemGrid.init();
                                        subitemGrid.setSkin("dhx_skyblue");
                                    </script>

                                </div>
                            </td>

                        </tr>
                    </table>

                </div>
            </form>
        </div>
    </div>
</div>
</div>
</body>
</html>