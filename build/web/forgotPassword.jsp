<%-- 
    Document   : forgot
    Created on : Jan 17, 2024, 10:06:15 PM
    Author     : khail
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Forgot Password</title>
        <meta name="viewport" content="width=device-width" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/forgotPassword.css">
    </head>

    <body>
        <div class="forgot-form">
            <form action="forgot_password" method="post">

                <div class="forgot-title">
                    Forgot Password
                </div>

                <div class="forgot-input">
                    <label for="username">Enter username:</label>
                    <input type="text" id="username" placeholder="Enter your username" name="username" value="">
                    <div>
                        <span class="error-message">${error}</span>
                    </div> 
                </div>

<!--                <div class="captcha">
                    <label for="captcha-input">Enter Captcha</label>
                    <div class="preview"></div>
                    <div class="captcha-form">
                        <input type="text" id="captcha-form" placeholder="Enter captcha text">
                        <button class="captcha-refresh" type="button">
                            <i class="fa fa-refresh"></i>
                        </button>
                    </div>
                </div>-->
                <button class="forgot-input" id="forgot-btn"> Confirm</button>
                
                <a class="forgot-input" id="forgot-btn" href="login">Back</a>
            </form>
        </div>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/forgot.js"></script>
    </body>
</html>

