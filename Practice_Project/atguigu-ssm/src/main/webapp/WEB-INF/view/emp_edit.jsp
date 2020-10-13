<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/10/10
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工信息编辑</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <h3>员工信息编辑</h3>
            <div style="color: red">${msg}</div>
        </div>
    </div>
    <hr>
    <form class="form-inline" action="${pageContext.request.contextPath}/addEmp" method="POST">

        <%--    如果emp为空则说明为插入操作,不为空为修改才做    --%>
        <c:if test="${not empty emp_info}">
            <input hidden name="_method" value="PUT">
            <input hidden name="empId" value="${emp_info.empId}">
            <input hidden name="page_num" value="${page_num}">
        </c:if>

        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" placeholder="姓名" name="empName" value="${empty emp_info ? '':emp_info.empName}" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
            <p></p>
        <div class="form-group has-success has-feedback">
            <input type="email" class="form-control" placeholder="邮箱" name="empEmail" value="${empty emp_info ? '':emp_info.empEmail}" style="margin-top:10px;">
            <span class="glyphicon glyphicon glyphicon-envelope form-control-feedback"></span>
        </div>
            <p></p>
        <div class="form-group has-success has-feedback">
            <select class="form-control" name="empGender">
                <option disabled selected>请选择性别</option>
                <option>男</option>
                <option>女</option>
            </select>
        </div>
            <p></p>
        <div class="form-group has-success has-feedback">
            <select class="form-control" name="dId">
                <option disabled selected>请选择部门</option>
                <c:forEach items="${dept_list}" var="dept">
                    <option value="${dept.deptId}">${dept.deptName}</option>
                </c:forEach>
            </select>
        </div>
        <p></p>
        <button type="submit" class="btn btn-primary">提交</button>
        <a href="${pageContext.request.contextPath}/emps?page_num=${page_num}" class="btn btn-primary">返回主页</a>
    </form>
</div>

</body>
</html>
