<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/10/11
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">

    <%@include file="common/css.jsp"%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="${pageContext.request.contextPath}/toIndex" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form class="form-signin" role="form" action="${pageContext.request.contextPath}/user/login" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <p style="color: red">${errormsg}</p>
            <input type="text" class="form-control" placeholder="请输入登录账号" autofocus name="username">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" placeholder="请输入登录密码" name="password" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control">
                <option disabled selected>请选择用户类型</option>
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="${pageContext.request.contextPath}/toSignin">我要注册</a>
            </label>
        </div>
        <button type="submit" class="btn btn-lg btn-success btn-block"> 登录</button>
    </form>
</div>

<%@include file="common/script.jsp"%>

<script>
    function doLogin() {
        let type = $(":selected").val();
        if ( type == "user" ) {
            window.location.href = "main.html";
        } else {
            window.location.href = "index.html";
        }
    }
</script>
</body>
</html>
