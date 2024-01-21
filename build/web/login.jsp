<%-- 
    Document   : login
    Created on : Jan 15, 2024, 8:24:00 PM
    Author     : yuh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="icon" href="assets/img/logo.png" type="image/png">
    </head>

    <body>
        <div class="login-form">
            <c:set var="cookie" value="${pageContext.request.cookies}"/>
            <form action="login" method="post">

                <div class="form-title">
                    Login
                </div>

                <div class="form-input">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" value="${cookie.cuser.value}">
                </div>

                <div class="form-input">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" value="${cookie.cpass.value}">
                </div>

                <div class="row mb-4">
                    <div class="col d-flex justify-content-center">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" ${(cookie.crem!=null?'checked':'')} value="OFF" name="rem" checked>
                            <label class="form-check-label" for="rem"> Remember me </label>
                        </div>
                        <div>
                            <span class="error-message">${error}</span>
                            <span class="error-message">${noti}</span>
                        </div> 
                    </div>
                </div>

                <div class="captcha">
                    <label for="captcha-input">Enter Captcha</label>
                    <div class="preview"></div>
                    <div class="captcha-form">
                        <input type="text" id="captcha-form" placeholder="Enter captcha text">
                        <button class="captcha-refresh" type="button">
                            <i class="fa fa-refresh"></i>
                        </button>
                    </div>
                </div>

                <div class="form-input">
                    <button type="submit" id="login-btn">Login</button>
                </div>
            </form>
            <div class="form-links">
                <a href="register.html">Register</a> | <a href="forgot_password">Forgot Password?</a> | <a href="home">Back</a>
            </div>
        </div>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/login.js"></script>
    </body>

</html>
