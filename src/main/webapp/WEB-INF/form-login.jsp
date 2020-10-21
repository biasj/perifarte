<%-- 
    Document   : form-login
    Created on : 21/10/2020, 13:20:07
    Author     : beatrizsato
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
        <title>Login</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <!--INSERIR MENSAGENS DE LOGIN/CADASTRO pela sessao ?-->
        
        <h1>Login</h1>
        <form method="post" action="processamento">
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
                <label>Senha</label><a href="#">Esqueceu?</a>
                <!--TODO: redefinir senha-->
                <div>
                    <input type="password" name="senha" value="${senha}" required>
                    <c:if test="${senha != null}">
                        <span class="erro"><c:out value="${senhaErro}" /></span>
                    </c:if>
                </div>
            </div>
            <div>
                <div><a class="login" href="./cadastro">Ainda não tenho cadastro</a></div>
                <div><a class="login" href="./formulario-org">Sou uma organizacao e quero me cadastrar</a></div>
            </div>
            <div>
                <button type="submit">Enviar dados</button>
            </div>
        </form>
    </body>
</html>
