<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 2017/2/16
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="itemForm"
      action="${pageContext.request.contextPath }/class/insert"method="post" enctype="multipart/form-data">
    <input type="hidden" name="classid" /> 添加班级息：
    <table width="100%" border=1>

        <tr>
            <td width="150px">班级名称</td>
            <td width="200px"><input type="text" name="className" width="80px"></td>
            <td><font color="red">*</font>最多40个字符</td>
        </tr>


        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交" />

                <input type="button"  value="取消" id="btnConcel"/>
            </td>
        </tr>
    </table>

</form>
</body>
</html>
