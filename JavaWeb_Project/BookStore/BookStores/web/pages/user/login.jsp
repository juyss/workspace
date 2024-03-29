<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员登录页面</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="${pageContext.request.contextPath}/static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>尚硅谷会员</h1>
								<a href="${pageContext.request.contextPath}/pages/user/signin.jsp">立即注册</a>
							</div>
							<div class="msg_cont">

								<%--如果errorMsg为空,则显示以下默认信息;不为空,则显示传入的信息--%>
									<%--JSTL实现--%>
<%--									<c:choose >--%>
<%--										<c:when test="${empty errorMsg}">--%>
<%--											${errorMsg = "请输入用户名和密码"}--%>
<%--										</c:when>--%>
<%--										<c:otherwise>--%>
<%--											${errorMsg}--%>
<%--										</c:otherwise>--%>
<%--									</c:choose>--%>

									<%--EL实现--%>
									${empty errorMsg?"请输入用户名和密码":errorMsg}

							</div>
							<div class="form">
								<form action="${pageContext.request.contextPath}/user.do" method="post">
									<input type="hidden" name="action" value="login"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>
</body>
</html>
