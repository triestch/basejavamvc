<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-2-10
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>新增</title>
    <c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
    <script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form action="${path}/AddServlet" method="post">
    登录名：<input name="n0" type="text"/> <br/>
    姓名：<input name="n1" type="text"/> <br/>
    性别：
    <select name="n2">
        <option value="0">男</option>
        <option value="1">女</option>
    </select><br/>
    部门：
    <input type="checkbox" name="n3" value="部门1"   />部门1
    <input type="checkbox" name="n3"  value="部门2" />部门2
    <input type="checkbox" name="n3" value="部门3"  />部门3
    <input type="checkbox" name="n3" value="部门4"  />部门4
    <br/>
    职务：
    <input type="radio" name="n4" value="职务1"  />职务1
    <input type="radio" name="n4"  value="职务2" />职务2
    <br/>
    时间：
    <input name="n5" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" class="Wdate"/> <br/>
    <input type="submit" value="提交" />
</form>
</body>
</html>
