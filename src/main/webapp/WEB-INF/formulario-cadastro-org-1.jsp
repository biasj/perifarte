<%-- 
    Document   : solicitacao-cadastro-org-1
    Created on : 20/10/2020, 19:00:35
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
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Perifarte - Solicitação de Cadastro</title>
    </head>
    <body>
        <!--não consegui colocar pelo pageContext-->
        <c:import url="./../cabecalho.jsp"/>
        
        <div class='container'>
            <form method="post" action="solicitacao-cadastro-org-1">
               <h2 class='form-group w-75 p3 mx-auto'>Solicitação de Cadastro de Organização</h2>
               
               <div class='form-group w-75 p3 mx-auto'>
                   <label for='nome'>Nome</label>
                   <input id='nome' class='form-control' type="text" name="nome" required value="${nome}">
                   <!-- caso o servlet aponte um erro -->
                   <c:if test="${nomeErro != null}">
                       <span class="erro"><c:out value="${nomeErro}" /></span>
                   </c:if>
               </div>

               <div class='form-group w-75 p3 mx-auto'>
                   <label for='cnpj'>CNPJ</label>
                   <input id='cnpj' class='form-control' type="text" name="cnpj" value="${cnpj}">
                   <!-- caso o servlet aponte um erro -->
                   <c:if test="${cnpjErro != null}">
                       <span class="erro"><c:out value="${cnpjErro}" /></span>
                   </c:if>
               </div>

               <div class='form-group w-75 p3 mx-auto'>
                   <label for='telefone'>Telefone</label>
                   <input class='form-control' type="number" id='telefone' name="telefone" value="${telefone}">
               </div>

               <div class='form-group w-75 p3 mx-auto'>
                   <label for='email'>E-mail</label>
                   <input id='email' class='form-control' type="email" name="email" value="${email}">
                   <!-- caso o servlet aponte um erro -->
                   <c:if test="${emailErro != null}">
                       <span class="erro"><c:out value="${emailErro}" /></span>
                   </c:if>
               </div>
                   
               <div class='form-group w-75 p3 mx-auto'>
                   <label for='senha'>Senha</label>
                   <input class='form-control' type="password" id='senha' name="senha">
               </div>
                   
               <div class='form-group w-75 p3 mx-auto'>
                   <button class='btn btn-outline-danger' type="reset">Reiniciar dados</button>
                   <button class='btn btn-primary' type="submit">Enviar dados</button>
               </div>
           </form>
        </div>
         
    </body>
</html>
