<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/9/18
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/blog?pageNum=1&pageSize=3">查询</a>
<br>
<form action="blog" method="get">
    页码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="pageNum" type="text"><br>
    页面大小&nbsp;<input name="pageSize" type="text"><br>
    <input type="submit" value="提交"><br>
</form>
</body>
</html>
