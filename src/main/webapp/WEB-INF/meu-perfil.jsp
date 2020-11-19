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
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Meu perfil</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        
        <div class="container">
            <c:choose>
                <c:when test="${organizacao != null}">
                    <a class="back-button" href="${pageContext.request.contextPath}/painel/org"><i class="fas fa-chevron-left" ></i> Obras doadas</a>
                </c:when>
                <c:when test="${artista != null}">
                    <a class="back-button" href="${pageContext.request.contextPath}/painel/artista"><i class="fas fa-chevron-left" ></i> Minhas obras</a>
                </c:when>
                <c:when test="${adm != null}">
                    <a class="back-button" href="${pageContext.request.contextPath}/painel/adm"><i class="fas fa-chevron-left" ></i> Meu painel</a>
                </c:when>
                <c:otherwise>
                    <a class="back-button" href="${pageContext.request.contextPath}/painel/doador"><i class="fas fa-chevron-left" ></i> Meus pedidos</a>
                </c:otherwise>
            </c:choose>
            
            <c:if test="${atualizacaoSucesso != null}">
                <div class="form-group w-75 mx-auto">
                    <span class="sucesso"><c:out value="${atualizacaoSucesso}" /></span>
                </div>  
            </c:if>
            
            <form method="post" action="${pageContext.request.contextPath}/login">
                
                <h2 class='form-group w-75 p3 mx-auto'>Meu perfil</h2>
                
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
                    
                <c:if test="${artista != null}">
                    <div class="form-group w-75 p3 mx-auto">
                        <label for="portfolio">Portfólio</label>
                        <input id="portifolio" class="form-control" type="portifolio" name="portifolio" required value="${artista.portifolio}">
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${portifolioErro != null}">
                            <span class="erro"><c:out value="${portifolioErro}" /></span>
                        </c:if>
                    </div>
                </c:if>
                    
                <c:if test="${org != null}">
                    <div class='form-group w-75 p3 mx-auto'>
                        <label for='cnpj'>CNPJ</label>
                        <input id='cnpj' class='form-control' type="text" name="cnpj" value="${org.cnpj}">
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${cnpjErro != null}">
                            <span class="erro"><c:out value="${cnpjErro}" /></span>
                        </c:if>
                    </div>
                    
                    <div class="form-group w-75 p3 mx-auto">
                        <label for="descricao">Descrição da organização:</label>

                        <input class="form-control" id="descricao" name="descricao" required value="${org.descricao}" rows="3"></input>    
                        <small>No máximo 100 caracteres.</small>
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${descricaoErro != null}">
                            <span class="erro"><c:out value="${descricaoErro}" /></span>
                        </c:if>
                    </div>
                    
                    <div class="form-group w-75 p3 mx-auto">
                        <label for='justificativa'>O que será realizado com as doações?</label>
                        <input class="form-control" id="justificativa" name="justificativa" required value="${org.justificativa}" rows="3"></input>  
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
    </body>
</html>