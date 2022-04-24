<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/8/9
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OutputTest</title>
</head>
<body>
1
    <%
        out.write("out输出");

        response.getWriter().write("response输出");
    %>

</body>
</html>
