<%-- 
    Document   : obras
    Created on : 14/11/2020, 22:50:22
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
        <title>Perifarte</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp" /> 
        <div class="container">
            <c:forEach var="obra" items="${obras}">
                <a class="list-group-item list-group-item-action" href="${pageContext.request.contextPath}/obra?id=${obra.id}">
                    <h5 class="mb-1"><c:out value="${obra.titulo}"/></h5>
                    <div class="d-flex justify-content-between">
                        <p class="mb-1">Preço: <c:out value="${obra.preco}"/></p>
                        <i class="far fa-edit" style="font-size: 30px;"></i>
                    </div>
                    <p class="mb-1">Organizacao: <c:out value="${obra.organizacao.nome}"/></p>
                </a>
            </c:forEach>
        </div>
    </body>
    
</html>
