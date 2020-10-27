<%-- 
    Document   : ficha-org
    Created on : 27/10/2020, 10:11:52
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
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Perifarte - Organizações</title>
    </head>
    <body>
        <c:import url="./../cabecalho-painel.jsp"/>
        <div class="container ">
            <form>
                <div class="form-group w-75 p3 mx-auto">
                    <h3 class="mb-1"><c:out value="${org.nome}"/></h3>
                    <p class="mb-1">Status: <c:out value="${org.status}"/></p>
                    <p class="mb-1">Valor arrecadado: R$ 1000</p>
                </div>

                <!--falta adicionar descricao e justificativa no banco de dados-->
                <div class="form-group w-75 p3 mx-auto">
                    <h5 class="mb-1">Descrição</h5>
                    <p class="mb-1"><c:out value="${org.descricao}"/></p> 
                </div>
                <div class="form-group w-75 p3 mx-auto">
                    <h5 class="mb-1">Justificativa</h5>
                    <p class="mb-1"><c:out value="${org.justificativa}"/></p>
                </div>
                
                <div class="form-group w-75 p3 mx-auto">
                    <c:if test="${org.status == 'pendente'}">
                        <button class="btn btn-primary">Aprovar Cadastro</button>
                    </c:if>
                    <c:if test="${org.status == 'aprovado'}">
                        <button class="btn btn-outline-danger">Suspender Cadastro</button>
                    </c:if> 
                </div>
               
            </form>
        </div>
    </body>
</html>
