<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-2-10
  Time: 8:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix='fmt' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>查看</title>
    <c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
</head>
<body>

登录名：<input name="n0" type="text" value="${userModel.loginname}" /> <br/>
姓名：<input name="n1" type="text" value="${userModel.truename}"/> <br/>
性别：
<select name="n2">
    <option value="0" <c:if test="${userModel.sex==0}">selected="selected"</c:if> >男</option>
    <option value="1" <c:if test="${userModel.sex==1}">selected="selected"</c:if>>女</option>
</select><br/>
部门：
<input type="checkbox" name="n3" value="部门1" <c:if test="${fn:contains(userModel.deptname,'部门1')}">checked="checked"</c:if>   />部门1
<input type="checkbox" name="n3"  value="部门2" <c:if test="${fn:contains(userModel.deptname,'部门2')}">checked="checked"</c:if>  />部门2
<input type="checkbox" name="n3" value="部门3" <c:if test="${fn:contains(userModel.deptname,'部门3')}">checked="checked"</c:if>   />部门3
<input type="checkbox" name="n3" value="部门4" <c:if test="${fn:contains(userModel.deptname,'部门4')}">checked="checked"</c:if>   />部门4
<br/>
职务：
<input type="radio" name="n4" value="职务1" <c:if test="${userModel.dutyname=='职务1'}">checked="checked"</c:if>   />职务1
<input type="radio" name="n4"  value="职务2" <c:if test="${userModel.dutyname=='职务2'}">checked="checked"</c:if> />职务2
<br/>
时间：
<input name="n5" type="text"value="<fmt:formatDate value="${userModel.updatetime}" pattern="yyyy-MM-dd HH:mm"/>"  /> <br/>
<a href="${path}/UserListServlet?method=findall" target="_self">返回</a>
</body>
</html>
