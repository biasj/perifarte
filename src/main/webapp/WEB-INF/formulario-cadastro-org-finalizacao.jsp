<%-- 
    Document   : formulario-cadastro-org-finalizacao
    Created on : 20/10/2020, 20:04:24
    Author     : beatrizsato
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--para "setar" a tela a partir do tamanho da tela do dispositivo-->
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
        <title>JSP Page</title>
    </head>
    <body>
        <!--não consegui colocar pelo pageContext-->
        <c:import url="./../cabecalho.jsp"/>
        <h1>Solicitação de Cadastro de Organização</h1>
        <c:if test="${organizacao.nome != null}">
            <h3> <c:out value="${organizacao.nome}" /></h3>
            <p>E-mail: <c:out value="${organizacao.email}" /></p>
            <p>Descricao: <c:out value="${organizacao.descricao}" /></p>
        </c:if>
        <c:if test="${organizacao.nome == null}">
            <p>Acesso negado, faça o login para acessar a plataforma</p>
            <a href="./login">login</a>
        </c:if>
            
    </body>
</html>
