<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/10/15
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <%@include file="../common/css.jsp"%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor:pointer;
        }
    </style>
</head>

<body>

<%@include file="../common/admin_header.jsp" %>

<div class="container-fluid">
    <div class="row">

        <%@include file="../common/admin_sidebar.jsp"%>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/toMain">首页</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/index/${empty pageNum ? '0':pageNum}">数据列表</a></li>
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <c:if test="${not empty errormsg}">
                    <div class="alert alert-info" role="alert">${errormsg}</div>
                </c:if>
                <div class="panel-body">
                    <form role="form" action="${pageContext.request.contextPath}/admin/add" method="POST">
                            <input type="hidden" class="form-control" name="_method" value="${empty admin?'POST':'PUT'}">
                            <input type="hidden" class="form-control" name="id" value="${empty admin? null:admin.id}">
                            <input type="hidden" class="form-control" name="page_num" value="${empty pageNum? null:pageNum}">
                        <div class="form-group">
                            <label for="loginacct">登陆账号</label>
                            <input type="text" class="form-control" id="loginacct" name="loginacct" value="${empty admin?"请输入登陆账号":admin.loginacct}">
                        </div>
                        <div class="form-group">
                            <label for="username">用户名称</label>
                            <input type="text" class="form-control" id="username" name="username" value="${empty admin?"请输入登陆用户名":admin.username}">
                        </div>
                        <div class="form-group">
                            <label for="userpswd">密码</label>
                            <input type="password" class="form-control" id="userpswd" name="userpswd">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input type="email" class="form-control" id="email" name="email" value="${empty admin?"请输入邮箱地址":admin.email}">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok"></i> 确认添加</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<%@include file="/WEB-INF/view/common/script.jsp" %>
<script src="${pageContext.request.contextPath}/static/script/docs.min.js"></script>
<script type="text/javascript">
    $(function () {

    });
</script>
</body>
</html>
