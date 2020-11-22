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
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        
        <!--css-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/painel.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        
        <title>Perifarte - Cadastro</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        <div class="container content-container">
            <a class="back-button" href="${pageContext.request.contextPath}/painel/artista"><i class="fas fa-chevron-left" ></i> Voltar</a>
            
            <div class="painel">
                <form method="post" action="processar-cadastro-obra">
                    <h2 class='form-group w-75 p3 mx-auto titulo-pagina'>Cadastro de Obra</h2>

                    <div class='form-group w-75 p3 mx-auto'>
                        <label for="titulo">Titulo</label>
                        <input class="form-control" type="text" id="titulo" name="titulo" required value="${titulo}">
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${tituloErro != null}">
                            <span class="erro"><c:out value="${tituloErro}" /></span>
                        </c:if>
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="descricao-obra">Descrição</label>
                        <input class="form-control" type="text" id="descricao-obra" name="descricao" required value="${descricao}">
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${descricaoErro != null}">
                            <span class="erro"><c:out value="${descricaoErro}" /></span>
                        </c:if>
                    </div>

                    <div class='form-group w-75 p3 mx-auto'>
                        <label for="preco">Preço</label>
                        <input class="form-control" id="preco-obra" type="number" name="preco" required value ="${preco}">
                        <!-- caso o servlet aponte um erro -->
                         <c:if test="${precoErro != null}">
                            <span class="erro"><c:out value="${precoErro}" /></span>
                        </c:if>
                    </div>

                    <div class='form-group w-75 p3 mx-auto'>
                        <label for="org-obra">Organização beneficiada:</label>
                        <select class="form-control" id="org-obra" name="ongEscolhida" required value="${ongEscolhida}">
                            <c:forEach var="org" items="${organizacoes}">
                                <option>${org.nome}</option>
                            </c:forEach>
                        </select>
                        <!-- caso o servlet aponte um erro -->
                         <c:if test="${ongEscolhidaErro != null}">
                            <span class="erro"><c:out value="${ongEscolhidaErro}" /></span>
                        </c:if>
                    </div>

                    <div class='form-group w-75 p3 mx-auto'>
                        <input class="form-control-file" type="file" name="file" size="50"/><br/>
                    </div>

                    <div class='form-group w-75 p3 mx-auto d-flex justify-content-center'>
                        <button class="w-50 btn btn-primary" type="submit">Cadastrar obra</button>
                    </div>

                </form>
            </div>
        </div>
        
    </body>
</html>
