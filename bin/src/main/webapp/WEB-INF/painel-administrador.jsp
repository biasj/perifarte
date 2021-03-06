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
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Perifarte - Organizações</title>
    </head>
    <body>
        <c:import url="./../cabecalho-painel.jsp"/>
        <div class="container">
            <h2>Administrador: <c:out value="${administrador.nome}" /></h2>
        
            <h3>Organizações</h3>

            <div class="list-group">
                <c:forEach var="org" items="${administrador.organizacoes}">
                        <a class="list-group-item list-group-item-action" href="./editar/org">
                            <h5 class="mb-1"><c:out value="${org.nome}"/></h5>
                            <div class="d-flex justify-content-between">
                                <p class="mb-1">Status: <c:out value="${org.status}"/></p>
                                <i class="far fa-edit" style="font-size: 30px;"></i>
                            </div>
                            <p class="mb-1">Valor arrecadado: R$ 1000</p>
                        </a>
                 </c:forEach>
            </div> 
        </div>
    </body>
</html>
