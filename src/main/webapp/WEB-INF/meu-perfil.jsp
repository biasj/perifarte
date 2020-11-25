<%-- 
    Document   : meu-perfil
    Created on : 19/11/2020, 10:53:44
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/painel.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Meu perfil</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        
        <div class="container content-container">
            <c:if test="${atualizaPerfil != null}">
                <div class="alert alert-success" role="alert">
                    <span><c:out value="${atualizaPerfil}" /></span>
                </div>  
            </c:if>
            
            <div class="card">
                <!--<h2 class="titulo-pagina"><c:out value="${usuario.nome}" /></h2>-->
                
                <div class="card-header">
                    <ul class="nav nav-fill">
                        <li class="nav-item">
                            <c:choose>
                                <c:when test="${usuarioAdministrador != null}">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/painel/adm">Painel</a>
                                </c:when>

                                <c:when test="${usuarioArtista != null}">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/painel/artista">Painel</a>
                                </c:when>

                                <c:when test="${usuarioDoador != null}">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/painel/doador">Painel</a>
                                </c:when>

                                <c:when test="${usuarioOrganizacao != null}">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/painel/org">Painel</a>
                                </c:when>
                            </c:choose>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link active" href="${pageContext.request.contextPath}/perfil">Perfil</a>
                        </li>
                    </ul>
                </div>
            
            
            <div class="card-body">
                <form method="post" action="${pageContext.request.contextPath}/perfil">

                    <h2 class='form-group w-75 p3 mx-auto titulo-pagina'>Meu perfil</h2>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="nome">Nome</label>
                        <input class="form-control" type="text" name="nome" id="nome" required value="${usuario.nome}">
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${nomeErro != null}">
                            <span class="erro"><c:out value="${nomeErro}" /></span>
                        </c:if>
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <label>E-mail</label>
                        <input class="form-control" type="email" name="email" required value="${usuario.email}">
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${emailErro != null}">
                            <span class="erro"><c:out value="${emailErro}" /></span>
                        </c:if>
                    </div>

                    <c:if test="${usuarioArtista != null}">
                        <div class="form-group w-75 p3 mx-auto">
                            <label for="portfolio">Portfólio</label>
                            <input id="portifolio" class="form-control" type="text" name="portfolio" required value="${usuarioArtista.portifolio}">
                            <!-- caso o servlet aponte um erro -->
                            <c:if test="${portifolioErro != null}">
                                <span class="erro"><c:out value="${portifolioErro}" /></span>
                            </c:if>
                        </div>
                    </c:if>

                    <c:if test="${usuarioOrganizacao != null}">
                        <div class='form-group w-75 p3 mx-auto'>
                            <label for='cnpj'>CNPJ</label>
                            <input id='cnpj' class='form-control' type="text" name="cnpj" placeholder="${usuarioOrganizacao.cnpj}" readonly>
                            <!-- caso o servlet aponte um erro -->
                            <c:if test="${cnpjErro != null}">
                                <span class="erro"><c:out value="${cnpjErro}" /></span>
                            </c:if>
                        </div>

                        <div class="form-group w-75 p3 mx-auto">
                            <label for="descricao">Descrição da organização:</label>
                            <textarea class="form-control" id="descricao" name="descricaoOrganizacao" required  rows="3"><c:out value="${usuarioOrganizacao.descricao}" /></textarea>    
                            <small>No máximo 100 caracteres.</small>
                            <!-- caso o servlet aponte um erro -->
                            <c:if test="${descricaoErro != null}">
                                <span class="erro"><c:out value="${descricaoErro}" /></span>
                            </c:if>
                        </div>

                        <div class="form-group w-75 p3 mx-auto">
                            <label for='justificativa'>O que será realizado com as doações?</label>
                            <textarea class="form-control" id="justificativa" name="justificativaOrganizacao" required rows="3"><c:out value="${usuarioOrganizacao.justificativa}" /></textarea>     
                            <small>No máximo 100 caracteres.</small>
                            <!-- caso o servlet aponte um erro -->
                            <c:if test="${justificativaErro != null}">
                                <span class="erro"><c:out value="${justificativaErro}" /></span>
                            </c:if>
                        </div>
                    </c:if>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="senha">Senha</label>
                        <input id="senha" class="form-control" type="password" name="senha" required value='${usuario.senha}'>
                    </div>

                    <div class="d-flex justify-content-center">
                        <button class="w-50 btn btn-primary" type="submit">Atualizar dados</button>
                    </div>

                </form>
                    
                    
                </div>
            </div>
                    
        </div>
    </body>
</html>
