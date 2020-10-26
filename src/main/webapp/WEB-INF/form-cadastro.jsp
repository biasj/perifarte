<%-- 
    Document   : form-cadastro
    Created on : 21/10/2020, 13:47:40
    Author     : beatrizsato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <title>Cadastro</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        
        <div class="container ">
            
            <form method="post" action="processar-cadastro">
                <h2 class='form-group'>Cadastro</h2>
                <div class="form-group w-75 p3 mx-auto">
                    <label for="nome">Nome</label>
                    <input class="form-control" type="text" name="nome" id="nome" required value="${nome}">
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${nomeErro != null}">
                        <span class="erro"><c:out value="${nomeErro}" /></span>
                    </c:if>

                </div>

                <div class="form-group w-75 p3 mx-auto">
                    <label>E-mail</label>
                    <input class="form-control" type="email" name="email" required value="${email}">
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${emailErro != null}">
                        <span class="erro"><c:out value="${emailErro}" /></span>
                    </c:if>
                </div>

                <div class="form-group w-75 p3 mx-auto">
                    <label for="senha">Senha</label>
                    <!--TODO: redefinir senha-->
                    <input id="senha" class="form-control" type="password" name="senha" required>
                </div>

                <div class="form-check w-75 p3 mx-auto">    
                    <input class="form-check-input" type="checkbox" value="artista" id="artista" name="artista" ${artista.contains('artista') ? 'checked' : ''}>
                    <label class="form-check-label" for="artista">Sou um artista</label>
                </div>  
                
                <!--só vai aparecer caso seja artista-->
                <div  style='display: none' class="form-group mx-auto w-75 p3">
                    <label for="portfolio">Portfólio (Instagram/Behance)</label>
                    <input class="form-control" id="portfolio" type="text" name="portfolio" placeholder="Ex: https://www.behance.net/tonariau">
                    
                </div>

                <div class='form-group w-75 p3 mx-auto'>
                    <button class="btn btn-primary" type="submit">Enviar dados</button>
                </div>
            </form>
        </div>
    </body>
</html>
