<%-- 
    Document   : form-login
    Created on : 21/10/2020, 13:20:07
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
        <title>Perifarte - Login</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <!-- INSERIR MENSAGENS DE LOGIN/CADASTRO pela sessao ? -->
        
        <div class='container'>
            <div class="content-container">
                <form method="post" action="${pageContext.request.contextPath}/login">
                    <c:if test="${loginErro != null}">
                         <div class="alert alert-danger" role="alert">
                             <span ><c:out value="${loginErro}" /></span>
                         </div>  
                     </c:if>

                     <h2 class='form-group w-75 p3 mx-auto titulo-pagina'>Login</h2>

                     <div class='form-group w-75 p3 mx-auto'>

                         <label for='email'>E-mail</label>
                         <input class='form-control' id='email' type="email" name="email" required value="${email}">
                         <!-- caso o servlet aponte um erro -->
                         <c:if test="${emailErro != null}">
                             <span class="erro"><c:out value="${emailErro}" /></span>
                         </c:if>
                     </div>

                     <div class='form-group mb-2 w-75 p3 mx-auto'>
                         <div class="d-flex w-100 justify-content-between">
                             <label for='senha'>Senha</label>
                             <a class=' mb-2 sublinhado' href="#">Esqueceu?</a>
                         </div>    

                         <!--TODO: redefinir senha-->
                         <input class='form-control' id='senha' type="password" name="senha" value="${senha}" required>
                         <c:if test="${senhaErro != null}">
                             <span class="erro"><c:out value="${senhaErro}" /></span>
                         </c:if>
                     </div>

                     <div class='form-group w-75 p3 mx-auto'>
                         <div><a class="login sublinhado" href="./cadastro">Ainda não tenho cadastro</a></div>
                     </div>

                     <div class='d-flex justify-content-center'>
                         <button class='w-50 btn btn-primary' type="submit">Fazer Login</button>
                     </div>

                 </form>
            </div>
        </div>
        
    </body>
</html>
