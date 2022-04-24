<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {

            //删除确认事件
            $("td.deleteItem").click(function () {
                return confirm("你确定要删除["+$(this).parent().parent().find("td:first").text()+"]吗?");
            });

            //清空购物者确认事件
            $("#clearItem").click(function () {
                return confirm("确定要清空购物车么?");
            });

            $(".item_count").change(function () {
                let name = $(this).parent().parent().find("td:first").text();
                let id = $(this).attr("bookId");
                let count = $(this).val();

                if (confirm("确定修改["+name+"]数量为"+count+"么?")){
                    location.href = "${pageContext.request.contextPath}/cartServlet?action=updateItem&count="+count+"&id="+id;
                }else{
                    this.value = this.defaultValue;
                }
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="${pageContext.request.contextPath}/static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <div>
        <span>欢迎<span class="um_span">${empty sessionScope.user_session ? "游客": sessionScope.user_session.username}</span>光临尚硅谷书城</span>
        <a href="${pageContext.request.contextPath}/pages/order/order.jsp">我的订单</a>
        <a href="${pageContext.request.contextPath}/index.jsp">返回</a>
    </div>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td>
                        <input class="item_count" bookId="${entry.value.id}" type="text" style="width: 20px" value="${entry.value.count}">
                    </td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td class="deleteItem"><a href="${pageContext.request.contextPath}/cartServlet?action=deleteItem&id=${entry.key}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5" style="color: red">亲,购物车暂时还是空的哦</td>
            </tr>
        </c:if>
    </table>

    <div class="cart_info">
        <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount == null ? 0 : sessionScope.cart.totalCount}</span>件商品</span>
        <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice == null ? 0 : sessionScope.cart.totalPrice}</span>元</span>
        <span class="cart_span"><a id="clearItem" href="${pageContext.request.contextPath}/cartServlet?action=clearItem">清空购物车</a></span>
        <span class="cart_span"><a href="${pageContext.request.contextPath}/pages/cart/checkout.jsp">去结账</a></span>
    </div>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>