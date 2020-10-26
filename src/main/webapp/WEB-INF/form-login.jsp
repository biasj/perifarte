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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <title>Login</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <!--INSERIR MENSAGENS DE LOGIN/CADASTRO pela sessao ?-->
        
        <div class='container'>
           <form method="post" action="processamento">
                <h2 class='form-group w-75 p3 mx-auto'>Login</h2>

                <c:if test="${loginErro != null}">
                            <span class="erro"><c:out value="${loginErro}" /></span>
                </c:if>

                <div class='form-group w-75 p3 mx-auto'>
                    <label for='email'>E-mail</label>
                    <input class='form-control' id='email' type="email" name="email" required value="${email}">
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${emailErro != null}">
                        <span class="erro"><c:out value="${emailErro}" /></span>
                    </c:if>
                </div>

                <div class='form-group mb-2 w-75 p3 mx-auto'>
                    <label for='senha'>Senha</label>
                    <a class=' mb-2' href="#">Esqueceu?</a>
                    <!--TODO: redefinir senha-->
                    <input class='form-control' id='senha' type="password" name="senha" value="${senha}" required>
                    <c:if test="${senhaErro != null}">
                        <span class="erro"><c:out value="${senhaErro}" /></span>
                    </c:if>
                </div>

                <div class='form-group w-75 p3 mx-auto'>
                    <div><a class="login" href="./cadastro">Ainda não tenho cadastro</a></div>
                    <div><a class="login" href="./formulario-org">Sou uma organizacao e quero me cadastrar</a></div>
                </div>

                <div class='form-group w-75 p3 mx-auto'>
                    <button class='btn btn-primary' type="submit">Enviar dados</button>
                </div>
            </form> 
        </div>
        
    </body>
</html>
