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
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        
        <!--css-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/painel.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>

        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        
        <title>Perifarte - Organizações</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        
        <div class="container content-container">
            <a class="back-button" href="${pageContext.request.contextPath}/painel/adm"><i class="fas fa-chevron-left" ></i> Voltar</a>
           
            <div class="painel">
                <c:if test="${atualizacaoSucesso != null}">
                    <div class="alert alert-success form-group w-75 mx-auto" role="alert">
                        <span class="sucesso"><c:out value="${atualizacaoSucesso}" /></span>
                    </div>  
                </c:if>
                
                <form method="post" action="org">
                    <!--cabeçalho do formulário-->
                    <div class="form-group w-75 p3 mx-auto">
                        <h3 class="mb-1"><c:out value="${org.nome}"/></h3>
                        <p class="mb-1">Status: <c:out value="${org.status}"/></p>
                        <p class="mb-1">Valor arrecadado: R$ 1000</p>
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <h5 class="mb-1">Descrição</h5>
                        <p class="mb-1"><c:out value="${org.descricao}"/></p> 
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <h5 class="mb-1">Justificativa</h5>
                        <p class="mb-1"><c:out value="${org.justificativa}"/></p>
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <c:if test="${org.status == 'pendente' || org.status == 'suspenso'}">
                            <div class="d-flex justify-content-between">
                                <button class="btn btn-outline-danger" type="submit" name="excluir">Excluir Solicitação</button>
                                <button class="btn btn-primary" type="submit" name="aprovar">Aprovar Cadastro</button> 
                            </div>
                        </c:if>
                        <c:if test="${org.status == 'aprovado'}">
                            <button class="btn btn-outline-danger" name="suspender">Suspender Cadastro</button>
                        </c:if> 
                    </div>

                </form>
            </div>
                
        </div>
    </body>
</html>
