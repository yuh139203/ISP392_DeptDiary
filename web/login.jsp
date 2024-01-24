<%-- 
    Document   : login
    Created on : Jan 15, 2024, 8:24:00 PM
    Author     : yuh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>DeptDiary</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="icon" href="assets/img/logo.png" type="image/png">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
                            <input class="form-check-input" type="checkbox" ${(cookie.crem != null ? 'checked' : '')} value="OFF" name="rem">
                            <label class="form-check-label" for="rem"> Remember me </label>
                        </div>

                        <div>
                            <span class="error-message">${error}</span> 
                            <span class="error-message">${noti}</span> 
                        </div>
                    </div>
                </div>

                <div>
                    <img src="login" alt="CAPTCHA">
                    <input class="input" type="text" name="captchaInput" placeholder="Enter CAPTCHA" required>
                    <c:if test="${not empty captchaError}">
                        <div style="color: red;">${captchaError}</div>
                    </c:if>
                    <button type="submit" name="refreshCaptcha" value="true">Refresh Captcha</button>
                </div>

                <!--<div>
                            <label for="captcha-input">Captcha</label>
                            <img src="Captcha" alt="CAPTCHA"><br>
                            <input type="text" name="captchaText" placeholder="Enter captcha text" required>
                             Hiển thị thông báo lỗi CAPTCHA 
                            <c:if test="${not empty requestScope.captchaError}">
                                <div style="color: red;">${requestScope.captchaError}</div>
                </c:if>
                        </div>-->


                <div class="form-input">
                    <button type="submit" id="login-btn">Login</button>
                </div>
            </form>
            <div class="form-links">
                <a href="signup.jsp">Register</a> | <a href="forgotPassword.jsp">Forgot Password?</a> | <a href="home.jsp">Back</a>
            </div>
        </div>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/login.js"></script>



    </body>

</html>