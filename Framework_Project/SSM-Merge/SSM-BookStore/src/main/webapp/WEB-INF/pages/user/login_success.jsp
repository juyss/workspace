<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="../../static/img/logo.gif" >
				<div>
					<span>欢迎<span class="um_span">${sessionScope.user_session.username}</span>光临尚硅谷书城</span>
					<a href="../order/order.jsp">我的订单</a>
					<a href="${pageContext.request.contextPath}/user.do?action=logout">注销</a>&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/index.jsp">返回</a>
				</div>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="${pageContext.request.contextPath}/index.jsp">转到主页</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>