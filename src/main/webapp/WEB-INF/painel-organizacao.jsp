<%-- 
    Document   : painel-organizacao
    Created on : 22/10/2020, 11:02:36
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        
        <title>Perifarte - Obras doadas</title>
    </head>
    <body>
        <c:import url="./../cabecalho.jsp"/>
        
        <div class="container">
            <h2> <c:out value="${organizacao.nome}" /></h2>
        
            <h3>Obras doadas para sua organização</h3>
            
            <c:choose>
                <c:when test="${organizacao.status == 'aprovado'}">
                    <div class="list-obras-doadas">
                        <c:choose>
                            <c:when test="${obrasDoadas != null}">
                                <c:forEach var="obra" items="${obrasDoadas}">
                                        <a class="list-group-item list-group-item-action" href="#">
                                            <h5 class="mb-1"><c:out value="${obra.titulo}"/></h5>
                                            <p class="mb-1"><c:out value="${obra.descricao}"/></p>
                                            <p class="mb-1"><c:out value="${obra.preco}"/></p>
                                        </a>
                                </c:forEach>
                            </c:when>

                            <c:otherwise>
                                <p>Infelizmente obras ainda não foram revertidas para a sua Organização.</p>
                            </c:otherwise>
                        </c:choose>
                    </div> 
                </c:when>
                
                <c:otherwise>
                    <p>Seu cadastro está pendente, suspenso ou excluído</p>
                </c:otherwise>
            </c:choose>
        </div>
        
        
    </body>
</html>
