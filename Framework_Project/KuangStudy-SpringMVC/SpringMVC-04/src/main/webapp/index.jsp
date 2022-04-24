<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/9/12
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
        <a href="${pageContext.request.contextPath}/show">测试:show</a>
        <br>
        <a href="${pageContext.request.contextPath}/restful/1231/UserName">restful风格--->GET请求:ip:port/project/test/1231/UserName</a>
        <hr>
        <h4>数据处理</h4>
        <a href="${pageContext.request.contextPath}/data/same?name=NAME">提交的域名称和处理方法的参数名一致</a>
        <br>
        <a href="${pageContext.request.contextPath}/data/different?username=USERNAME">提交的域名称和处理方法的参数名不一致</a>
        <br>
        <p>数据自动封装为对象</p>
        <p>PATH: /mapping/test  method:<p style="color: red">GET请求</p></p>
        <form action="${pageContext.request.contextPath}/data/object" method="GET">
            <input type="text" name="id"><br>
            <input type="text" name="name"><br>
            <input type="text" name="pwd"><br>
            <input type="submit" value="提交">
        </form>
        <br>
        <p>数据自动封装为对象</p>
        <p>PATH: /mapping/test  method:<p style="color: red">POST请求</p></p>
        <form action="${pageContext.request.contextPath}/data/object" method="POST">
            <input type="text" name="id"><br>
            <input type="text" name="name"><br>
            <input type="text" name="pwd"><br>
            <input type="submit" value="提交">
        </form>
        <hr>
</body>
</html>
