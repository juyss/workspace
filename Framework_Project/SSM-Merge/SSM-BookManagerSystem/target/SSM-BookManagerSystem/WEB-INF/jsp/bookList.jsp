<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/9/13
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书列表</title>
</head>
<body>

    <table align="center" border="1" width="300" height="300" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>书名</th>
            <th>数量</th>
            <th>描述</th>
        </tr>
        <c:forEach var="book" items="${book_list}">
            <tr>
                <td>${book.id}</td>
                <td>${book.name}</td>
                <td>${book.count}</td>
                <td>${book.detail}</td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>
