<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fruit</title>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
        <!-- CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/global.css">
        <script src="js/productDetails.js" defer></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <!--DETAILS-->
        <form action="productDetails" method="post" class="container mt-3 mb-5">
            <div class="row">
                <div class="col-6 row">
                    <div class="col-3">
                        <div class="d-flex justify-content-center"><img class="img-fluid mb-2 rounded" width="80%"
                                                                        src="${sessionScope.productShowDetails.image}" alt=""></div>
                        <div class="d-flex justify-content-center"><img class="img-fluid mb-2 rounded" width="80%"
                                                                        src="${sessionScope.productShowDetails.image}" alt=""></div>
                        <div class="d-flex justify-content-center"><img class="img-fluid mb-2 rounded" width="80%"
                                                                        src="${sessionScope.productShowDetails.image}" alt=""></div>
                        <div class="d-flex justify-content-center"><img class="img-fluid mb-2 rounded" width="80%"
                                                                        src="${sessionScope.productShowDetails.image}" alt=""></div>
                        <div class="d-flex justify-content-center"><img class="img-fluid mb-2 rounded" width="80%"
                                                                        src="${sessionScope.productShowDetails.image}" alt=""></div>
                    </div>
                    <div class="col-9">
                        <div><img class="img-fluid mb-2 rounded" width="100%" src="${sessionScope.productShowDetails.image}" alt=""></div>
                    </div>
                </div>
                <div class="col-6">
                    <h4 class="mt-5">${sessionScope.productShowDetails.productName}</h4>
                    <p>
                        <span class="me-2">Product code: <span class="text-danger">${sessionScope.productShowDetails.productCode}</span></span>
                        <span class="me-2"> | </span>
                        <span class="me-2">Status: <span class="text-danger">${sessionScope.productShowDetails.productStatus ? 'Stocking' : 'Sold-out'}</span></span>
                        <span class="me-2"> | </span>
                        <span class="me-2">Origin: <span class="text-danger">${sessionScope.productShowDetails.origin.name}</span></span>
                    </p>
                    <div class="bg-light pb-1 pt-1 my-4">
                        <div class="ms-3 my-4">
                            <h5>Price:
                                <c:forEach items="${sessionScope.productShowDetails.sizes}" var="a" varStatus="count">
                                    <c:if test="${a.sizeId eq param.size}">
                                        <c:if test="${sessionScope.productShowDetails.sale > 0}">
                                            <span class="ms-5 text-danger">$${a.price - a.price * sessionScope.productShowDetails.sale / 100}</span>
                                        </c:if>
                                        <c:if test="${sessionScope.productShowDetails.sale == 0}">
                                            <span class="ms-5 text-dark">$${a.price}</span>
                                        </c:if>
                                        <c:if test="${sessionScope.productShowDetails.sale > 0}">
                                            <span class="ms-2 text-decoration-line-through text-secondary fw-light">$${a.price}</span>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${sessionScope.productShowDetails.sale > 0}"><span class="ms-2 text-danger py-1 px-1 rounded discount">${sessionScope.productShowDetails.sale}</span></c:if>
                                </h5>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-2">
                                <h5 class="ms-3">Size: </h5>
                            </div>
                            <div class="col-10">
                            <c:forEach items="${sessionScope.productShowDetails.sizes}" var="a" varStatus="count">
                                <input type="text" hidden name="sizeId" value="${param.size}">
                                <div class="mb-4 mx-2 optionSize">
                                    <a href="productDetails?id=${sessionScope.productShowDetails.productId}&size=${a.sizeId}" class="border border-dark rounded py-2 px-2 text-decoration-none text-dark ${param.size eq a.sizeId ? 'labelSize' : ''}">${a.sizeName}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="row mt-5">
                        <div class="col-3">
                            <h5 class="ms-3 mt-1">Quantity: </h5>
                        </div>
                        <div class="col-3">
                            <div class="input-group mb-3">
                                <button class="btn btn-product" type="button" onclick="minusQuantity()"><i
                                        class="fas fa-minus"></i></button>
                                <input type="text" name="quantity" id="quantity" class="form-control text-center" min="1" value="1" aria-label="quantity">
                                <button class="btn btn-product" type="button" onclick="addQuantity()"><i class="fas fa-plus"></i></button>
                            </div>
                        </div>
                        <div class="col-6"></div>
                    </div>
                    <input type="text" hidden name="productId" value="${param.id}">      
                    <div class="row mt-4">
                        <div class="col-6 d-grid">
                            <button type="submit" class="btn btn-color py-3">Add to cart</button>
                        </div>
                        <div class="col-6 d-grid">
                            <button type="submit" class="btn btn-danger py-3">Buy Now</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mt-5 mx-4">
                <div class="w-25 border-bottom border-dark">
                    <h4>Description</h4>
                </div>
                <p class="mt-4">${sessionScope.productShowDetails.description}</p>
            </div>
        </form>
        <!--END DETAILS-->

        <!-- Swiper -->
        <h5 class="ms-5 mt-5 mb-4">You Might Also Like</h5>
        <div class="begin-swiper mx-5">
            <div class="swiper-container mySwiper">
                <div class="swiper-wrapper">
                    <c:forEach items="${sessionScope.youMayLike}" var="a" varStatus="count">
                        <div class="swiper-slide">
                            <a href="productDetails?id=${a.productId}&size=${a.sizes.get(0).sizeId}" class="nav-link card position-relative text-decoration-none" style="width: 18rem;">
                                <img src="${a.image}" class="card-img-top img-fluid" alt="...">
                                <div class="card-body text-center">
                                    <div class="product-info-content">
                                        <p class="text-secondary">${a.origin.name}</p>
                                        <h5 class="card-title">${a.productName}</h5>
                                        <c:choose>
                                            <c:when test="${a.sale > 0}">
                                                <p class="card-text fw-bold text-danger">$${(a.sizes.get(0).price) - (a.sizes.get(0).price) * a.sale / 100} - $${(a.sizes.get(a.sizes.size() - 1).price) - a.sizes.get(a.sizes.size() - 1).price * a.sale / 100}
                                                    <span class="text-decoration-line-through text-secondary fw-light">$${a.sizes.get(0).price} - $${a.sizes.get(a.sizes.size() - 1).price}</span>
                                                </p>
                                            </c:when>
                                            <c:otherwise>
                                                <p class="card-text fw-bold text-dark">$${a.sizes.get(0).price} - $${a.sizes.get(a.sizes.size() - 1).price}</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="d-grid gap-2">
                                        <button class="btn btn-product" type="button"><i class="fas fa-plus"></i> Add to cart</button>
                                    </div>
                                </div>
                                <c:if test="${a.sale > 0}">
                                    <span class="position-absolute top-0 start-0 badge py-2 px-2 rounded bg-danger">
                                        -${a.sale}%
                                    </span>
                                </c:if>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <!--End Swiper -->
        <jsp:include page="footer.jsp"/>
    </body>
</html>
