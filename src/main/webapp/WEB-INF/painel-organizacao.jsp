<%-- 
    Document   : painel-organizacao
    Created on : 22/10/2020, 11:02:36
    Author     : beatrizsato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
        <title>Painel organização</title>
    </head>
    <body>
        <h2> <c:out value="${organizacao.nome}" /></h2>
        
        <h3>Obras que foram doadas para sua organização</h3>
        <ul>
            <li>Monalisa</li>
            <li>Abaporu</li>
        </ul>
    </body>
</html>
