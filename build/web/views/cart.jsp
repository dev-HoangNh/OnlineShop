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
        <script src="js/cart.js" defer></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container my-5">
            <div class="row">
                <div class="col-8">
                    <div class="toast align-items-center show w-100" role="alert" aria-live="assertive" aria-atomic="true">
                        <div class="d-flex">
                            <div class="toast-body">
                                <p class="text-uppercase fs-6">Free delivery</p>
                                <div>Applies to orders of 1,000,000đ or more. <span><a href="#" class="text-secondary">View
                                            details</a></span></div>
                            </div>
                            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                        </div>
                    </div>
                    <h5 class="my-4">Bag</h5>
                    <c:forEach items="${sessionScope.cartProducts}" var="a" varStatus="count">
                        <!--ITEM-->
                        <div class="row border-bottom pb-4 mt-4">
                            <div class="col-2 item-no-padding">
                                <img src="${a.product.image}" class="img-fluid" alt="">
                            </div>
                            <div class="col-10">
                                <div class="ps-4">
                                    <div class="fw-bold mb-2">${a.product.productName} <span id="price_${a.cartId}" class="d-flex float-end">$${a.quantity * (a.size.price - a.size.price * a.product.sale / 100)}</span></div>
                                    <div class="mb-1">${a.product.origin.name}</div>
                                    <div class="mb-3">${a.product.productCode}</div>
                                    <div class="row">
                                        <div class="col-4 row item-no-padding">
                                            <div class="col-4 item-no-padding"><label>Size</label></div>
                                            <div class="col-8 item-no-padding">
                                                <div class="input-group mb-3">
                                                    <input readonly type="text" readonly id="size_${a.cartId}" class="form-control text-center" value="${a.size.sizeName}"
                                                           aria-label="size">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-4 row item-no-padding ms-4">
                                            <div class="col-4 item-no-padding"><label>Quantity</label></div>
                                            <div class="col-8 item-no-padding">
                                                <div class="input-group mb-3">
                                                    <input readonly type="text" id="quantity_${a.cartId}" class="form-control text-center" min="1" value="${a.quantity}"
                                                           aria-label="quantity">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="mt-3">
                                        <a class="text-dark text-decoration-none" href="#"><i class="fas fa-heart fa-lg me-3"></i></a>
                                        <a class="text-dark text-decoration-none" href="#" onclick="deleteCart('${a.cartId}')"><i class="fas fa-trash fa-lg"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--END ITEM-->
                    </c:forEach>
                </div>
                <div class="col-4">
                    <div class="ms-2">
                        <h4 class="mb-4 text-header">Summary</h4>
                        <div class="mb-3 text-header">Subtotal <i class="fa-solid fa-circle-question fa-xs ms-2"></i> <span
                                class="d-flex float-end">$${sessionScope.totalPrice}</span></div>
                        <div class="py-3 text-header border-bottom">Estimated Delivery & Handling <span
                                class="d-flex float-end">Free</span></div>
                        <div id="totalPrice" class="mt-1 py-4 border-bottom text-header">Total <span class="d-flex float-end">$${sessionScope.totalPrice}</span></div>
                        <div class="d-grid mt-3">
                            <a href="checkout" type="button" class="btn btn-color mt-3 py-3 rounded-pill">Checkout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
