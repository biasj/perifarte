<%-- 
    Document   : painel-administrador
    Created on : 25/10/2020, 19:59:29
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
        <title>Painel administrador</title>
    </head>
    <body>
        <h2> <c:out value="${administrador.nome}" /></h2>
        
        <h3>ORGANIZAÇÕES</h3>
        <ul>
            <li>AjudaMuito</li>
            <li>Crança Esperança</li>
        </ul>
    </body>
</html>
