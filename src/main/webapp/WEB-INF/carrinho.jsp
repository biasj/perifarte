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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/carrinho.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>          
        <title>Perifarte - Carrinho</title>
    </head>
    <body>
    	<c:import url="./../cabecalho.jsp"/> 
        <div class="container">
            <c:if test="${erro != null}">
                <div class="alert alert-danger" role="alert">
                    <span><c:out value="${erro}" /></span>
                </div>  
            </c:if>
            
            
            <h2 class="text-center">Meu carrinho</h2>

            <div class="container content-container">
                <div class="row row-cols-1 row-cols-md-3">
                    <form method="post" action="carrinho">
                        <!--para cada obra no carrinho mostra as informacoes-->
                        <c:forEach var="detalhe" items="${obras}">
                            <div class="col mb-4">
                                <div class="card h-100">
                                    
                                    <div class="media obra-mini">
                                        <img class="mr-3 mini-obra-artista" src="${pageContext.request.contextPath}/imagem-obra?id=${detalhe.obra.id}" >

                                        <div class="media-body">
                                            <h5 class="mt-0"><c:out value="${detalhe.obra.titulo}"/></h5>
                                            <p class="mb-1">Organização: <c:out value="${detalhe.obra.organizacao.nome}"/></p>
                                            <p class="mb-1">Artista: <c:out value="${detalhe.artista.nome}"/></p>
                                            <p class="mb-1">Valor: R$ <c:out value="${detalhe.obra.preco}0"/></p>
                                        </div>
                    
                                    </div>
                                        
                                    <hr>
                                        
                                    <div class="d-flex justify-content-center">
                                        <a class="retira-carrinho" href="${pageContext.request.contextPath}/retira-carrinho?id=${detalhe.obra.id}">Retirar da cesta</a>
                                    </div>

                                </div>
                            </div>
                        </c:forEach>
                        
                        <!--confere se está existem obras no carrinho para mostrar o resumo ou a mensagem-->
                        <c:choose>
                            <c:when test="${obras != null}">
                                <div class="resumo">
                                    <h5>Resumo do pedido: </h5>
                                    <div class="d-flex justify-content-between">
                                        <p><c:out value="${numObra} obra"/><c:if test="${numObra > 1}">s</c:if></p>
                                        <h6>R$ <c:out value="${total}0"/></h6>
                                    </div>
                                    <c:if test="${usuario != null}">
                                        <div class="d-flex justify-content-between">
                                            <p>Endereço de e-mail: </p>
                                            <p><c:out value="${usuario.email}"/></p>
                                        </div>
                                        <small>*As obras compradas serão enviadas por e-mail</small>
                                    </c:if>
                                </div>

                                <div class="d-flex justify-content-center">
                                    <button class="btn btn-primary" name="ProsseguirComPagamento">Prosseguir com o pagamento</button>
                                </div>
                            </c:when>
                            
                            <c:otherwise>
                                <p>Infelizmente ainda não há obras no carrinho</p>
                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/home">Comprar obras</a>
                            </c:otherwise>
                        </c:choose>

                    </form>
                </div>

            </div>
        </div>
    </body>
</html>