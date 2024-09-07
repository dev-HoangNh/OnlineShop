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
                        <a href="adminViewOrder" class="text-decoration-none text-dark border-color fw-bold">View Order</a>
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
                        <a href="adminAddSize" class="text-decoration-none text-dark">Add Size for product</a>
                    </div>
                    <hr>
                </div>
                <div class="col-9">
                    <div class="card border-card mt-4">
                        <div class="card-header">
                            <h2 class="my-2 ms-3">View Order</h2>
                        </div>
                        <div class="card-body">
                            <c:forEach items="${sessionScope.listOrderAdmin}" var="a">
                                <div class="border-bottom" style="border-color: #3A1562 !important; border-bottom-width: 2px !important">
                                    <h6 class="my-3">Order ID: ${a.orderId}</h6>
                                    <h6 class="my-3">Customer name: ${a.account.fullName}, Customer phone number: ${a.account.phoneNumber}, Customer address:  ${a.addressShip.addressName}</h6>
                                    <h6 class="my-3">Total price: $${a.totalPrice}</h6>
                                    <table class="table table-bordered border-card table-striped table-hover text-center">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Product</th>
                                                <th scope="col">Quantity</th>
                                                <th scope="col">Size</th>
                                                <th scope="col">Unit price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${a.orderDetails}" var="details" varStatus="count">
                                                <tr>
                                                    <th scope="row">${count.index+1}</th>
                                                    <td><a class="text-decoration-none text-dark" href="">${details.product.productName}</a></td>
                                                    <td>${details.quantity}</td>
                                                    <td>$${details.size.sizeName}</td>
                                                    <td>$${details.unitPrice}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
