<%-- 
    Document   : form-cadastro-artista
    Created on : 28/10/2020, 15:06:55
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
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        
        <title>Perifarte - Cadastro Artista</title>
    </head>
    <body>
         <c:import url="./../cabecalho.jsp"/>
         <div class="container ">
            
            <form method="post" action="processar-cadastro-artista">
                <h2 class='form-group w-75 p3 mx-auto titulo-pagina'>Cadastro de Artista</h2>
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
                    <input id="senha" class="form-control" type="password" name="senha" required value="${senha}">
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${senhaErro != null}">
                        <span class="erro"><c:out value="${emailErro}" /></span>
                    </c:if>
                </div>
                    
                <div class="form-group w-75 p3 mx-auto">
                    <label for="portfolio">Portfólio</label>
                    <input id="portifolio" class="form-control" type="portifolio" name="portifolio" placeholder="https://www.instagram.com/artista" required value="${portifolio}">
                    <small>Ex: instagram, behance, linkedin</small>
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${portifolioErro != null}">
                        <span class="erro"><c:out value="${portifolioErro}" /></span>
                    </c:if>
                </div>

                <div class='d-flex justify-content-center'>
                    <button class="w-50 btn btn-primary" type="submit">Enviar dados</button>
                </div>
            </form>
        </div>
    </body>
</html>
