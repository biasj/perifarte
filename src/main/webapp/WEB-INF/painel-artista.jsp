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
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        
        <!--css-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/painel.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        
        <title>Perifarte - Artistas</title>
    </head>
    <body>
         <c:import url="./../cabecalho.jsp"/>
        <div class="container content-container">
            <c:if test="${exclusaoSucesso != null}">
                <div class="alert alert-warning" role="alert">
                    <span><c:out value="${exclusaoSucesso}"/></span>
                </div>  
            </c:if>
            
            <div class="card">
                <div class='card-header'>
                    <ul class="nav nav-fill">
                        <li class="nav-item">
                          <a class="nav-link active" href="${pageContext.request.contextPath}/painel/artista">Painel</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="${pageContext.request.contextPath}/perfil">Perfil</a>
                        </li>
                    </ul>
                </div>
                    
                <div class="card-body">
                    <div class='d-flex justify-content-between'>
                       <h3>Minhas obras</h3>
                        <div><a class="login btn btn-primary" href="/perifarte/cadastroobra">Adicionar Obra</a></div> 
                    </div>
                    
                    <!--para cada obra existente publica por esse artista logado-->
                    <div class="list-group-flush">
                        <c:forEach var="obra" items="${usuario.obras}">
                                <a class="list-group-item list-group-item-action item-lista-adm" href="${pageContext.request.contextPath}/editar/obra?id=${obra.id}">
                                    <div class="media">
                                        <img class="mr-3 mini-obra-artista" src="${pageContext.request.contextPath}/imagem-obra?id=${obra.id}" >
                                    
                                        <div class="media-body">
                                            <h5 class="mt-0"><c:out value="${obra.titulo}"/></h5>
                                            <div class="d-flex justify-content-between">
                                                <p class="mb-1">Preço: R$ <c:out value="${obra.preco}0"/></p>
                                                <i class="far fa-edit" style="font-size: 30px;"></i>
                                            </div>
                                            <p class="mb-1">Organizacao: <c:out value="${obra.organizacao.nome}"/></p>
                                        </div>
                                    </div>
                                </a>
                         </c:forEach>
                    </div> 

                </div>
            </div>   
        </div>
        
    </body>
</html>
