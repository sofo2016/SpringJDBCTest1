<%--
  Created by IntelliJ IDEA.
  User: wanggenshen_sx
  Date: 2016/12/23
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show Page</title>
    <script src="../../js/jquery-3.3.1.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>LastName</th>
        <th>Email</th>
    </tr>

    <c:forEach items="${employees}" var="emp">
        <tr>
            <th>${emp.id}</th>
            <th>${emp.lastName}</th>
            <th>${emp.email}</th>
        </tr>
    </c:forEach>
</table>
</body>
</html>