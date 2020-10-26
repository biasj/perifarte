<%-- 
    Document   : formulario-cadastro-org-2
    Created on : 20/10/2020, 19:52:08
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <title>JSP Page</title>
    </head>
    <body>
        <!--não consegui colocar pelo pageContext-->
        <c:import url="./../cabecalho.jsp"/>
        <div class="container">
            <form method='post' action='solicitacao-cadastro-org-2'>
                <h2 class='form-group w-75 p3 mx-auto'>Solicitação de Cadastro de Organização</h2>
                <h3 class='form-group w-75 p3 mx-auto'>Organização: <c:out value="${organizacao.nome}"/></h3>
                <div class="form-group w-75 p3 mx-auto">
                    <label>Descrição da organização:</label>
                    <input class="form-control" type="text" name="descricao" required value="${descricao}">
                    <!-- caso o servlet aponte um erro -->
                        <c:if test="${descricaoErro != null}">
                            <span class="erro"><c:out value="${descricaoErro}" /></span>
                        </c:if>
                </div>
                <div class="form-group w-75 p3 mx-auto">
                    <label>O que será realizado com as doações?</label>
                    <input class="form-control" type="text" name="justificativa" required value="${justificativa}">
                    <!-- caso o servlet aponte um erro -->
                        <c:if test="${justificativaErro != null}">
                            <span class="erro"><c:out value="${justificativaErro}" /></span>
                        </c:if>
                </div>
                <div class="form-group w-75 p3 mx-auto">
                    <button class="btn btn-outline-danger" type="reset">Reiniciar dados</button>
                    <button class="btn btn-primary" type="submit">Enviar dados</button>
                </div>
            </form>
        </div>
        
    </body>
</html>
