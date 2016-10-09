<%@page language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${titulo}</title>
        <jsp:include page="../head.jsp"/>
    </head>
    <header>
        <jsp:include page="../header.jsp"/>
    </header>
    <body>
        <br>
        <h1 class="heading wow fadeInUp" data-wow-duration="500ms" data-wow-delay="300ms">${sucesso}</h1>
   <jsp:include page="../footer.jsp"/>
</html>
