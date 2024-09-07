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
        <script src="js/adminAddSize.js" defer></script>
        <!-- VALIDATION PLUGIN -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container my-5">
            <div class="row">
                <div class="col-3 border-end py-5">
                    <div class="d-flex justify-content-center mt-3">
                        <img class="rounded-circle img-thumbnail" style="height: 150px; width: 150px;"
                             src="https://i.pinimg.com/736x/fd/aa/9d/fdaa9db62b7da804a40845083e5f368a.jpg" alt="">
                    </div>
                    <div class="d-flex justify-content-center mt-4">
                        <h5 class="fw-bold">Admin</h5>
                    </div>
                    <div class="d-flex justify-content-center my-4">
                        <h6>Have a good day!</h6>
                    </div>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="adminViewOrder" class="text-decoration-none text-dark">View Order</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="adminViewMember" class="text-decoration-none text-dark">View member</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="adminAddOrigin" class="text-decoration-none text-dark">Add origin</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="adminAddCategory" class="text-decoration-none text-dark">Add Category</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="adminAddProduct" class="text-decoration-none text-dark">Add Product</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="adminAddSize" class="text-decoration-none text-dark border-color fw-bold">Add Size for product</a>
                    </div>
                    <hr>
                </div>
                <div class="col-9">
                    <div class="card border-card mt-4">
                        <div class="card-header">
                            <h2 class="my-2 ms-3">Add Size & Price</h2>
                        </div>
                        <div class="card-body">
                            <c:if test="${not empty param.id}">
                                <h6 class="ms-5 my-3">Product ID: ${param.id}</h6>
                                <form class="mx-5" id="size_form" action="adminAddSize" method="post">
                                    <input type="hidden" name="id" value="${param.id}">
                                    <div class="mb-5 mt-3 row">
                                        <label for="size" class="col-sm-4 col-form-label fw-bold">Enter size: </label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="size" name="size">
                                        </div>
                                    </div>
                                    <div class="mb-5 mt-3 row">
                                        <label for="price" class="col-sm-4 col-form-label fw-bold">Enter price: </label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="price" name="price">
                                        </div>
                                    </div>
                                    <div class="float-end">
                                        <button id="size_btn" type="submit" class="btn btn-lg btn-dark">Add Size & Price</button>
                                    </div>
                                </form>
                            </c:if>
                            <div class="${empty param.id ? '' : 'margin-top-150'}">
                                <c:if test="${not empty param.id}">
                                    <h6>List size & price of product id: ${param.id}</h6>
                                    <table class="table table-bordered border-card table-striped table-hover text-center">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Size</th>
                                                <th scope="col">Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${sessionScope.sizes}" var="a" varStatus="count">
                                                <tr>
                                                    <th scope="row">${count.index+1}</th>
                                                    <td>${a.sizeName}</td>
                                                    <td>${a.price}</td>
                                                    <td>
                                                        <a href="#" onclick="deleteSize('${a.sizeId}', '${param.id}')" >
                                                            <button class="btn btn-dark">Delete</button>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:if>
                                <h6>List fruits: </h6>
                                <table class="table table-bordered border-card table-striped table-hover text-center">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Fruit</th>
                                            <th scope="col">Origin</th>
                                            <th scope="col">Product code</th>
                                            <th scope="col">Add Size & Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${sessionScope.products}" var="a" varStatus="count">
                                            <tr>
                                                <th scope="row">${count.index+1}</th>
                                                <td>${a.productName}</td>
                                                <td>${a.productOrigin}</td>
                                                <td>${a.productCode}</td>
                                                <td><a href="adminAddSize?id=${a.productId}"><button class="btn btn-dark">Add</button></a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
