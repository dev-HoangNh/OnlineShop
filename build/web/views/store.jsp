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
        <!--PRODUCT-->
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <div class="accordion" id="accordionPanelsStayOpenExample">
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true"
                                        aria-controls="panelsStayOpen-collapseOne">
                                    Product Categories
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show"
                                 aria-labelledby="panelsStayOpen-headingOne">
                                <div class="accordion-body">
                                    <c:forEach items="${sessionScope.storeCategories}" var="a" varStatus="count">
                                        <div><a href="store?category=${a.id}" class="nav-link mb-3">${a.name}</a></div>
                                        </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingTwo">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="true"
                                        aria-controls="panelsStayOpen-collapseTwo">
                                    Country of Origin
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse show"
                                 aria-labelledby="panelsStayOpen-headingTwo">
                                <div class="accordion-body">
                                    <c:forEach items="${sessionScope.storeOrigins}" var="a" varStatus="count">
                                        <div><a href="store?origin=${a.id}" class="nav-link mb-3">${a.name}</a></div>
                                        </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingThree">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                        data-bs-target="#panelsStayOpen-collapseThree" aria-expanded="true"
                                        aria-controls="panelsStayOpen-collapseThree">
                                    Price Filtering
                                </button>
                            </h2>
                            <div id="panelsStayOpen-collapseThree" class="accordion-collapse collapse show"
                                 aria-labelledby="panelsStayOpen-headingThree">
                                <div class="accordion-body">
                                    <div><a href="store?min=0&max=5" class="nav-link mb-3">Under $5</a></div>
                                    <div><a href="store?min=5&max=10" class="nav-link mb-3">$5 - $10</a></div>
                                    <div><a href="store?min=10&max=20" class="nav-link mb-3">$10 - $20</a></div>
                                    <div><a href="store?min=20&max=50" class="nav-link mb-3">$20 - $50</a></div>
                                    <div><a href="store?min=50&max=${Float.MAX_VALUE}" class="nav-link mb-3">Over $50</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-9">
                    <img src="/images/slide_4_img.webp" class="img-fluid" alt="">
                    <div class="row my-4">
                        <div class="col-6">
                            <h2 class="text-header">Fruit <span class="fs-6 ms-3">${sessionScope.totalProductSize} products</span></h2>
                        </div>
                        <div class="col-6 d-flex justify-content-end">
                            <div class="dropdown">
                                <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    <i class="fas fa-sort-numeric-down"></i> Sorting
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end">
                                    <li><a class="dropdown-item text-header" href="store?sort=1">Price: Low to High</a></li>
                                    <li><a class="dropdown-item text-header" href="store?sort=2">Price: High to Low</a></li>
                                    <li><a class="dropdown-item text-header" href="store?sort=3">Name: A-Z</a></li>
                                    <li><a class="dropdown-item text-header" href="store?sort=4">Name: Z-A</a></li>
                                    <li><a class="dropdown-item text-header" href="store?sort=5">Bestselling</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-4">
                        <c:forEach items="${sessionScope.storeProducts}" var="a" varStatus="count">
                            <div class="col-4 mb-4">
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
                    <c:if test="${empty param.type && empty param.category && empty param.origin && empty param.min && empty param.sort}">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                    <a class="page-link text-header" href="store?page=${currentPage - 1}">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${totalPageCount}" var="pageNumber">
                                    <li class="page-item ${currentPage == pageNumber ? 'active' : ''}">
                                        <a class="page-link text-header" href="store?page=${pageNumber}">${pageNumber}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${currentPage == totalPageCount ? 'disabled' : ''}">
                                    <a class="page-link text-header" href="store?page=${currentPage + 1}">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                    <c:if test="${param.type eq 'gift' && empty param.category && empty param.origin && empty param.min && empty param.sort}">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                    <a class="page-link text-header" href="store?type=gift&page=${currentPage - 1}">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${totalPageCount}" var="pageNumber">
                                    <li class="page-item ${currentPage == pageNumber ? 'active' : ''}">
                                        <a class="page-link text-header" href="store?type=gift&page=${pageNumber}">${pageNumber}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${currentPage == totalPageCount ? 'disabled' : ''}">
                                    <a class="page-link text-header" href="store?type=gift&page=${currentPage + 1}">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                    <c:if test="${param.type eq 'fruit' && empty param.category && empty param.origin && empty param.min && empty param.sort}">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                    <a class="page-link text-header" href="store?type=fruit&page=${currentPage - 1}">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${totalPageCount}" var="pageNumber">
                                    <li class="page-item ${currentPage == pageNumber ? 'active' : ''}">
                                        <a class="page-link text-header" href="store?type=fruit&page=${pageNumber}">${pageNumber}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${currentPage == totalPageCount ? 'disabled' : ''}">
                                    <a class="page-link text-header" href="store?type=fruit&page=${currentPage + 1}">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                    <c:if test="${param.type eq 'daily' && empty param.category && empty param.origin && empty param.min && empty param.sort}">
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                    <a class="page-link text-header" href="store?type=daily&page=${currentPage - 1}">Previous</a>
                                </li>
                                <c:forEach begin="1" end="${totalPageCount}" var="pageNumber">
                                    <li class="page-item ${currentPage == pageNumber ? 'active' : ''}">
                                        <a class="page-link text-header" href="store?type=daily&page=${pageNumber}">${pageNumber}</a>
                                    </li>
                                </c:forEach>
                                <li class="page-item ${currentPage == totalPageCount ? 'disabled' : ''}">
                                    <a class="page-link text-header" href="store?type=daily&page=${currentPage + 1}">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </c:if>
                </div>
            </div>
        </div>
        <!--END PRODUCT-->
        <jsp:include page="footer.jsp"/>
    </body>
</html>
