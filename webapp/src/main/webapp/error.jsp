<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-2-15
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="errorMsgDiv">
    <h2 align="center" style="font-size: 18px; color: red; ">
        程序异常，请联系管理员<br><br>
        错误码：<br><br>
    </h2>
    <h1 align="center" style="font-size: 60px; color: red;">
        <%=request.getAttribute("javax.servlet.error.status_code")%> <br>
    </h1>
    <br>
    <br>
    <h2 align="center" style="font-size: 18px; color: gray; ">
        信息： <%=request.getAttribute("javax.servlet.error.message")%> <br><br>
        异常： <%=request.getAttribute("javax.servlet.error.exception_type")%> <br><br>
    </h2>
</div>

</body>
</html>
