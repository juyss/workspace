<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/10/14
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="navbar-wrapper">
    <div class="container">
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/toIndex" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse" style="float:right;">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="${pageContext.request.contextPath}/toLogin">登录</a></li>
                        <li><a href="${pageContext.request.contextPath}/toSignin">注册</a></li>
                    </ul>
                </div>
            </div>
        </nav>

    </div>
</div>
