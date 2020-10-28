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
            <tr >
                <td align="center">${book.id}</td>
                <td align="center">${book.name}</td>
                <td align="center">${book.count}</td>
                <td align="center">${book.detail}</td>
            </tr>
        </c:forEach>

    </table>

</body>
</html>
