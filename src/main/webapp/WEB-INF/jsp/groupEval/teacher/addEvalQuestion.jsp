<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>添加群体评测题目</title>

</head>

<body>

<div class="titleName">
    <a href="<%=basePath%>teacher/interact/evalquestionList.jsp" class="label label-default">返回</a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    创建评论表       当前课程：<span style="color:red">${sessionScope.loginUser.curTeachingClass.course.courseName}</span>
</div>

<div id="addDiv"  class="addDiv"  style="width: 100%;">
    <div class="box">
        <div class="rounded">题目详细 </div>
        <div style="border: #CCC 1px solid;">
            <!--创建评测题目  -->
            <s:if test="groupEvalQuestion.getId() == null">
                <form action="interact/eval-question!create.action" id="addEvaForm" method="post">
                    <input type="hidden" name="id" id="evalq_id" value="${entity.id}"/>
                    <table>
                        <tr>
                            <td>
                                <span class="label label-primary">评论题目:</span>
                            </td>
                            <td colspan=3>
                                <input type="text" tip="输入评测题目(最多100字)" maxlength="100" required="required" style="width:600px;height:40px;"  id="evalTitle" name="entity.evalTitle" size="60" value="${entity.evalTitle}" maxlength="30">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <br>
                            </td>
                        </tr>
                        <tr>
                            <td><span class="label label-primary">题目详细:</span></td>
                            <td colspan=3>
                                <textarea tip="输入题目详细(最多200字)" maxlength="200" style="width:600px;height:100px;"  id="evalDetail" name="entity.evalDetail">${entity.evalDetail}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan=3>
                                <input type="submit" class="btn btn-primary" value="提交"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </s:if>

            <s:if test="entity.getId()!=null">
                <form action="interact/eval-question!update.action" id="addEvaForm" method="post">
                    <input type="hidden" name="ids" id="evalq_id" value="${entity.id}"/>
                    <table>
                        <tr>
                            <td>
                                <span class="label label-primary">评论题目:</span>
                            </td>
                            <td colspan=3><input type="text" tip="输入评测题目(最多100字)" maxlength="100" required="required"  style="width:600px;height:40px;" id="evalTitle" name="entity.evalTitle" size="60" value="${entity.evalTitle}" maxlength="30"></td>

                        </tr>
                        <tr>
                            <td>
                                <br>
                            </td>
                        </tr>
                        <tr>
                            <td><span class="label label-primary">题目详细:</span></td>
                            <td colspan=3>
                                <textarea tip="输入评测详细信息(最多200字)" maxlength="200" style="width:600px;height:100px;"  id="evalDetail" name="entity.evalDetail" >${entity.evalDetail}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td colspan=3>
                                <input type="submit" class="btn btn-primary" value="修改"/>
                            </td>
                        </tr>
                    </table>
                </form>

                <div class="setBar">
                    <div id="workcontainer" class="setBar" style="background-color:D9EDF7;">
                        <a id="pickfiles" href="javascript:;">添加文件</a>
                        <a id="uploadfiles" href="javascript:;">上传文件</a><br />
                        <div id="attchment"></div>
                    </div>
                </div>
            </s:if>
        </div>
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
                url:'interact/eval-question!getQListXML2.action?id=${entity.id}',
                dataType:'xml',
                success:function(data){
                    var selector=document.getElementById("qselector");
                    var currentId=$("#evalq_id").val();
                    $(data).find("q").each(function(){
                        var q=$(this);
                        var id=q.find('id').text();
                        if(id!=currentId){
                            var name=q.find('name').text();
                            var option=document.createElement("option");
                            option.value=id;
                            option.text=name;
                            try{
                                selector.add(option);
                            }catch(e){
                                selector.add(option,null);
                            }
                        }
                    });
                }
            });
        </script>

        <table  width=100% height=70%>
            <tr>
                <td width="100" valign="top">
                    <div style="border: #ccc 1px solid;420px" >
                        <div class="titleName" >考查项目
                            <a href="javascript:void(0)" onclick="createAddItemWin()">添加</a>
                            <a href="javascript:void(0)" onclick="deleteItem()">删除</a>
                            <a href="javascript:void(0)" onclick="modifyItem()">修改</a>
                        </div>
                        <div id="grid1" style="width:200px; height:400px; background-color:white;"></div>
                        <script type="text/javascript">
                            $(function() {
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
                        <div class="titleName" >考查指标:
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

                    </div></td>

            </tr>
        </table>
    </div>
</div>

<!--弹出层开始-->

<div class="jqmWindow" style="width: 700px;" id="popWindow">
    <div class="drag">群体评测项目详情<div class="close"></div></div><div id="wrapper">
    <div id="container">
        <div class="topArc"><span class="left"><span class="right"></span></span></div>
        <div id="previewDetail" style="overflow:scroll;width:670px;height:500px;"></div>
        <div class="bottomArc"><span class="left"><span class="right"></span></span></div>
    </div>
</div></div>
<!--弹出层结束-->
</body>
</html>