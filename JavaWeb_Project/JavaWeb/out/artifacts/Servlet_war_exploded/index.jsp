<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/8/6
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>index</title>
  </head>
  <body>

  <a href="1.html">相对路径</a>
  <br>
  <a href="/1.html">绝对路径</a>
  <br>
  <a href="first/second/3.html">3.html</a>
  <br>
  地址相对跳转:<a href="first/second/4.html">4.html</a>
  地址绝对跳转:<a href="/first/second/4.html">4.html</a>
  转发相对跳转:<a href="dispatch03">4.html</a>
  </body>
</html>
