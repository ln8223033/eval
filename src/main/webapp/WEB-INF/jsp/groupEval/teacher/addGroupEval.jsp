<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2016/12/30
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加新评测</title>
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
    </script>
</head>
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
<table id="flex" style="display: none"></table>

<!-- 课程选择树 -->
<!-- 弹出层开始 -->
<!-- 注意事项：
1、form表单id名字（item）和flexgrid操作对象应相同
2、<input type="hidden" name="id">为主键必须要的
3、<input id="submit" type="button" value="提交" class="btTxt"/>类型为button
-->
    <div class="drag">
        群体评测题目详情
        <div class="close"></div>
    </div>
    <table id="jqGrid"></table>
    <div id="jqGridPager"></div>
        <div id="container">
            <div class="topArc">
					<span class="left"><span class="right"></span>
					</span>
            </div>
            <form id="evalquestion" method="post" action="eval/create">
                <input type="hidden" name="id" id="evalquestionid"/>
                <ul class="form-box clearfix">
                    <li><label class="desc">题目：</label>
                        <div class="form-ipt-box">
                            <input type="text" id="evalquestionTitle" maxlength="255" class="short2"
                                   style="width:320px" name="entity.evalTitle" required="true"
                                   reg="nonBlank" tip="请输入名称" /><b style="color:#FF0000">*</b>
                        </div>
                    </li>
                    <li><label class="desc">描述：</label>
                        <div class="form-ipt-box">
							<textarea name="entity.evalDetail" id="evalquestionDetail" style="width:320px;">

							</textarea>
                        </div>
                    </li>
                    <li><div style="width: 530px" align="center">
                        <input id="submit_evalques" type="button" value="提交" /> <input
                            type="button" onclick="$('#projQuestion').jqmHide();" value="返回"
                    />
                    </div>
                    </li>
                </ul>
            </form>
            <div class="bottomArc">
					<span class="left"><span class="right"></span>
					</span>
            </div>
        </div>
    </div>
</div>
<!-- 弹出层结束 -->

<!-- 下面的页面 -->
&nbsp;&nbsp;评测信息<br>
<form action="eval/create" id="submitConfirm">
    <input type="hidden" value="" name="entity.id" id="evalQuestionId"><!-- 评测题目id -->

    <div class="bar">
        <div class="alert alert-info">
            <font color="red">*</font>
            选择班级:<br>
            <table border="1">
                <tr>
                    <s:iterator value="usefulTeachingClasses">
                        <td>
                            <input type="checkbox" name="classes" value="<s:property value='id'/>"/>
                            <s:property value="teachingClassName"/>
                        </td>
                    </s:iterator>
                </tr>
            </table>
        </div>
    </div>


    <a href="javascript:void(0)" id="argsSet">&nbsp;评测参数设置</a>

    <div id="argsSetArea" class="alert alert-info" style="width:400px;display:none;">
        <div class="rounded" id="argForm">评论参数设置</div>
        <div style="margin:5px;padding:5px;" id="datePick">
			  		<span class="argu">
						发布开关
						<input type="radio" name="autoPublish" value="true" <s:if test="entity.isAutoPublish() == true">checked</s:if>/>开
						<input type="radio" name="autoPublish" value="false"  <s:if test="entity.isAutoPublish() == false">checked</s:if>/>关
					</span>
            <div>
                评测开始时间
                <input type="text" name="startTime" value="<s:date name="entity.startTime" format="yyyy-MM-dd HH:mm:ss"/>"
                       id="time1"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly/>
                <br/>
                分组截止时期
                <input type="text" name="groupTime" value="<s:date name="entity.groupTime" format="yyyy-MM-dd HH:mm:ss"/>"
                       id="time4"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly/>
                <br/>
                作品上传截止
                <input type="text" name="eatime" value="<s:date name="entity.eatime" format="yyyy-MM-dd HH:mm:ss"/>"
                       id="time3"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly/>
                <br/>
                评测结束时间
                <input type="text" name="endTime" value="<s:date name="entity.endTime" format="yyyy-MM-dd HH:mm:ss"/>"
                       id="time2"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly/>
                <br/>
            </div>
        </div>
    </div>

    <div class="bar">
        <div class="item">
            <input type="submit"  style="width:80px; height:30px;" class="btn btn-primary"  onClick="return check()" value="确认添加">
        </div>
    </div>
</form>
<br>
</body>
</html>
