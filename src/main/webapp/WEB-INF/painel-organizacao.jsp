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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilo.css">
        <!--bootstrap-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" crossorigin="anonymous">
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        <title>Perifarte - Obras doadas</title>
    </head>
    <body>
        <c:import url="./../cabecalho-painel.jsp"/>
        <h2> <c:out value="${organizacao.nome}" /></h2>
        
        <h3>Obras que foram doadas para sua organização</h3>
        
        <div class="list-obras-doadas">
        	<c:choose>
        		<c:when test="${obra.obraDoada != null && obra.obraDoada.length > 0}">
     				<ul>
		     			<c:forEach var="org" items="${administrador.organizacoes}">
			     			<li>
			     			<a class="list-group-item list-group-item-action" href="./editar/org">
			     				<h5 class="mb-1"><c:out value="${obra.nome}"/></h5>
			     				<h5 class="mb-1"><c:out value="${artista.nome}"/></h5>
			     				<h5 class="mb-1"><c:out value="${obra.preco}"/></h5>
			                </a>
		     				</li>
		     			</c:forEach>
		            </ul>
		        </c:when>
		   
		    <c:otherwise>
		    	<p>Infelizmente obras ainda não foram revertidas para a sua Organização.</p>
		    </c:otherwise>
		   	</c:choose>
        </div> 
        
        <div class="total-doado">
	        <c:choose>
	        	<c:when test="${obra.obraDoada != null && obra.obraDoada.length > 0}"> 
	        		<c:forEach var="totalDoado" items="${doacoes.totalDoado}">
	        		<h4><c:out value="${totalDoado}"/></h4>
	        		</c:forEach>
	        	</c:when>
	        
		    	<c:otherwise>
		    		<p>Total doado: R$ 0.00</p>
		    	</c:otherwise>
		    </c:choose>
        </div>
        
    </body>
</html>
