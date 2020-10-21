<%-- 
    Document   : painel-usuario
    Created on : 21/10/2020, 14:36:42
    Author     : beatrizsato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Logado</h1>
        <h3>Nome: <c:out value="${usuario.nome}"/> </h3>
        <p>E-mail: <c:out value="${usuario.email}"/></p>
    </body>
</html>
