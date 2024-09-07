<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"></script>
        <!-- CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/global.css">
        <script src="js/userViewOrder.js" defer></script>
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
                        <h5 class="fw-bold"> ${sessionScope.account.userName}</h5>
                    </div>
                    <div class="d-flex justify-content-center my-4">
                        <h6>Register Date: ${sessionScope.account.registerDate}</h6>
                    </div>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="viewInfor" class="text-decoration-none text-dark">View information</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="updateInfor" class="text-decoration-none text-dark">Update information</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="shippingAddress" class="text-decoration-none text-dark">Shipping address information</a>
                    </div>
                    <hr>
                    <hr>
                    <div class="my-4 ms-3">
                        <a href="viewOrder" class="text-decoration-none text-dark border-color fw-bold">View Order</a>
                    </div>
                    <hr>
                </div>
                <div class="col-9">
                    <div class="card border-card mt-4">
                        <div class="card-header">
                            <h2 class="my-2 ms-3">View Order</h2>
                        </div>
                        <div class="card-body">
                            <table class="table table-bordered border-card table-striped table-hover text-center">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Order ID</th>
                                        <th scope="col">Address</th>
                                        <th scope="col">Total price</th>
                                        <th scope="col">Order date</th>
                                        <th scope="col">Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${sessionScope.listOrderAccount}" var="a" varStatus="count">
                                        <tr>
                                            <th scope="row">${count.index+1}</th>
                                            <td><a class="text-decoration-none text-dark" href="">${a.orderId}</a></td>
                                            <td>${a.addressShip.addressName}</td>
                                            <td>$${a.totalPrice}</td>
                                            <td>${a.orderDate}</td>
                                            <td><a href="#" onclick="deleteOrder('${a.orderId}')"><button class="btn btn-dark">Delete</button></a></td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>

</html>