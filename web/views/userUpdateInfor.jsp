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
        <script src="js/updateInfor.js" defer></script>
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
                        <a href="updateInfor" class="text-decoration-none text-dark border-color fw-bold">Update information</a>
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
                            <h2 class="my-2 ms-3">Update Information</h2>
                        </div>
                        <div class="card-body">
                            <form class="mx-4" id="updateProfile_form" action="updateInfor" method="post">
                                <div class="mb-5 mt-3 row">
                                    <label for="username" class="col-sm-2 col-form-label fw-bold">User name:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="username" name="username">
                                    </div>
                                </div>
                                <div class="mb-5 row">
                                    <label for="fullName" class="col-sm-2 col-form-label fw-bold">Full name:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="fullName" name="fullName">
                                    </div>
                                </div>
                                <div class="mb-4 row">
                                    <label for="gender" class="col-sm-2 col-form-label fw-bold">Gender:</label>
                                    <div class="col-sm-10">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" id="inlineCheckbox1" checked name="gender"
                                                   value="Male">
                                            <label class="form-check-label" for="inlineCheckbox1">Male</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" id="inlineCheckbox2" name="gender" value="Female">
                                            <label class="form-check-label" for="inlineCheckbox2">Female</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="mb-5 row">
                                    <label for="address" class="col-sm-2 col-form-label fw-bold">Address:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="address" name="address">
                                    </div>
                                </div>
                                <div class="mb-5 row">
                                    <label for="phoneNumber" class="col-sm-2 col-form-label fw-bold">Phone number:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber">
                                    </div>
                                </div>
                                <div class="float-end mb-3">
                                    <button id="updateProfile_btn" type="submit" class="btn btn-lg btn-dark">Update</button>
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
