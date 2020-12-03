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
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        
        <!--css-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/painel.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        
        <title>Perifarte - Organizações</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <div class="container">
            
            <div class='content-container'>
                <c:if test="${atualizacaoSucesso != null}">
                    <div class="form-group w-75 mx-auto alert alert-success" role="alert">
                        <span><c:out value="${atualizacaoSucesso}" /></span>
                    </div>  
                </c:if>

                <c:if test="${exclusaoSucesso != null}">
                    <div class="form-group w-75 mx-auto alert alert-warning" role="alert">
                        <span><c:out value="${exclusaoSucesso}" /></span>
                    </div>  
                </c:if>

            <div class="card">
                <div class="card-header">
                    <ul class="nav nav-fill">
                        <li class="nav-item">
                          <a class="nav-link active" href="${pageContext.request.contextPath}/painel/adm">Painel</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="${pageContext.request.contextPath}/perfil">Perfil</a>
                        </li>
                    </ul>
                </div>
             
                <div class="card-body">
                    <div class="list-group-flush painel">
                        <h4>Organizações</h4>
                        <!--para cada organizacao existente no banco de dados--> 
                        <c:forEach var="org" items="${administrador.organizacoes}">
                            <c:if test="${org.status != 'excluido'}">
                                <!--mostrar as seguintes informacoes com possibilidade de editar-->
                                <a class="list-group-item list-group-item-action item-lista-adm" href="/perifarte/editar/org?id=${org.id}">
                                    <h5 class="mb-1"><c:out value="${org.nome}"/></h5>
                                    <div class="d-flex justify-content-between">
                                        <p class="mb-1">Status: <c:out value="${org.status}"/></p>
                                        <i class="far fa-edit" style="font-size: 30px;"></i>
                                    </div>
                                        <p class="mb-1">Valor arrecadado: R$ <c:out value="${org.totalRecebido}0" /></p>
                                </a>
                            </c:if>
                         </c:forEach>
                    </div>

                    <!--lista de administradores cadastrados-->
                    <div class="list-group-flush painel">
                        <h4>Administradores</h4>
                        <!--para cada organizacao existente no banco de dados--> 
                        <c:forEach var="admin" items="${todosAdms}">
                            <!--mostrar as seguintes informacoes com possibilidade de editar-->
                            <a class="list-group-item list-group-item-action item-lista-adm" href="/perifarte/editar/adm?id=${admin.id}">

                                <h5 class="mb-1"><c:out value="${admin.nome}"/></h5>
                                <div class="d-flex justify-content-between">    
                                    <p class="mb-1">E-mail: <c:out value="${admin.email}"/></p>
                                    <i class="far fa-edit" style="font-size: 30px;"></i>
                                </div>
                                <p>Id: <c:out value="${admin.id}"/></p>
                                <p>Status: <c:out value="${admin.status}"/></p>
                            </a>
                         </c:forEach>
                    </div>

                    <!--lista de doadores cadastrados-->
                    <div class="list-group-flush painel">
                        <h4>Doadores</h4>
                        <!--para cada organizacao existente no banco de dados--> 
                        <c:forEach var="doador" items="${todosDoadores}">
                            <!--mostrar as seguintes informacoes com possibilidade de editar-->
                            <a class="list-group-item list-group-item-action item-lista-adm" href="#">
                                <h5 class="mb-1"><c:out value="${doador.nome}"/></h5>
                                <div class="d-flex justify-content-between">
                                    <p class="mb-1">E-mail: <c:out value="${doador.email}"/></p>
                                </div>
                                <p class="mb-1">Valor doado: R$ <c:out value="${doador.totalDoado}" /></p>
                            </a>

                         </c:forEach>
                    </div>

                    <!--lista de artistas cadastrados-->
                    <div class="list-group-flush painel">
                        <h4>Artistas</h4>
                        <!--para cada organizacao existente no banco de dados--> 
                        <c:forEach var="artista" items="${todosArtistas}">
                            <!--mostrar as seguintes informacoes com possibilidade de editar-->
                            <a class="list-group-item list-group-item-action item-lista-adm" href="#">
                                <h5 class="mb-1"><c:out value="${artista.nome}"/></h5>
                                <div class="d-flex justify-content-between">
                                    <p class="mb-1">Portfolio: <c:out value="${artista.portifolio}"/></p>
                                </div>
                            </a>
                         </c:forEach>
                    </div>
                </div>

            </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" crossorigin="anonymous"></script>
            
    </body>
</html>
