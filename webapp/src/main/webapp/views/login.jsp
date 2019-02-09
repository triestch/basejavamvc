<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-2-9
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录</title>
    <c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
    <script src="${path}/js/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function loginVerify() {
            var v = true;
            if ($.trim($("#txt_UserName").val()) == "") {
                v = false;
                alert("请填写用户名");
                return false;
            }
            if ($.trim($("#txt_UserPwd").val()) == "") {
                v = false;
                alert("请填写密码");
                return false;
            }
            if ($.trim($("#txt_CheckCode").val()) == "") {
                v = false;
                alert("请填写验证码");
                return false;
            }
            return v;
        }

        function resetInfo() {
            $("#txt_UserName").val("");
            $("#txt_UserPwd").val("");
            $("#txt_CheckCode").val("");
        }

        function changeImg() {
            var imgSrc = $("#imgObj");
            var timestamp = (new Date()).valueOf();
            var src ="${path}/GetVerfiyCodeServlet?timestamp="+timestamp;
            imgSrc.attr("src", src);
        }

    </script>
</head>
<body>
<p style="color: red;">${msg}</p>
<form action="${path}/LoginAction" method="post">
    用户名：<input type="text" name="txt_UserName" id="txt_UserName"> <br/>
    密码：<input type="password" name="txt_UserPwd" id="txt_UserPwd"> <br/>
    验证码：<input type="text" name="txt_CheckCode" id="txt_CheckCode">
    <img id="imgObj" src="${path}/GetVerfiyCodeServlet" alt="点击换一张" onclick="changeImg()">
    <br/>
    <input type="submit" value="登录" onclick="return loginVerify()"/>
    &nbsp;&nbsp;&nbsp;
    <input type="button" value="重置" onclick="resetInfo()"/>
</form>
</body>

</html>


