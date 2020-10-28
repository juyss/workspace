
<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/10/14
  Time: 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="col-sm-3 col-md-2 sidebar">
    <div class="tree">
        <ul style="padding-left:0px;" class="list-group">
            <c:forEach items="${sessionScope.menu_list}" var="pmeun">

            <li class="list-group-item tree-closed">

                <c:choose>
                    <c:when test="${not empty pmeun.url}">
                        <span><i class="${pmeun.icon}"></i> <a href="${pageContext.request.contextPath}/${pmeun.url}">${pmeun.name}</a> <span class="badge" style="float:right">${fn:length(pmeun.children)} </span></span>
                    </c:when>
                    <c:otherwise>
                        <span><i class="${pmeun.icon}"></i> ${pmeun.name} <span class="badge" style="float:right"> ${fn:length(pmeun.children)} </span></span>
                    </c:otherwise>
                </c:choose>

                <ul style="margin-top:10px;display:none;">

                    <c:forEach items="${pmeun.children}" var="cmenu">

                    <li style="height:30px;">
                        <a href="${pageContext.request.contextPath}/${cmenu.url}"><i class="${cmenu.icon}"></i> ${cmenu.name} </a>
                    </li>
                    </c:forEach>
                </ul>
            </li>

            </c:forEach>
        </ul>
    </div>
</div>
