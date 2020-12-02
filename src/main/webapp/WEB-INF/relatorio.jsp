<%-- 
    Document   : relatorio
    Created on : 02/12/2020, 13:40:50
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/relatorio.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Perifarte - Relatório de Doações</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <div class="container content-container">
            <a class="back-button sublinhado" href="${pageContext.request.contextPath}/home"><i class="fas fa-arrow-left" ></i>  Voltar</a>
            
            <h5 id="total-arrecadado-h5">Total arrecadado:</h5>
            <div class="d-flex justify-content-center total-arrecadado">
                <h2>R$ <c:out value="${totalDoado}0"/></h2>
            </div>
            
            <div class="lista-arrecadado">
                <ul class="list-group list-group-flush">
                    <h5 class="titulo-lista">Arrecadado por organização:</h5>
                    <c:forEach var="org" items="${organizacoes}">
                        <li class="list-group-item">
                            <div class="d-flex justify-content-between">
                                <h4 class="titulo-org"><c:out value="${org.nome}" /></h4>
                                <div>
                                    <p>Total arrecadado: <br>R$ <c:out value="${org.totalRecebido}" /></p>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>
