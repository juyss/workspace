<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="${pageContext.request.contextPath}/static/img/logo.gif" >
			<span class="wel_word">后台管理系统</span>
		<div>
			<a href="${pageContext.request.contextPath}/manager/bookServlet?action=pageBooks">图书管理</a>
			<a href="order_manager.jsp">订单管理</a>
			<a href="${pageContext.request.contextPath}/client/bookServlet?action=pageBooks">返回商城</a>
		</div>
	</div>
	
	<div id="main">
		<h1>欢迎 <span style="color: red">${sessionScope.user_session.username}</span> 进入后台管理系统</h1>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>