<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019-2-9
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix='fmt' %>
<html>
<head>
    <title>用户列表</title>
    <c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
    <script src="${path}/js/jquery-1.8.0.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function addInfo() {

        }

        function searchInfo() {

        }

        function delinfo(pkid) {

        }

    </script>
</head>
<body>
<a href="#" onclick="addInfo()">新增</a>
&nbsp;&nbsp;&nbsp;
<a href="#" onclick="searchInfo()">查询</a>
<h3>用户列表信息</h3>

<table border="1" cellpadding="0" cellspacing="0">
    <tr>
        <th>主键</th>
        <th>登录名</th>
        <th>姓名</th>
        <th>创建时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.pageBean.beanList}" var="cstm">
        <tr>
            <td>${cstm.pkid}</td>
            <td>${cstm.loginname}</td>
            <td>${cstm.truename}</td>
            <td><fmt:formatDate value="${cstm.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
                <a href="<c:url value="/UserListServlet?method=edit&id=${cstm.pkid}"/>">编辑</a>
                <a href="#" onclick="delinfo(${cstm.pkid})">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="${path}/UserListServlet?method=findall&pc=1">首页</a>
<c:if test="${pageBean.pc>1}">
<a href="${path}/UserListServlet?method=findall&pc=${pageBean.pc-1}">上一页</a>
</c:if>
<c:if test="${pageBean.pc<pageBean.tp}">
<a href="${path}/UserListServlet?method=findall&pc=${pageBean.pc+1}">下一页</a>
</c:if>
<a href="${path}/UserListServlet?method=findall&pc=${pageBean.tp}">末页</a>

第${pageBean.pc}页

</body>
</html>
