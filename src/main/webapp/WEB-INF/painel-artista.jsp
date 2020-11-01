<%-- 
    Document   : painel-artista
    Created on : 28/10/2020, 09:54:16
    Author     : Gabriel
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
        <title>Perifarte - Artistas</title>
    </head>
    <body>
         <c:import url="./../cabecalho-painel.jsp"/>
        <div class="container">
            <div class="d-flex justify-content-between">
               <h2>Artista: <c:out value="${artista.nome}" /></h2>
                <div><a class="login btn btn-primary" href="./cadastroobra">Adicionar Obra</a></div> 
            </div>
            
            <h3>Obras</h3>

            <div class="list-group">
                <c:forEach var="obra" items="${artista.obras}">
                        <a class="list-group-item list-group-item-action" href="./editar/art">
                            <h5 class="mb-1"><c:out value="${obra.titulo}"/></h5>
                            <div class="d-flex justify-content-between">
                                <p class="mb-1">Preço: <c:out value="${obra.preco}"/></p>
                                <i class="far fa-edit" style="font-size: 30px;"></i>
                            </div>
                            <p class="mb-1">Descrição: <c:out value="${obra.descricao}"/></p>
                        </a>
                 </c:forEach>
            </div> 
        </div>
        
    </body>
</html>
