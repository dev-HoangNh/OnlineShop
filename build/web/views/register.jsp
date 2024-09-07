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
        <link rel="stylesheet" href="css/loginAndRegister.css">
        <!-- VALIDATION PLUGIN -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
        <script src="js/register.js" defer></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <!--BODY-->
        <div class="d-flex justify-content-center my-5">
            <div class="card text-header shadow" style="width: 50%;">
                <div class="card-body mb-5 mt-4">
                    <h2 class="card-title mb-4 fw-bold text-center">Register</h2>
                    <div class="d-flex justify-content-center">
                        <form id="register_form" class="w-75 mt-3" action="register" method="post">
                            <c:if test="${sessionScope.errorMessageRegister != null}">
                                <div class="alert alert-danger rounded-pill" role="alert">
                                    ${sessionScope.errorMessageRegister}
                                </div>
                            </c:if>
                            <div class="mb-4">
                                <label for="email" class="form-label fw-bold">Email address</label>
                                <input type="email" class="form-control py-3 rounded-pill" id="email" name="email" placeholder="Please enter your email">
                            </div>
                            <div class="mb-4">
                                <label for="username" class="form-label fw-bold">Username</label>
                                <input type="text" class="form-control py-3 rounded-pill" id="username" name="username" placeholder="Please enter your username">
                            </div>
                            <div class="mb-4">
                                <label for="password" class="form-label fw-bold">Password</label>
                                <input type="password" class="form-control py-3 rounded-pill" id="password" name="password" placeholder="Please enter your password">
                            </div>
                            <div class="mb-4">
                                <label for="rePassword" class="form-label fw-bold">Re-Password</label>
                                <input type="password" class="form-control py-3 rounded-pill" id="rePassword" name="rePassword" placeholder="Please enter your re-password">
                            </div>
                            <div class="d-grid gap-2">
                                <button id="register_btn" class="btn btn-color rounded-pill py-2" type="submit">Register</button>
                            </div>
                            <div class="text-center mt-2"><span>You already have an account? <a class="text-decoration-none text-header" href="login">Login</a></span></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!--END BODY-->
        <jsp:include page="footer.jsp"/>
    </body>
</html>
