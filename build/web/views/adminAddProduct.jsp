g<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="js/adminAddProduct.js" defer></script>
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
                        <a href="adminAddProduct" class="text-decoration-none text-dark border-color fw-bold">Add Product</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="adminAddSize" class="text-decoration-none text-dark">Add Size for product</a>
                    </div>
                    <hr>
                </div>
                <div class="col-9">
                    <div class="card border-card mt-4">
                        <div class="card-header">
                            <h2 class="my-2 ms-3">Add Product</h2>
                        </div>
                        <div class="card-body">
                            <form class="mx-5" id="product_form" action="adminAddProduct" method="post">
                                <div class="mb-5 mt-3 row">
                                    <label for="fruitName" class="col-sm-4 col-form-label fw-bold">Enter fruit name: </label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="fruitName" name="fruitName">
                                    </div>
                                </div>
                                <div class="mb-5 mt-3 row">
                                    <label for="fruitcode" class="col-sm-4 col-form-label fw-bold">Enter fruit code: </label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="fruitcode" name="fruitcode">
                                    </div>
                                </div>
                                <div class="mb-5 mt-3 row">
                                    <label for="status" class="col-sm-4 col-form-label fw-bold">Enter status: </label>
                                    <div class="col-sm-8">
                                        <select class="form-select text-center" aria-label=".form-select-lg example" id="status"
                                                name="status">
                                            <option selected>Status</option>
                                            <option class="py-2" value="true">Stock</option>
                                            <option class="py-2" value="false">Sold-out</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-5 mt-3 row">
                                    <label for="sale" class="col-sm-4 col-form-label fw-bold">Enter sale: </label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="sale" name="sale">
                                    </div>
                                </div>
                                <div class="mb-5 mt-3 row">
                                    <label for="description" class="col-sm-4 col-form-label fw-bold">Enter description for fruits: </label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="description" name="description">
                                    </div>
                                </div>
                                <div class="mb-5 mt-3 row">
                                    <label for="image" class="col-sm-4 col-form-label fw-bold">Enter url image for fruits: </label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="image" name="image">
                                    </div>
                                </div>
                                <div class="mb-5 mt-3 row">
                                    <label for="categoryId" class="col-sm-4 col-form-label fw-bold">Choose category for fruit: </label>
                                    <div class="col-sm-8">
                                        <select class="form-select text-center" aria-label=".form-select-lg example" id="categoryId"
                                                name="categoryId">
                                            <option selected>Category</option>
                                            <c:forEach items="${sessionScope.categories}" var="a">
                                                <option class="py-2" value="${a.id}">${a.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-5 mt-3 row">
                                    <label for="originID" class="col-sm-4 col-form-label fw-bold">Choose origin for fruit: </label>
                                    <div class="col-sm-8">
                                        <select class="form-select text-center" aria-label=".form-select-lg example" id="originID"
                                                name="originID">
                                            <option selected>Origin</option>
                                            <c:forEach items="${sessionScope.origins}" var="a">
                                                <option class="py-2" value="${a.id}">${a.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="mb-5 mt-3 row">
                                    <label for="type" class="col-sm-4 col-form-label fw-bold">Choose type for fruit: </label>
                                    <div class="col-sm-8">
                                        <select class="form-select text-center" aria-label=".form-select-lg example" id="type"
                                                name="type">
                                            <option selected>Type</option>
                                            <option class="py-2" value="gift">Gift</option>
                                            <option class="py-2" value="fruit">Fruit</option>
                                            <option class="py-2" value="daily">Daily</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="float-end mb-3">
                                    <button id="product_btn" type="submit" class="btn btn-lg btn-dark">Add Product</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
