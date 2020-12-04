<%-- 
    Document   : ficha-artista
    Created on : 28/10/2020, 22:09:36
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/painel.css">
        
        <!--font awesome -> icons-->
        <script src="https://kit.fontawesome.com/4b644107cc.js" crossorigin="anonymous"></script>
        
        <title>Perifarte - Obra</title>
    </head>
    <body>
         <c:import url="./../cabecalho.jsp"/>
         
         <div class="container content-container">
             <c:if test="${atualizacaoSucesso != null}">
                <div class="alert alert-success" role="alert">
                    <span><c:out value="${atualizacaoSucesso}" /></span>
                </div>  
            </c:if>
             
            <a class="back-button" href="${pageContext.request.contextPath}/painel/artista"><i class="fas fa-chevron-left" ></i> Voltar</a>
            
            <div class='painel'>
                <form method="post" action="obra">
                    <h3 class="form-group w-75 p3 mx-auto">Atualizar obra</h3>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="titulo-obra">Titulo</label>
                        <input class="form-control" id="titulo-obra" type="text" name="titulo" required value="${obra.titulo}">
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${tituloErro != null}">
                            <span class="erro"><c:out value="${tituloErro}" /></span>
                        </c:if>
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="descricao-obra">Descrição</label>
                        <input class="form-control" id="descricao-obra" type="text" name="descricao" required value="${obra.descricao}">
                        <!-- caso o servlet aponte um erro -->
                        <c:if test="${descricaoErro != null}">
                            <span class="erro"><c:out value="${descricaoErro}" /></span>
                        </c:if>
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="preco-obra">Preço</label>
                        <div>
                            <input class="form-control" id="preco-obra" type="number" name="preco" required value ="${obra.preco}">
                            <!-- caso o servlet aponte um erro -->
                             <c:if test="${precoErro != null}">
                                <span class="erro"><c:out value="${precoErro}" /></span>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="org-obra">Organização beneficiada</label>
                        <select class="form-control" id="org-obra" name="ongEscolhida" required value="${obra.organizacao.nome}">
                            <option selected>${obra.organizacao.nome}</option>
                            <c:forEach var="org" items="${organizacoes}">
                                <c:if test="${org.nome != obra.organizacao.nome}">
                                    <option>${org.nome}</option>
                                </c:if>

                            </c:forEach>
                        </select>

                         <c:if test="${ongEscolhidaErro != null}">
                            <span class="erro"><c:out value="${ongEscolhidaErro}" /></span>
                        </c:if>
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <input id="file" type="file" name="file" size="50"/><br/>
                    </div>

                    <div class="form-group w-75 mx-auto d-flex justify-content-between">
                        <button type="submit" name="botaoExcluir" class="btn btn-outline-danger">Excluir Obra</button>
                        <button type="submit" name="botaoAtualizar" class="btn btn-primary">Atualizar Obra</button>
                    </div>

                </form>
            </div>
         </div>
         
        
    </body>
</html>
