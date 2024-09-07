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
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <!--BODY-->
        <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="true">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                        aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                        aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                        aria-label="Slide 3"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="3"
                        aria-label="Slide 4"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="images/slide_4_img.webp" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="images/slide_1_img.webp" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="images/slide_3_img.webp" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                    <img src="images/slide_2_img.webp" class="d-block w-100" alt="...">
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
                    data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
                    data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <!--END BODY-->
        <!--PRODUCT-->
        <div class="container">
            <div id="flashSale" class="mx-4 my-5">
                <h3 class="text-header mt-4"><i class="fas fa-percent"></i> Flash Sale</h3>
                <div class="row mt-4">
                    <c:forEach items="${sessionScope.productFlashSale}" var="a" varStatus="count">
                        <div class="col-3">
                            <a href="productDetails?id=${a.productId}&size=${a.sizes.get(0).sizeId}" class="card position-relative text-decoration-none" style="width: 18rem;">
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

            <div class="bg-light pb-1 pt-2">
                <div class="text-center my-3">
                    <h6>Free Delivery</h6>
                    <span>Applies to orders of 1.000.000vn₫ or more.</span>
                </div>
            </div>

            <div id="bestSale" class="mx-4 my-5">
                <h3 class="text-header mt-4"><i class="fas fa-exclamation"></i> Top Sale</h3>
                <div class="row mt-4">
                    <c:forEach items="${sessionScope.productTopSelling}" var="a" varStatus="count">
                        <div class="col-3">
                            <a href="productDetails?id=${a.productId}&size=${a.sizes.get(0).sizeId}" class="card position-relative text-decoration-none" style="width: 18rem;">
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
        <!--END PRODUCT-->
        <jsp:include page="footer.jsp"/>
    </body>
</html>
