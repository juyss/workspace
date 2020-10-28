<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$ (function () {
			//正则表达式验证
			$("#sub_btn").click(function () {

				/**
				 * 验证用户名,由字母数字下划线组成
				 */
				// 1.获取用户名输入框内容
				var userNameInput = $("#username").val();
				// 2.创建正则表达式
				var userNameSyntax = /^\w{5,12}$/;
				// 3.判断是否符合要求
				if (!userNameSyntax.test(userNameInput)){
					$("span.errorMsg").text("用户名不合法!");
					return false;
				}

				//验证密码是否确认
				let password = $("#password").val();
				let repwd = $("#repwd").val();
				if (password!=repwd){
					$("span.errorMsg").text("确认密码不一致!");
					return false;
				}

				//邮箱格式
				var email = $("#email").val();
				var emailSyntax = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if (!emailSyntax.test(email)){
					$("span.errorMsg").text("邮箱格式不正确!");
					return false;
				}

				//提交完成后删除错误信息
				$("span.errorMsg").text("");
			});

			$("#code_img").click(function () {
				this.src = "${pageContext.request.contextPath}/kaptcha.do?temp="+new Date();
			});
		});


	</script>
	<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}

</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="${pageContext.request.contextPath}/static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${errorMsg}</span>
							</div>
							<div class="form">
								<form action="${pageContext.request.contextPath}/user/signin" method="post">
									<input type="hidden" name="action" value="signin"/>
									<label>用户名称:</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码:</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码:</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件:</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码:</label>
									<input class="itxt" type="text" style="width: 150px;" name="kaptcha" id="kaptcha"/>
									<img id="code_img" alt="" src="${pageContext.request.contextPath}/kaptcha.do" style="float: right; margin-right: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />

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