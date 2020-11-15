<%-- 
    Document   : nao-autorizado
    Created on : 14/11/2020, 20:56:04
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Erro - Não Autorizado</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <div class="container">
            <h1>Erro - Página não autorizada</h1>
            <p>Infelizmente você não tem acesso a essa página. Por favor faça seu login novamente</p>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/login">Login</a>
        </div>
    </body>
</html>
