<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            //删除确认
            $("a.delete").click(function () {
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】?");
            });

            //跳转到指定页
            $("#search_btn").click(function () {
                let pageNo = $("#pn_input").val();
                if (pageNo != null && pageNo > 0 && pageNo <= ${requestScope.page.pageTotal}) {
                    location.href = "${pageContext.request.contextPath}/${page.url}&pageNo=" + pageNo;
                } else {
                    alert("请输入正确页码");
                }
            });
        });
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="${pageContext.request.contextPath}/static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <jsp:include page="../common/manage_menu.jsp"></jsp:include>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/manager/bookServlet?action=queryBook&id=${book.id}&pageNo=${page.pageNo}">修改</a>
                </td>
                <td><a class="delete"
                       href="${pageContext.request.contextPath}/manager/bookServlet?action=deleteBook&id=${book.id}&pageNo=${page.pageNo}">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <hr>
    <br>
    <jsp:include page="../common/page_nav.jsp"></jsp:include>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>