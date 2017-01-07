
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>群体评测管理</title>
</head>
<body>
<div style="background-color:B1C3FA; height: 40px;">
    <center><h4 class="fixie"><b>群体评测管理</b></h4></center>
    <hr style="height:5px;border:none;border-top:2px solid #555555;">
</div>
<div>
    <form action="${pageContext.request.contextPath}/groupeval/groupEval/insert.action" method="post">
        <table width="100%" bgcolor="EDF5FF" border="1px">
            <tr>
                <td colspan="3" align="center"><font size="1"><b>添加系统档案盒</b></font></td>
            </tr>
            <tr>
                <td colspan="3"><hr></td>
            </tr>
            <tr>
                <td width="150px">档案盒类型名称:</td>
                <td width="200px"><input type="text" name="archiveBoxTypeName" width="80px"></td>
                <td><font color="red">*</font>最多40个字符</td>
            </tr>
            <tr>
                <td width="150px">档案盒类型代码:</td>
                <td width="200px"><input type="text" name="archiveBoxTypeId" width="80px"></td>
                <td><font color="red">*</font>最多40个字符</td>
            </tr>
            <tr>
                <td colspan="3" align="center">
                    <input type="submit" value="[提交]">
                    <input type="reset" value="[重置]">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
