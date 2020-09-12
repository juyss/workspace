<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/9/12
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>测试页面</title>
    </head>

    <body>
        <h4>
            数据显示:${message}
        </h4>
        <hr>
        <h5>@PostMapping 和 @GetMapping </h5>
        <p>PATH:/mapping/test   method:<p style="color: red">POST请求</p></p>
        <form action="${pageContext.request.contextPath}/mapping/test" method="POST">
            <input type="text" name="id"><br>
            <input type="text" name="name"><br>
            <input type="text" name="pwd"><br>
            <input type="submit" value="提交">
        </form>
        <br>
        <p>PATH:/mapping/test   method:<p style="color: red">GET请求</p></p>
        <form action="${pageContext.request.contextPath}/mapping/test" method="GET">
            <input type="text" name="id"><br>
            <input type="text" name="name"><br>
            <input type="text" name="pwd"><br>
            <input type="submit" value="提交">
        </form>
    </body>
</html>
