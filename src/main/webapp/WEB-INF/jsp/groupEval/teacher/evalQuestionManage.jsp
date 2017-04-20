<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>" />
    <title>群体评测题库</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
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
    <style type="text/css">
        .form-box li {
            width: 520px;
        }
        .form-box li.half-wth {
            width: 260px;
        }
        input.projTitle {
            cursor: pointer;
            height: 20px;
            background-color: transparent;
            border: 0px solid #FFFFFF;
        }
        input.changedPaperName {
            cursor: pointer;
            height: 20px;
            background-color: transparent;
            border: 1px solid #7D26CD;
        }
        #wrapper {
            margin: 10px;
        }
        #container {
            width: 530px;
        }
        ul.ztree {
            margin-top: 10px;
            border: 1px solid #617775;
            background: #f0f6e4;
            width: 220px;
            height: 360px;
            overflow-y: scroll;
            overflow-x: auto;
        }
    </style>
    <script type="text/javascript">
        var actions="";
        var uploader;
        $(function() {
            var privateFlexigridParam = {
                title : '评测题目管理', url : '/question/list?listType=private', dataType : 'json',
                colModel : [
                    {label: '题目', name: 'title',sortable : false, width: 80},
                    {label: '题目的详细内容', name: 'titleDetail',sortable : false, width: 80},
                    {label: '出题人', name: 'ownerId', width: 80},
                    {label: '出题时间', name: 'createTime', width: 80, editable:false,formatter:"date",formatoptions: {srcformat:'u',newformat:'Y-m-d H:i:s'}},
                    {label: '共享状态', name: 'isShare', width: 80},
                    {label: '所属课程', name: 'courseGroupId', width: 80},
                ],
                buttons : [
                    {separator : true},
                    {name : '添加', bclass : 'add', onpress : action},
                    {separator : true},
                    {name : '查看或编辑', bclass : 'edit', onpress : action},
                    {separator : true},
                    {name : '删除', bclass : 'delete', onpress : action},
                    {separator : true},
                    {name : '创建副本', bclass : 'eye', onpress : action},
                    {separator : true },
                ],
                searchitems : [
                    {display : '名称',name : 'evalTitle', isdefault : true}
                ],
                sortname : "createTime", sortorder : "desc",
                usepager : true, useRp : true,
                checkbox : true,// 是否要多选框
                rowId : 'id',// 多选框绑定行的id
                rp : 15,//分页参数
                showTableToggleBtn : true,
                height : getTotalHeight() - 175
            };
            var publicFlexigridParam = {
                title : '题目管理', url : '/question/list?listType=public', dataType : 'json',
                colModel : [
                    {label: '题目', name: 'title',sortable : false, width: 80},
                    {label: '题目的详细内容', name: 'titleDetail',sortable : false, width: 80},
                    {label: '出题人', name: 'ownerId', width: 80},
                    {label: '出题时间', name: 'createTime', width: 80, editable:false,formatter:"date",formatoptions: {srcformat:'u',newformat:'Y-m-d H:i:s'}},
                    {label: '共享状态', name: 'isShare', width: 80},
                    {label: '所属课程', name: 'courseGroupId', width: 80},
                ],
                buttons : [
                    {separator : true},
                    {name : '查看', bclass : 'eye', onpress : action},
                    {separator : true},
                    {name : '创建副本', bclass : 'add', onpress : action},
                    {separator : true }
                ],
                searchitems : [
                    {display : '名称',name : 'evalTitle', isdefault : true}
                ],
                sortname : "createTime", sortorder : "desc",
                usepager : true, useRp : true,
                checkbox : true,// 是否要多选框
                rowId : 'id',// 多选框绑定行的id
                rp : 15,//分页参数
                showTableToggleBtn : true,
                height : getTotalHeight() - 175
            };
            $("#flex").flexigrid(privateFlexigridParam);
            $(".jqmWindow").jqm({modal : true,overlay : 10}).jqmAddClose('.close').jqDrag('.drag');

            $("#submit_evalques").click(function() {
                var dataToSend = $("#evalquestion").serialize();
                var reg = /^\s*id=.*?&/g;
                dataToSend = dataToSend.replace(reg, "");

                if ($("#evalquestionid").val() == "") {
                    actions = "interact/eval-question!create.action";
                } else {
                    actions = "interact/eval-question!update.action?ids="+$("#evalquestionid").val();
                }
                $.ajax({
                    url : actions,
                    data : dataToSend,
                    type : 'POST',
                    dataType : 'text',
                    success : function(data) {
                        $("#evalquestion").jqmHide();
                        if (data != 'true') {
                            $.messager.alert('出错信息', data, 'error');
                            return;
                        }
                        $.messager.show({title : '成功提示',msg : '数据已保存',timeout : 1500,showType : 'fade'});
                        $('#flex').flexReload();
                    }
                });
            });

            $(".tabs-inner").click(function() {
                var type = $(this).attr("type");
                $(this).parent().siblings().removeClass("tabs-selected");
                $(this).parent().addClass("tabs-selected");
                if (type == "public") {
                    $("#nowType").val("public");
                    $('<table id="flex" style="display: none"></table>').insertAfter(".flexigrid");
                    $(".flexigrid").remove();
                    $("#flex").flexigrid(publicFlexigridParam);
                } else if (type == "private") {
                    $("#nowType").val("private");
                    $('<table id="flex" style="display: none"></table>').insertAfter(".flexigrid");
                    $(".flexigrid").remove();
                    $("#flex").flexigrid(privateFlexigridParam);
                } else {
                    return false;
                }
            });

            uploader = new plupload.Uploader({
                runtimes : 'gears,html5,flash,silverlight,browserplus',
                browse_button : 'pickfiles',
                container : 'workcontainer',
                max_file_size : '10mb',
                url : '<%=basePath%>project/proj-question!addAttachment.action?id='+$("#projQuestionId").val(),
                flash_swf_url : 'plugins/plupload/plupload.flash.swf',
                silverlight_xap_url : 'plugins/plupload/plupload.silverlight.xap',
                resize : {width : 320, height : 240, quality : 90}
            });

            uploader.bind('FilesAdded', function(up, files) {
                $.each(files, function(i, file) {
                    $('#attchment').append(
                        '<div id="' + file.id + '">' +
                        file.name + ' (' + plupload.formatSize(file.size) + ') <b></b>' +
                        '</div>');
                });
                up.refresh(); // Reposition Flash/Silverlight
            });

            uploader.bind('UploadProgress', function(up, file) {
                $('#' + file.id + " b").html(file.percent + "%");
            });

            uploader.bind('Error', function(up, err) {
                $('#attchment').append("<div>Error: " + err.code +
                    ", Message: " + err.message +
                    (err.file ? ", File: " + err.file.name : "") +
                    "</div>"
                );
                up.refresh(); // Reposition Flash/Silverlight
            });

            uploader.bind('FileUploaded', function(up, file, response) {
                var returnData = eval("("+response.response+")");
                if(returnData.result != 'true'){
                    alert(returnData.result);
                }else{
                    $('#' + file.id).remove();
                    var appendData = "";
                    $.each(returnData.data,function(idx,data){
                        appendData = appendData + "<div class=\"fileList\" id=\""+data.id+"\">";
                        appendData = appendData +"<a href=\"project/proj-question!downloadAttachment.action?ids="+data.id+"\">"+data.name+"</a>&nbsp;&nbsp;";
                        appendData = appendData + "<a href=\"javascript:void(0)\" onclick=\"deleteFile('"+data.id+"')\">删除</a></div>";
                    });
                    $("#attchment").append(appendData);
                }
            });
            $('#uploadfiles').click(function(e) {
                uploader.start();
                e.preventDefault();
            });
            uploader.init();
        });

        function listAttachment() {
            $("#attchment").html("");
            var url="project/proj-question!listAttachment.action?id="+$("#projQuestionId").val();
            uploader.settings.url = '<%=basePath%>project/proj-question!addAttachment.action?id='+$("#projQuestionId").val();
            $.ajax({
                url:url,
                dataType:'json',
                type:'post',
                success:function(data){
                    var fileDetail = eval(data);
                    var appendData = "";
                    $.each(fileDetail.data,function(idx,data){
                        appendData = appendData + "<div class=\"fileList\" id=\""+data.id+"\">";
                        appendData = appendData +"<a href=\"project/proj-question!downloadAttachment.action?ids="+data.id+"\">"+data.name+"</a>&nbsp;&nbsp;";
                        appendData = appendData + "<a href=\"javascript:void(0)\" onclick=\"deleteFile('"+data.id+"')\">删除</a></div>";
                    });
                    $("#attchment").append(appendData);
                }
            });
            $("#evalquestion").find("li").eq(2).show();
        }

        function deleteFile(workId){
            $.ajax({
                url:"project/proj-question!deleteAttachment.action?id="+$("#projQuestionId").val()+"&ids="+workId,
                dataType:'text',
                success:function(data){
                    if(data=='true'){
                        var temp =$("#"+workId);
                        $("#"+workId).remove();
                    }else{
                        alert(data);
                    }
                }
            });
        }

        function action(com, grid) {
            switch (com) {
                case '添加':
                <s:if test="#session.loginUser.curCourse eq null">
                    alert("尚未选择课程！");
                    return false;
                </s:if>
                    window.location.href = "<%=basePath%>interact/eval-question!forwordToCreate.action";
                    break;
                case '创建副本':
                    selected_count = $('.trSelected', grid).length;
                    if (selected_count == 0) {
                        $.messager.show({title:'提示信息', msg:'请选择一条数据!', timeout:1500,showType:'fade'});
                        return;
                    }
                    if (selected_count > 1) {
                        $.messager.show({title:'提示信息', msg:'抱歉只能同时编辑一条数据!', timeout:1500,showType:'fade'});
                        return;
                    }
                    data = new Array();
                    var type = $("#nowType").val() ;
                    $('.trSelected td', grid).each(function(i) {var str = $(this).children('div').text().trim();if(str.charCodeAt() == 160){data[i] = ""}else{data[i] = str}});
                    $.ajax({
                        url : '<%=basePath%>interact/eval-question!copyEvalQuestion.action?id='+data[0] + '&type=' + type,
                        type : 'POST',
                        dataType : 'text',
                        success : function(data) {
                            if(data != 'true'){
                                $.messager.alert('错误提示', data,'error');
                                return;
                            }else{
                                $('#flex').flexReload();
                            }
                        }
                    });
                    break;
                case '查看':
                    selected_count = $('.trSelected', grid).length;
                    if (selected_count == 0) {
                        $.messager.show({title:'提示信息', msg:'请选择一条数据!', timeout:1500,showType:'fade'});
                        return;
                    }
                    if (selected_count > 1) {
                        $.messager.show({title:'提示信息', msg:'抱歉只能同时查看一条数据!', timeout:1500,showType:'fade'});
                        return;
                    }
                    data = new Array();
                    $('.trSelected td', grid).each(function(i) {var str = $(this).children('div').text().trim();if(str.charCodeAt() == 160){data[i] = ""}else{data[i] = str}});
                    window.location.href = "<%=basePath%>interact/eval-question!preview.action?id=" + data[0] + "&share=yes";
                    break;
                case '查看或编辑':

                    selected_count = $('.trSelected', grid).length;
                    if (selected_count == 0) {
                        $.messager.show({title:'提示信息', msg:'请选择一条数据!', timeout:1500,showType:'fade'});
                        return;
                    }
                    if (selected_count > 1) {
                        $.messager.show({title:'提示信息', msg:'抱歉只能同时编辑一条数据!', timeout:1500,showType:'fade'});
                        return;
                    }
                    data = new Array();
                    $('.trSelected td', grid).each(function(i) {var str = $(this).children('div').text().trim();if(str.charCodeAt() == 160){data[i] = ""}else{data[i] = str}});
                    window.location.href = "<%=basePath%>interact/eval-question!preview.action?id=" + data[0];
                    break;
                case '删除':
                    selected_count = $('.trSelected', grid).length;
                    if (selected_count == 0) {
                        $.messager.show({title : '提示信息',msg : '请选择至少一条记录!',timeout : 1500,showType : 'fade'});
                        return;
                    }
                    names = '';
                    $('.trSelected td:nth-child(3) div', grid).each(function(i) {
                        if (i)
                            names += ',';
                        names += $(this).text();
                    });
                    ids = '';
                    $('.trSelected td:nth-child(2) div', grid).each(function(i) {
                        if (i)
                            ids += ',';
                        ids += $(this).text();
                    });
                    $.messager.confirm('删除确认','确定删除[' + names + ']',function(r) {
                        if (r) {
                            $.ajax({
                                url : 'interact/eval-question!delete.action',
                                data : {ids : ids},
                                type : 'POST',
                                dataType : 'text',
                                success : function(data) {
                                    if (data != 'true') {
                                        $.messager.alert('错误提示',data,'error');
                                        return;
                                    }
                                    $.messager.show({title : '成功提示',msg : '删除数据成功',timeout : 1500,showType : 'fade'});
                                    $('#flex').flexReload();
                                }
                            });
                        }
                    });
                    break;
                case '共享':
                    selected_count = $('.trSelected', grid).length;
                    if (selected_count == 0) {
                        $.messager.show({title:'提示信息', msg:'请选择一条数据!', timeout:1500,showType:'fade'});
                        return;
                    }
                    names = '';
                    $('.trSelected td:nth-child(3) div', grid).each(function(i) {
                        if (i)
                            names += ',';
                        names += $(this).text();
                    });
                    ids = '';
                    $('.trSelected td:nth-child(2) div', grid).each(function(i) {
                        if (i)
                            ids += ',';
                        ids += $(this).text();
                    });

                    $.messager.confirm('共享确认','确定共享[' + names + ']',function(r) {
                        if (r) {
                            $.ajax({
                                url : 'interact/eval-question!share.action',
                                data : {ids : ids},
                                type : 'POST',
                                dataType : 'text',
                                success : function(data) {
                                    if (data != 'true') {
                                        $.messager.alert('错误提示',data,'error');
                                        return;
                                    }
                                    $.messager.show({title : '成功提示',msg : '共享数据成功',timeout : 1500,showType : 'fade'});
                                    $('#flex').flexReload();
                                }
                            });
                        }
                    });
                    break;
            }
        }

        function isShare(id) {
            $.ajax({
                url : 'interact/eval-question!share.action?ids=' + id,
                type : 'POST',
                dataType : 'text',
                success : function(data) {
                    $("#popWindow").jqmHide();
                    if (data != 'true') {
                        $.messager.alert('出错信息', data, 'error');
                        return;
                    }
                    $.messager.show({title : '成功提示',msg : '数据已保存',timeout : 1500,showType : 'fade'});
                    $('#flex').flexReload();
                }
            });
        }

    </script>
</head>
<body>
<div class="tabs-wrap">
    <ul class="tabs" id="explore-nav" style="width:100%;over-flow:hidden;">
        <s:if test='#session.loginUser.userType eq "teacher"'>
            <li><a style="float:left;" href="javascript:void(0)"
                   onclick="chooseCourse(this)">选择课程</a>
                &nbsp;&nbsp;当前课程：${loginUser.curCourse.courseName}</li>
        </s:if>
        <li>
            <a class="tabs-inner" type="public" href="javascript:void(0)"><span
                    class="tabs-title">共享评测题库</span>
            </a>
        </li>
        <li class="tabs-selected">
            <a class="tabs-inner" type="private"
               href="javascript:void(0)"><span class="tabs-title">个人评测题库</span>
            </a>
        </li>
        <li>
            <input type="hidden" value="private" id="nowType">
        </li>
    </ul>
</div>
<table id="flex" style="display: none"></table>

<!-- 课程选择树 -->
<div id="courseContent" class="courseContent"
     style="display:none;position:absolute;top:0px;left:0px;z-index:30000;">
    <ul id="courseTree" class="ztree"
        style="margin-top:0; width:150px; height: 300px;"></ul>
</div>
<!-- 弹出层开始 -->
<!-- 注意事项：
1、form表单id名字（item）和flexgrid操作对象应相同
2、<input type="hidden" name="id">为主键必须要的
3、<input id="submit" type="button" value="提交" class="btTxt"/>类型为button
-->
<div class="jqmWindow"
     style="position:absolute;left:105px;top:110px;width: 552px;"
     id="projQuestion">
    <div class="drag">
        群体评测题目详情
        <div class="close"></div>
    </div>
    <div id="wrapper">
        <div id="container">
            <div class="topArc">
					<span class="left"><span class="right"></span>
					</span>
            </div>
            <form id="evalquestion" method="post">
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
                        <input
                                type="button" onclick="$('#projQuestion').jqmHide();" value="返回"
                                class="btTxt" />
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
</body>
</html>
