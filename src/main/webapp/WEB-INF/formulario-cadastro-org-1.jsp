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
        <title>Solicitação de Cadastro</title>
    </head>
    <body>
        <h1>Solicitação de Cadastro de Organização</h1>
        
<!--        formulário-->
         <form method="post" action="solicitacao-cadastro-org-1">
                <div>
                    <label>Nome</label>
                    <input type="text" name="nome">
                    <c:if test="${false}">
                        <span>Mensagem de erro</span>
                    </c:if>
                </div>
                <div>
                    <label>CNPJ</label>
                    <input type="text" name="cnpj">
                </div>
                <div>
                    <label>Telefone</label>
                    <input type="number" name="telefone">
                </div>
                <div>
                    <label>E-mail</label>
                    <input type="email" name="email">
                </div>
                <div>
                    <label>Senha</label>
                    <input type="password" name="senha">
                </div>
                <div>
                    <button type="reset">Reiniciar dados</button>
                    <button type="submit">Enviar dados</button>
                </div>
            </form>
    </body>
</html>
