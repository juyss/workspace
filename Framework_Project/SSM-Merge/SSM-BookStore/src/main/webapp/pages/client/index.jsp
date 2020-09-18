<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" >
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function () {

			//跳转指定页码
			$("#search_btn").click(function () {
				let pageNo = $("#pn_input").val();
				if (pageNo != null && pageNo > 0 && pageNo <= ${requestScope.page.pageTotal}) {
					location.href = "${pageContext.request.contextPath}/${page.url}&pageNo=" + pageNo;
				} else {
					alert("请输入正确页码");
				}
			});

			// 添加购物车
			$("button.add_to_cart").click(function () {
				let bookId = $(this).attr("bookId");
				location.href = "${pageContext.request.contextPath}/cartServlet?action=addItem&id="+bookId;
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="${pageContext.request.contextPath}/static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<div>
				<c:if test="${empty sessionScope.user_session}">
					<a href="${pageContext.request.contextPath}/pages/user/login.jsp">登录</a> |
					<a href="${pageContext.request.contextPath}/pages/user/signin.jsp">注册</a> &nbsp;&nbsp;
				</c:if>
				<c:if test="${not empty sessionScope.user_session}">
					<span>欢迎<span class="um_span">${sessionScope.user_session.username}</span>光临尚硅谷书城</span>
					<a href="../order/order.jsp">我的订单</a>
					<a href="${pageContext.request.contextPath}/user.do?action=logout">注销</a>&nbsp;&nbsp;
				</c:if>
				<a href="${pageContext.request.contextPath}/pages/cart/cart.jsp">购物车</a>
				<a href="${pageContext.request.contextPath}/pages/manager/manager.jsp">后台管理</a>
			</div>
	</div>
	<div id="main" <%--style="background-color: #39987c"--%>>
		<div id="book">
			<div class="book_cond">
				<form action="${pageContext.request.contextPath}/client/bookServlet?action=pageBooksByPrice" method="post">
					价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
						<input id="max" type="text" name="max" value="${param.max}"> 元
						<input type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
				<div>
					您刚刚将<span style="color: red">${sessionScope.cart.lastAddedCartItem.name}</span>加入到了购物车中
				</div>
			</div>

			<c:forEach items="${requestScope.page.items}" var="book">

				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${pageContext.request.contextPath}/static/img/default.jpg" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.name}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button bookId="${book.id}" class="add_to_cart">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
<jsp:include page="../common/page_nav.jsp"></jsp:include>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>