<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar justify-content-between">
    
    <div class="container" id="nav">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">perifarte</a>
     
        <form class='form-inline'>
            <c:choose>
                <c:when test="${sessionScope.usuario != null}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/perfil"><i class="far fa-user-circle"></i></a>                        
                    <a class='nav-link' href='${pageContext.request.contextPath}/carrinho'><i class="fas fa-shopping-cart"></i></a>
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout"><i class="fas fa-sign-out-alt"></i></a>
                    
                </c:when>
                <c:otherwise>
                    <a class='nav-link' href='${pageContext.request.contextPath}/login'><i class="far fa-user-circle"></i></a>
                    <a class='nav-link' href='${pageContext.request.contextPath}/carrinho'><i class="fas fa-shopping-cart"></i></a>
                </c:otherwise>
            </c:choose>
        </form>
        
    </div>

</nav>  
