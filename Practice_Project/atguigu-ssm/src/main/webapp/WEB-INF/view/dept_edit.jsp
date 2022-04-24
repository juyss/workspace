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
    <title>添加部门</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <h3>编辑部门</h3>
            <div style="color: red">${msg}</div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>部门ID</th>
                    <th>部门名称</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${dept_list}" var="dept">
                    <tr>
                        <th>${dept.deptId}</th>
                        <th>${dept.deptName}</th>
                        <th>
                            <a id="delete_btn" class="btn btn-danger btn-sm"
                               href="${pageContext.request.contextPath}/deleteDept/${dept.deptId}">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </a>
                        </th>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <hr>

    <form class="form-inline" action="${pageContext.request.contextPath}/addDept" method="post">
        <div class="form-group">
            <label for="dept_id">部门ID</label>
            <input type="text" class="form-control" id="dept_id" name="deptId">
        </div>
        <div class="form-group">
            <label for="dept_name">部门名称</label>
            <input type="text" class="form-control" id="dept_name" name="deptName">
        </div>
        <p></p>
        <button type="submit" class="btn btn-primary">确认添加</button>
        <a href="${pageContext.request.contextPath}/emps" class="btn btn-primary">返回主页</a>
    </form>
</div>

    <script type="text/javascript">
        $(document).on("click", "#delete_btn", function () {
            return confirm("确定删除[ " + $(this).parent().parent().find("th:eq(1)").text() + " ]吗?")
        });
    </script>

</body>
</html>
