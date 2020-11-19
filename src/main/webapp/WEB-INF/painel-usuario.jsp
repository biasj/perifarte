<%-- 
    Document   : painel-usuario
    Created on : 21/10/2020, 14:36:42
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
        <title>Perifarte - Meus pedidos</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        
        <!--será painel de meus pedidos-->
        <div class="container">
            <h1>Logado</h1>
            <h3>Nome: <c:out value="${usuario.nome}"/> </h3>
            <p>E-mail: <c:out value="${usuario.email}"/></p>
        </div>

    </body>
</html>
