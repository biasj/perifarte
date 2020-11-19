<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-dark bg-dark justify-content-between">
    <div class="container" id="nav">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/home">Perifarte</a>
        <form class='form-inline'>
            <c:choose>
                <c:when test="${sessionScope.usuario != null}">
                    <a class='nav-link' href='#'><i class="fas fa-shopping-cart" style="font-size: 28px; color: white"></i></a>
                    
                    <!--dropdown-->
                    <div class="dropdown ">
                      <a class="btn btn-secondary dropdown-toggle nav-link" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="far fa-user-circle" style="font-size: 33px; color: white;" ></i>
                      </a>

                      <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/perfil">Meu perfil</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
                      </div>
                    </div>
                        
                </c:when>
                <c:otherwise>
                    <a class='nav-link' href='./login'><i class="far fa-user-circle" style="font-size: 33px; color: white;" ></i></a>
                    <a class='nav-link' href='#'><i class="fas fa-shopping-cart" style="font-size: 28px; color: white;"></i></a>
                </c:otherwise>
            </c:choose>
        </form>
    </div>

</nav>  
