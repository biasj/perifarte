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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/form.css">
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Perifarte - Solicitação de Cadastro</title>
    </head>
    <body>
        <!--não consegui colocar pelo pageContext-->
        <c:import url="./../cabecalho.jsp"/>
        <div class="container">
            <h3 class="w-75 p3 mx-auto">Cadastro de Organização</h3>
            <c:if test="${organizacao.nome != null}">
                <h3 class="w-75 p3 mx-auto"> <c:out value="${organizacao.nome}" /></h3>
                <h4 class="w-75 p3 mx-auto">Solicitação enviada!</h4>
                <p class="w-75 p3 mx-auto">Suas informações foram recebidas e estão na fila para serem analisadas pela nossa equipe!</p>
                <p class="w-75 p3 mx-auto">A organização receberá confirmação de cadastro no e-mail: </p>
                <p class="w-75 p3 mx-auto"><c:out value="${organizacao.email}" /></p>
            </c:if>
            <c:if test="${organizacao.nome == null}">
                <p class="w-75 p3 mx-auto">Acesso negado, faça o login para acessar a plataforma</p>                
                <div class="d-flex justify-content-center ">
                    <a class="w-50 btn btn-primary" href="./login">login</a>
                </div>
            </c:if>
        </div>
        
            
    </body>
</html>
