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
    <link href="static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script type="text/javascript" src="static/js/jquery-1.12.4.min.js"></script>
</head>
<body>
<div class="container">
    <!-- 标题 -->
    <div class="row">
        <div class="col-md-12">
            <h3>添加部门</h3>
        </div>
    </div>
    <hr>
    <form class="form-inline" action="addDept" method="post">
        <div class="form-group">
            <label for="dept_id">部门ID</label>
            <input type="text" class="form-control" id="dept_id" name="deptId">
        </div>
        <div class="form-group">
            <label for="dept_name">部门名称</label>
            <input type="text" class="form-control" id="dept_name" name="deptName">
        </div>
        <button type="submit" class="btn btn-primary">确认添加</button>
    </form>
</div>


</body>
</html>
