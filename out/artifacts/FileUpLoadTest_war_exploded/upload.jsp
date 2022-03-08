<%--
  Created by IntelliJ IDEA.
  User: 30412
  Date: 2021/9/18
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<%--这里配置的访问路径是Servlet程序中对应的web.xml中写的访问路径--%>
<form action="http://localhost:8080/FileUpLoadTest/uploadservlet" method="post" enctype="multipart/form-data">
    用户名：<input type="text" name="username"><br>
    头像：<input type="file" name = "photo"><br>
    <input type="submit" value="上传">
</form>
</body>
</html>
