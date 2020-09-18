<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/9/18
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BookList</title>
</head>
<body>
博客列表: <br>
<c:forEach var="blog" items="${blog_list}">
    博客:${blog}  <br>
</c:forEach>

分页信息: <br>
总条目数:${page_info.total}<br>
总页数:${page_info.pages}<br>
导航栏数值:
        <c:forEach var="num" items="${page_info.navigatepageNums}">
            [第&nbsp;&nbsp;${num}&nbsp;&nbsp;页]
        </c:forEach>
</body>
</html>
