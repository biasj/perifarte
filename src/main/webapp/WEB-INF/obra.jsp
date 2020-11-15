<%-- 
    Document   : obra
    Created on : 14/11/2020, 23:41:20
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>        
        <title>Perifarte - Obra</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/> 
        <div class="container">
            <a class="back-button" href="${pageContext.request.contextPath}/home"><i class="fas fa-chevron-left" ></i> Voltar</a>
            <h1><c:out value="${obra.titulo}"/></h1>
            <p>pintado por <c:out value="${artista.nome}"/></p>
            <p>doado para <c:out value="${org.nome}"/></p>
        </div>
    </body>
</html>
