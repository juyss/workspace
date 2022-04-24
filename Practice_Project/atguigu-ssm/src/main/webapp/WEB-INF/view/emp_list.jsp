<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shmebluk
  Date: 2020/9/27
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工列表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>
<!-- 搭建显示页面 -->
<div class="container-fluid">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>
        <div class="col-md-12 col-md-offset-1" style="color: red">${msg}</div>
    </div>
    <!-- 按钮 -->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <a class="btn btn-success" href="${pageContext.request.contextPath}/toEmpEdit?pageNum=${pageInfo.pageNum}">添加员工</a>
            <a class="btn btn-success" href="${pageContext.request.contextPath}/toDeptEdit/${pageInfo.pageNum}">编辑部门</a>
        </div>
    </div>
    <p></p>
    <!-- 显示表格数据 -->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th style="text-align: center">员工名</th>
                    <th style="text-align: center">性别</th>
                    <th style="text-align: center">电子邮箱</th>
                    <th style="text-align: center">所在部门</th>
                    <th style="text-align: center">操作</th>
                </tr>
                <c:forEach items="${pageInfo.list }" var="emp">
                    <tr>
                        <th style="text-align: center">${emp.empName }</th>
                        <th style="text-align: center">${emp.empGender}</th>
                        <th style="text-align: center">${emp.empEmail }</th>
                        <th style="text-align: center">${emp.department.deptName }</th>
                        <th style="text-align: center">
                            <a id="update_btn" class="btn btn-primary btn-sm"
                               href="${pageContext.request.contextPath}/toEmpEdit?pageNum=${pageInfo.pageNum}&empId=${emp.empId}">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </a>
                            <a id="delete_btn" class="btn btn-danger btn-sm"
                               href="${pageContext.request.contextPath}/deleteEmp/${emp.empId}/${pageInfo.pageNum}">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </a>
                        </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <script type="text/javascript">
        $(document).on("click", "#delete_btn", function () {
            return confirm("确定删除[ " + $(this).parent().parent().find("th:first").text() + " ]吗?")
        });
    </script>

    <!-- 显示分页信息 -->
    <div class="row">
        <!--分页文字信息  -->
        <div align="center" class="col-md-12">
            <a class="btn">
                当前第 ${pageInfo.pageNum} 页，共 ${pageInfo.pages} 页，共 ${pageInfo.total} 条记录
            </a>
        </div>

        <!-- 分页条信息 -->
        <div class="col-md-12">
            <nav align="center" aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/emps">首页</a></li>
                    <c:if test="${pageInfo.hasPreviousPage }">
                        <li><a href="${pageContext.request.contextPath}/emps?page_num=${pageInfo.pageNum-1}"
                               aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
                        </a></li>
                    </c:if>
                    <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
                        <c:if test="${page_Num == pageInfo.pageNum }">
                            <li class="active"><a href="#">${page_Num }</a></li>
                        </c:if>
                        <c:if test="${page_Num != pageInfo.pageNum }">
                            <li><a href="${pageContext.request.contextPath}/emps?page_num=${page_Num }">${page_Num }</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage }">
                        <li><a href="${pageContext.request.contextPath}/emps?page_num=${pageInfo.pageNum+1 }"
                               aria-label="Next"> <span aria-hidden="true">&raquo;</span>
                        </a></li>
                    </c:if>
                    <li><a href="${pageContext.request.contextPath}/emps?page_num=${pageInfo.pages}">末页</a></li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="row">

    </div>
</div>
</body>
</html>
