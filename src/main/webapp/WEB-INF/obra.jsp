<%-- 
    Document   : obra
    Created on : 14/11/2020, 23:41:20
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>        
        <title>Perifarte - Obra</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/> 
        <div class="container">
            <a class="back-button" href="${pageContext.request.contextPath}/home"><i class="fas fa-chevron-left" ></i> Voltar</a>
            <div>
                <img src="" alt="">
                <div class='d-flex justify-content-between'>
                    <h3><c:out value="${detalhe.obra.titulo}"/></h3>
                    <h5>Arrecadado: </h5>
                </div>
                
                <div class="d-flex justify-content-between">   
                    <a href="https://${detalhe.artista.portifolio}"><c:out value="${detalhe.artista.nome}"/></a>
                    <p><c:out value="${detalhe.obra.organizacao.nome}"/></p> 
                </div>
            </div>
            
            <div class="d-flex justify-content-between detalhe-obra">
                <h3>R$ <c:out value="${detalhe.obra.preco}"/></h3>
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/carrinho">Comprar</a>
            </div>
            
            <h3>Descrição</h2>
            <p><c:out value="${detalhe.obra.descricao}"/></p>
            
            <h3>Informações sobre a Organização</h2>
            <p><c:out value="${detalhe.obra.organizacao.descricao}"/></p>
            
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/carrinho">Adicionar ao Carrinho</a>
        </div>
    </body>
</html>
