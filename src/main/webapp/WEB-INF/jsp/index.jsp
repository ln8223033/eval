<%@ page language="java" import="java.util.*,edu.dbke.model.org.User" pageEncoding="UTF-8"%>
<%@ page import="edu.dbke.util.*" %>
<%
    response.setHeader("P3P","CP=CAO PSA OUR");//p3p
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String uucUploadUrl = SystemConf.getConf("uucUploadUrl");
    String uucUploadDeleteUrl = SystemConf.getConf("uucUploadDeleteUrl");
    String uucDocPlayUrl = SystemConf.getConf("uucDocPlayUrl");
    String uucFileDownUrl = SystemConf.getConf("uucFileDownUrl");
    String fileNode = SystemConf.getConf("fileNode");
    User user = (User)request.getSession().getAttribute("loginUser") ;//登录用户
    String systemId = request.getParameter("systemId");//应用系统id
    request.setAttribute("systemId", systemId);
    String businessId = request.getParameter("businessId");//应用id
    request.setAttribute("businessId", businessId);

    String currentOrgId = user != null ? user.getOrg_id() : null;//登录用户机构Id
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>评测管理系统</title>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <script type="text/javascript" src="script/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="script/cp.js"></script>
    <script type="text/javascript" src="script/index.js"></script>
    <script type="text/javascript" src="script/dialog_cp.js"></script>
    <script language="javascript">
        $(function(){
            var currentOrgId = "<%=currentOrgId %>";//用户名称
            alert("欢迎用户" + currentOrgId + "登录");
            var applicationSystemId = "<%=systemId %>" ;//应用系统id
            alert("应用系统ID是" + applicationSystemId);

            if(null!=getCookie("cp_lock")){
                lockPage();
                $("#lock_pwd").attr("disabled","true");
                $("#close_dialog").hide();
                $("#lock_btn").attr("disabled","true");
            }
        });
    </script>
</head>
<frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
    <frame src="common/top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" />
    <frame src="common/center.jsp" name="mainFrame" id="mainFrame" />
    <frame src="common/footer.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>
</html>
