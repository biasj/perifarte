<%-- 
    Document   : painel-administrador
    Created on : 25/10/2020, 19:59:29
    Author     : beatrizsatosim
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
        <title>Perifarte - Organizações</title>
    </head>
    <body>
        <c:import url="./../cabecalho-painel.jsp"/>
        <div class="container">
            <h2>Administrador: <c:out value="${administrador.nome}" /></h2>
        
            <div class="list-group adm">
                <h3>Organizações</h3>
                <!--para cada organizacao existente no banco de dados--> 
                <c:forEach var="org" items="${administrador.organizacoes}">
                    <c:if test="${org.status != 'excluido'}">
                        <!--mostrar as seguintes informacoes com possibilidade de editar-->
                        <a class="list-group-item list-group-item-action" href="/perifarte/editar/org?id=${org.id}">
                            <h5 class="mb-1"><c:out value="${org.nome}"/></h5>
                            <div class="d-flex justify-content-between">
                                <p class="mb-1">Status: <c:out value="${org.status}"/></p>
                                <i class="far fa-edit" style="font-size: 30px;"></i>
                            </div>
                            <p class="mb-1">Valor arrecadado: R$ 1000</p>
                        </a>
                    </c:if>
                 </c:forEach>
            </div>
            
            <!--lista de doadores cadastrados-->
            <div class="list-group adm">
                <h3>Doadores</h3>
                <!--para cada organizacao existente no banco de dados--> 
                <c:forEach var="doador" items="${doadores}">
                    <c:if test="${org.status != 'excluido'}">
                        <!--mostrar as seguintes informacoes com possibilidade de editar-->
                        <a class="list-group-item list-group-item-action" href="#">
                            <h5 class="mb-1"><c:out value="${doador.nome}"/></h5>
                            <div class="d-flex justify-content-between">
                                <p class="mb-1">E-mail: <c:out value="${doador.email}"/></p>
                                <!--<i class="far fa-edit" style="font-size: 30px;"></i>-->
                            </div>
                            <p class="mb-1">Valor arrecadado: R$ 1000</p>
                        </a>
                    </c:if>
                 </c:forEach>
            </div>
            
            <!--lista de artistas cadastrados-->
            <div class="list-group adm">
                <h3>Artistas</h3>
                <!--para cada organizacao existente no banco de dados--> 
                <c:forEach var="artista" items="${artistas}">
                    <c:if test="${org.status != 'excluido'}">
                        <!--mostrar as seguintes informacoes com possibilidade de editar-->
                        <a class="list-group-item list-group-item-action" href="#">
                            <h5 class="mb-1"><c:out value="${artista.nome}"/></h5>
                            <div class="d-flex justify-content-between">
                                <p class="mb-1">Portfolio: <c:out value="${artista.portifolio}"/></p>
                                <!--<i class="far fa-edit" style="font-size: 30px;"></i>-->
                            </div>
                        </a>
                    </c:if>
                 </c:forEach>
            </div>
            
        </div>
    </body>
</html>
