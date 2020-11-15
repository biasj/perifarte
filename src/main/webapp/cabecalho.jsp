<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-dark bg-dark justify-content-between">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Perifarte</a>
    <form class='form-inline'>
        <c:choose>
            <c:when test="${sessionScope.usuario != null}">
                <a class='nav-link' href='#'><i class="fas fa-shopping-cart" style="font-size: 28px; color: white"></i></a>
                <a class='nav-link' href='${pageContext.request.contextPath}/logout'><i class="fas fa-sign-out-alt" style="font-size: 28px; color: white"></i></a>
            </c:when>
            <c:otherwise>
                <a class='nav-link' href='./login'><i class="far fa-user-circle" style="font-size: 33px; color: white;" ></i></a>
                <a class='nav-link' href='#'><i class="fas fa-shopping-cart" style="font-size: 28px; color: white;"></i></a>
            </c:otherwise>
        </c:choose>
        
    </form>
</nav>