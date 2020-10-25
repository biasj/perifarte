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
        <title>Cadastro</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <h1>Cadastro</h1>
        <form method="post" action="processar-cadastro">
            <div>
                <label>Nome</label>
                <div>
                    <input type="text" name="nome" required value="${nome}">
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${nomeErro != null}">
                        <span class="erro"><c:out value="${nomeErro}" /></span>
                    </c:if>
                </div>
            </div>
            
            <div>
                <label>E-mail</label>
                <div>
                    <input type="email" name="email" required value="${email}">
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${emailErro != null}">
                        <span class="erro"><c:out value="${emailErro}" /></span>
                    </c:if>
                </div>
            </div>
                    
            <div>
                <label>Senha</label>
                <!--TODO: redefinir senha-->
                <div>
                    <input type="password" name="senha" required>
                </div>
            </div>
                    
            <div>    
                <input type="checkbox" value="artista" name="artista" ${artista.contains('artista') ? 'checked' : ''}><label>Sou um artista</label>
                <input type="text" name="portfolio" placeholder="portfolio">
            </div>  
                    
            <div>
                <button type="submit">Enviar dados</button>
            </div>
        </form>
    </body>
</html>
