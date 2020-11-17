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
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        
        <!--css-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/obras.css">

        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>      
        <title>Perifarte</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp" /> 
        <div class="container">
            
            <div class="d-flex justify-content-between home-buttons">
                <a class='btn btn-light' href="#">Atualização COVID</a>
                <a class='btn btn-light' href="#">Relatório de Doações</a>
            </div>
            
            <div class="row row-cols-2 row-cols-md-3">
                
                <c:forEach var="detalhe" items="${obras}">
                    <div class="col mb-4">
                        <div class="card h-100 card-obra">
                            <img src="" class="card-img-top">
                            <h5 class="card-title"><c:out value="${detalhe.obra.titulo}"/></h5>
                            <p class="mb-1" id='preco-obra'>R$ <c:out value="${detalhe.obra.preco}"/></p>
                            <p class="mb-1">Artista: <c:out value="${detalhe.artista.nome}"/></p>
                            <p class="mb-1">Organização: <c:out value="${detalhe.obra.organizacao.nome}"/></p>
                            <a class="stretched-link" href="${pageContext.request.contextPath}/obra?id=${detalhe.obra.id}"></a>
                        </div>
                    </div>
                </c:forEach>
                
            </div>
            
        </div>
    </body>
    
</html>
