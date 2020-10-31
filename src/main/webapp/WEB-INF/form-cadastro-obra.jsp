<%-- 
    Document   : form-cadastro-obra
    Created on : 24/10/2020, 18:26:54
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Cadastro</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <h1>Cadastro de Obra</h1>
        <form method="post" action="processar-cadastro-obra">
            <div>
                <label>Titulo</label>
                <div>
                    <input type="text" name="titulo" required value="${titulo}">
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${tituloErro != null}">
                        <span class="erro"><c:out value="${tituloErro}" /></span>
                    </c:if>
                </div>
            </div>
            
            <div>
                <label>descricao</label>
                <div>
                    <input type="text" name="descricao" required value="${descricao}">
                    <!-- caso o servlet aponte um erro -->
                    <c:if test="${descricaoErro != null}">
                        <span class="erro"><c:out value="${descricaoErro}" /></span>
                    </c:if>
                </div>
            </div>
                    
            <div>
                <label>preco</label>
                <!--TODO: redefinir senha-->
                <div>
                    <input type="number" name="preco" required value ="${preco}">
                    <!-- caso o servlet aponte um erro -->
                     <c:if test="${precoErro != null}">
                        <span class="erro"><c:out value="${precoErro}" /></span>
                    </c:if>
                </div>
            </div>
            <div>
                <label>ongEscolhida</label>
                <!--TODO: redefinir senha-->
                <div>
                    <input type="text" name="ongEscolhida" required value ="${ongEscolhida}">
                    <!-- caso o servlet aponte um erro -->
                     <c:if test="${ongEscolhidaErro != null}">
                        <span class="erro"><c:out value="${ongEscolhidaErro}" /></span>
                    </c:if>
                </div>
            </div>
            <div>
                <input type="file" name="file" size="50"/><br/>
            </div>
            <div>
                <button type="submit" value="Upload File">Enviar dados</button>
            </div>

        </form>
    </body>
</html>
