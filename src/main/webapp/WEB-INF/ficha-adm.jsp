<%-- 
    Document   : ficha-adm
    Created on : 10/11/2020, 14:48:18
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
        
        <title>Perifarte - Administrador</title>
    </head>
    <body>
         <c:import url="./../cabecalho.jsp"/>
         
         <div class="container content-container">
             <c:if test="${atualizacaoSucesso != null}">
                <div class="alert alert-success form-group w-75 mx-auto" role="alert">
                    <span class="sucesso"><c:out value="${atualizacaoSucesso}" /></span>
                </div>  
            </c:if>
                
            <a class="back-button" href="${pageContext.request.contextPath}/painel/adm"><i class="fas fa-chevron-left" ></i> Voltar</a>
            
            <div class="painel">
                <form method="post" action="adm">
                    <h3 class="titulo-pagina">Ficha Administrador</h3>
                    <div class="form-group w-75 p3 mx-auto">
                        <label for="admEdit-nome">Nome</label>
                        <input class="form-control" id="admEdit-nome" type="text" name="admEdit-nome" required value="${admEdit.nome}">
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="admEdit-email">Email</label>
                        <input class="form-control" id="admEdit-email" type="email" name="admEdit-email" required value="${admEdit.email}">
                    </div>

                    <div class="form-group w-75 p3 mx-auto">
                        <label for="admEdit-id">ID</label>
                        <div>
                            <input class="form-control" id="admEdit-id" type="number" name="admEdit-id" required value ="${admEdit.id}">
                        </div>
                    </div>
                        
                    <c:if test="${admEdit.status == 'pendente'}">
                        <div class="form-group w-75 mx-auto d-flex justify-content-between">
                            <button type="submit" name="botaoExcluir" class="btn btn-outline-danger">Excluir</button>
                            <button type="submit" name="botaoAprovar" class="btn btn-warning">Aprovar Administrador</button>
                        </div>
                    </c:if>
                        
                    <c:if test="${admEdit.status =='aprovado'}">
                        <div class="form-group w-75 mx-auto d-flex justify-content-center">
                            <button type="submit" name="botaoExcluir" class="btn btn-outline-danger">Excluir Administrador</button>
                        </div>
                    </c:if>
                </form>
            </div>
         </div>
                        
    </body>
</html>
