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
                        <a href="viewInfor" class="text-decoration-none text-dark border-color fw-bold">View information</a>
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
                        <a href="viewOrder" class="text-decoration-none text-dark">View Order</a>
                    </div>
                    <hr>
                </div>
                <div class="col-9">
                    <div class="card border-card mt-4">
                        <div class="card-header">
                            <h2 class="my-2 ms-3">View Information</h2>
                        </div>
                        <div class="card-body">
                            <div class="mx-4">
                                <div class="my-4 mt-3 row">
                                    <label class="col-sm-2 col-form-label fw-bold" for="email">Email:</label>
                                    <div class="col-sm-10">
                                        <input class="form-control-plaintext" type="text" id="email" name="email"
                                               value="${empty sessionScope.account.email ? 'Empty' : sessionScope.account.email}" style="border: none !important;" readonly>
                                    </div>
                                </div>
                                <hr>
                                <div class="my-4 row">
                                    <label class="col-sm-2 col-form-label fw-bold" for="userName">Username:</label>
                                    <div class="col-sm-10">
                                        <input class="form-control-plaintext" type="text" id="userName" name="userName"
                                               value="${empty sessionScope.account.userName ? 'Empty' : sessionScope.account.userName}" style="border: none !important;" readonly>
                                    </div>
                                </div>
                                <hr>
                                <div class="my-4 row">
                                    <label class="col-sm-2 col-form-label fw-bold" for="fullName">Full name:</label>
                                    <div class="col-sm-10">
                                        <input class="form-control-plaintext" type="text" id="fullName" name="fullName"
                                               value="${empty sessionScope.account.fullName ? 'Empty' : sessionScope.account.fullName}" style="border: none !important;" readonly>
                                    </div>
                                </div>
                                <hr>
                                <div class="my-4 row">
                                    <label class="col-sm-2 col-form-label fw-bold" for="address">Address:</label>
                                    <div class="col-sm-10">
                                        <input class="form-control-plaintext" type="text" id="address" name="address"
                                               value="${empty sessionScope.account.address ? 'Empty' : sessionScope.account.address}" style="border: none !important;" readonly>
                                    </div>
                                </div>
                                <hr>
                                <div class="my-4 row">
                                    <label class="col-sm-2 col-form-label fw-bold" for="phoneNumber">Phone number:</label>
                                    <div class="col-sm-10">
                                        <input class="form-control-plaintext" type="text" id="phoneNumber" name="phoneNumber"
                                               value="${empty sessionScope.account.phoneNumber ? 'Empty' : sessionScope.account.phoneNumber}" style="border: none !important;" readonly>
                                    </div>
                                </div>
                                <hr>
                                <div class="my-4 row">
                                    <label class="col-sm-2 col-form-label fw-bold" for="gender">Gender:</label>
                                    <div class="col-sm-10">
                                        <input class="form-control-plaintext" type="text" id="gender" name="gender"
                                               value="${empty sessionScope.account.gender ? 'Empty' : sessionScope.account.gender}" style="border: none !important;" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
