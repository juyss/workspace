<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/8/19
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${requestScope.page.pageNo > 1 }">
        <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
    </c:if>
    【${requestScope.page.pageNo}】
    <c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal }">
        <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${pageContext.request.contextPath}/${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录 到第<input value="${param.pageNo}"
                                                                                     name="pn" id="pn_input"/>页
    <input id="search_btn" type="button" value="确定">
</div>
