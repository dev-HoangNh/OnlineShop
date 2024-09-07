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
        <link rel="stylesheet" href="css/checkout.css">
        <script src="js/checkout.js" defer></script>
        <!-- VALIDATION PLUGIN -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container my-4">
            <div class="row">
                <div class="col-8">
                    <div class="mx-4">
                        <h3 class="text-header">Shipping information</h3>
                        <form id="checkout_form" action="checkout" method="post">
                            <div class="my-3">
                                <label for="fullName" class="form-label fw-bold">Enter Fullname: </label>
                                <input type="text" class="form-control rounded-pill py-3" id="fullName" name="fullName" placeholder="Fullname" value="${sessionScope.account.fullName}">
                            </div>
                            <div class="row">
                                <div class="col-6">
                                    <div class="my-3">
                                        <label for="email" class="form-label fw-bold">Enter email: </label>
                                        <input type="text" class="form-control rounded-pill py-3" id="email" name="email" placeholder="Email" value="${sessionScope.account.email}">
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="my-3">
                                        <label for="phoneNumber" class="form-label fw-bold">Enter phone number: </label>
                                        <input type="text" class="form-control rounded-pill py-3" id="phoneNumber" name="phoneNumber" placeholder="phone number" value="${sessionScope.account.phoneNumber}">
                                    </div>
                                </div>
                            </div>
                            <div class="shippingAddress rounded-4 my-3">
                                <select class="form-select form-select-lg mb-3 rounded-pill" aria-label=".form-select-lg example" name="address1">
                                    <option selected value="-1">Choose your shipping address</option>
                                    <c:forEach items="${sessionScope.addressCheckout}" var="a" varStatus="count">
                                        <option value="${a.addressId}">${a.addressName}</option>
                                    </c:forEach>
                                </select>
                                <div class="text-header my-3 fw-bold">Or enter here:</div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control rounded-pill" id="shippingAddress" placeholder="name@example.com"  name="address2">
                                    <label for="shippingAddress">Shipping address</label>
                                </div>
                            </div>
                            <div class="row mt-4">
                                <div class="col-6">
                                    <a class="fw-bold text-decoration-none" href="cart"><i class="fas fa-arrow-left me-2"></i> Back to cart</a>
                                </div>
                                <div class="col-6 d-grid">
                                    <button id="checkout_btn" type="submit" class="py-3 btn btn-color">Order</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-4">
                    <div class="ms-2">
                        <h4 class="mb-4 text-header">Summary</h4>
                        <div class="mb-3 text-header">Subtotal <i class="fa-solid fa-circle-question fa-xs ms-2"></i> <span
                                class="d-flex float-end">$${sessionScope.totalPrice}</span></div>
                        <div class="py-3 text-header border-bottom">Estimated Delivery & Handling <span
                                class="d-flex float-end">Free</span></div>
                        <div class="mt-1 py-4 border-bottom text-header">Total <span class="d-flex float-end">$${sessionScope.totalPrice}</span></div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
