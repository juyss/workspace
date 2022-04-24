<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
        <input type="file" name="file1">
    <br>
        <input type="file" name="file2">
    <br>
        <input type="submit">
    <br>
</form>
</body>
</html>
