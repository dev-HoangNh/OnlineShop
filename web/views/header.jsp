<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--HEADER-->
<div class="container">
    <header>
        <div class="row mt-3">
            <div class="col-2 d-flex justify-content-center">
                <a href="home"><img class="img-fluid" src="images/logo.png" id="logo" alt="logo"></a>
            </div>
            <div class="col-6 text-header d-flex justify-content-center">
                <form action="store" method="post" class="mt-4">
                    <div class="input-group mb-3 mt-3">
                        <input type="text" class="form-control" name="search" placeholder="Search for fruit" aria-label="Search for fruit" aria-describedby="button-addon2">
                        <button class="btn btn-product" type="submit" id="button-addon2"><i class="fas fa-search"></i> Search</button>
                    </div>
                    <h6 class="mt-2">First company in Vietnam refund & exchange goods for NO REASON within 48 hours</h6>
                </form>
                <div></div>
            </div>
            <div class="col-4 mt-4">
                <div class="mt-4 float-end">
                    <c:choose>
                        <c:when test="${sessionScope.account == null}">
                            <a class="text-header text-decoration-none mx-2" href="login"><i class="fas fa-sign-in-alt"></i> Login</a>
                            <span class="text-header">|</span>
                            <a class="text-header text-decoration-none mx-2" href="register"><i class="fas fa-registered"></i> Register</a>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${sessionScope.account.role eq 'member'}">
                                    <a class="text-header text-decoration-none mx-2" href="viewInfor"><i class="fas fa-user"></i> Hi ${sessionScope.account.userName}</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="text-header text-decoration-none mx-2" href="adminViewOrder"><i class="fas fa-user"></i> Hi admin</a>
                                </c:otherwise>
                            </c:choose>
                            <span class="text-header">|</span>
                            <a class="text-header text-decoration-none mx-2" href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
                        </c:otherwise>
                    </c:choose>
                        <c:if test="${sessionScope.account.role eq 'member'}">
                        <span class="text-header">|</span>
                        <a class="text-header text-decoration-none mx-2 position-relative" href="cart"><i
                                class="fas fa-shopping-cart"></i> Cart <span
                                class="ms-3 position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">${sessionScope.cartProducts.size()}</span></a>
                        </c:if>
                </div>
            </div>
        </div>
        <hr>
    </header>

    <div class="row text-header my-3">
        <div class="col-3 d-flex justify-content-center">
            <a class="text-decoration-none text-header fs-5 fw-bold hover-underline-animation" href="store">Store</a>
        </div>
        <div class="col-3 d-flex justify-content-center">
            <a class="text-decoration-none text-header fs-5 fw-bold hover-underline-animation" href="store?type=gift">Fruit gift</a>
        </div>
        <div class="col-3 d-flex justify-content-center">
            <a class="text-decoration-none text-header fs-5 fw-bold hover-underline-animation" href="store?type=fruit">Fruit</a>
        </div>
        <div class="col-3 d-flex justify-content-center">
            <a class="text-decoration-none text-header fs-5 fw-bold hover-underline-animation" href="store?type=daily">Daily fresh fruit</a>
        </div>
    </div>
</div>
<!--END HEADER-->
