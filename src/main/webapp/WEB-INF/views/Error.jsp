<%-- 
    Document   : Error
    Created on : Oct 8, 2020, 3:36:44 PM
    Author     : Huy
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <h1>Đã xảy ra lỗi gì đó. Quý khách vui lòng thử lại</h1>
        <a class="btn btn-primary" href="<c:url value="/trangchu"/>">Trở về trang chủ</a>
    </body>
</html>
