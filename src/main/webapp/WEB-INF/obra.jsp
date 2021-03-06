<%-- 
    Document   : obra
    Created on : 14/11/2020, 23:41:20
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/obras.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>      
        <title>Perifarte - Obra</title>
    </head>
    <body >
        <c:import url="./../cabecalho.jsp"/> 
        <div class="container content-container" >
            <a class="back-button sublinhado" href="${pageContext.request.contextPath}/home"><i class="fas fa-arrow-left" ></i>  Voltar</a>
            <form method="post" action="obra">
                <div class='detalhe-obra'>
                    <div>
                        <img src="${pageContext.request.contextPath}/imagem-obra?id=${detalhe.obra.id}" alt="" class="imagem-detalhe-obra">
                        <h4 class='text-center titulo-obra'><c:out value="${detalhe.obra.titulo}"/></h4>

                        <div class="d-flex justify-content-between">   
                            <a class="sublinhado" href="https://${detalhe.artista.portifolio}"><c:out value="${detalhe.artista.nome}"/></a>
                            <p><c:out value="${detalhe.obra.organizacao.nome}"/></p> 
                        </div>
                        <div class="d-flex justify-content-between">   
                            <h5>Total doado à obra: </h5>
                            <h5>R$ <c:out value="${detalhe.obra.totalArrecadado}0" /></h5> 
                        </div>
                    </div>


                    <div class="d-flex justify-content-between adicionar-carrinho">
                        <h4>R$ <c:out value="${detalhe.obra.preco}0"/></h4>
                        <button class="btn btn-primary btn-obra" type="submit" name="botaoComprar">Comprar</button>
     
                    </div>


                    <h5>Descrição</h5>
                    <p><c:out value="${detalhe.obra.descricao}"/></p>

                    <h5>Informações sobre a Organização</h5>
                    <p><c:out value="${detalhe.obra.organizacao.descricao}"/></p>



                    <div class="d-flex justify-content-center">
                        <button class="btn btn-primary btn-adicionar-carrinho" type="submit" name="botaoComprar">Adicionar ao Carrinho</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
