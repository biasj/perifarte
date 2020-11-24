<%-- 
    Document   : info
    Created on : 23/11/2020, 10:55:32
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/info.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>      
        <title>Perifarte</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp" /> 
        
        <div class='container'>
            
            <h2 class='text-center'>Como ajudar?</h2>
            <div class="card text-center info-card">
                <div class="card-body">
                  <h5 class="card-title">Feito para artista</h5>
                  <p class="card-text">Para ajudar é simples! Você se cadastra, cadastra sua obra, 
                      escolhe quem você quer ajudar e pronto!</p>
                  <a href="${pageContext.request.contextPath}/cadastro-artista" class="btn btn-primary">Cadastro de Artista</a>
                </div>
            </div>
                
            <div class="card text-center info-card">
                <div class="card-body">
                  <h5 class="card-title">Também para pessoas generosas</h5>
                  <p class="card-text">Alguém tem que comprar as obras dos artistas, não é mesmo?</p>
                  <a href="${pageContext.request.contextPath}/cadastro" class="btn btn-primary">Cadastro de Doador</a>
                </div>
            </div>
            
            <div class="card text-center info-card">
                <div class="card-body">
                  <h5 class="card-title">Organização</h5>
                  <p class="card-text">Alguém tem que receber dinheiro</p>
                  <a href="${pageContext.request.contextPath}/cadastro-org" class="btn btn-primary">Cadastro de Organização</a>
                </div>
            </div>
                
        </div>
    </body>
</html>
