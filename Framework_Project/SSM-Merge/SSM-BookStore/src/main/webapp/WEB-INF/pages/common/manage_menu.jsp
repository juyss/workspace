<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/8/17
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div style="color: red" >${errorMsg}</div>
    <a href="${pageContext.request.contextPath}/pages/manager/book_edit.jsp?pageNo=${page.pageNo}">添加图书</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/manager/bookServlet?action=pageBooks">图书管理</a>
    <a href="order_manager.jsp">订单管理</a>
    <a href="${pageContext.request.contextPath}/client/bookServlet?action=pageBooks">返回商城</a>
</div>
