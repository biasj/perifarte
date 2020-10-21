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
        <title>JSP Page</title>
        <style>
            .erro {
                color: red;
            }
        </style>
    </head>
    <body>
        <h1>Solicitação de Cadastro de Organização</h1>
        <h3>Informações sobre a org <c:out value="${organizacao.nome}" /></h3>
        <form method='post' action='solicitacao-cadastro-org-2'>
            <div>
                <label>Descrição da organização:</label>
                <input type="text" name="descricao" value="${descricao}">
                <!-- caso o servlet aponte um erro -->
                    <c:if test="${descricaoErro != null}">
                        <span class="erro"><c:out value="${descricaoErro}" /></span>
                    </c:if>
            </div>
            <div>
                <label>O que será realizado com as doações?</label>
                <input type="text" name="justificativa" value="${justificativa}">
                <!-- caso o servlet aponte um erro -->
                    <c:if test="${justificativaErro != null}">
                        <span class="erro"><c:out value="${justificativaErro}" /></span>
                    </c:if>
            </div>
            <div>
                <button type="reset">Reiniciar dados</button>
                <button type="submit">Enviar dados</button>
            </div>
        </form>
    </body>
</html>
