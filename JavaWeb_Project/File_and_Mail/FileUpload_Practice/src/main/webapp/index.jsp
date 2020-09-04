<html>
<body>
    <h2>Hello World!</h2>
    <form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
        <input type="file" name="file1"><br>
        <input type="file" name="file2"><br>
        <input type="submit" value="上传"> <input type="reset" value="重置">
    </form>
</body>
</html>
