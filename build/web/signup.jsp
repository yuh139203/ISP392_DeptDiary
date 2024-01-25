<%-- 
    Document   : signup
    Created on : 20 Jan 2024, 6:44:09 pm
    Author     : ussat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <title>Sign Up</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
        <meta content="noindex, nofollow" name="robots" />
        <link rel="stylesheet" href="css/signup.css">
        <link href="assets/img/logo.png" rel="icon">
    </head>

    <body>
        <!-- Sign Up Form -->
        <section class="container mt-5" id="signup">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <form id="signupForm" method="post" action="signup">
                        <div class ="form-title">Sign Up</div>
                        <div class="form-group">
                            <label for="name">UserName</label>
                            <input class="form-control" id="name" name="name" placeholder="Enter your name" value="${username}" type="text" />
                        </div>
                        <div>
                            <span class="error-message" style="color: red;">${userNameErrorMessage}</span>                            
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" id="email" name="email" placeholder="Enter your email" value="${email}"
                                   type="email" />
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input class="form-control" id="password" name="password" placeholder="Enter your password" value="${password}"
                                   type="password" />
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password</label>
                            <input class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" value="${confirmPassword}"
                                   type="password" />
                        </div>
                        <div>
                            <span class="error-message" style="color: red;">${confirmPasswordErrorMessage}</span>                            
                        </div>
                        <div>
                            <img src="login" alt="CAPTCHA">
                            <input class="input" type="text" name="captchaInput" placeholder="Enter CAPTCHA" required>
                            <c:if test="${not empty captchaError}">
                                <div style="color: red;">${captchaError}</div>
                            </c:if>
                            <button type="submit" name="refreshCaptcha" value="true">Refresh Captcha</button>
                        </div>
                        <div>
                            <span class="error-message" style="color: red;">${error}</span>                            
                        </div>
                        <button 
                            class="btn btn-primary w-100" type="submit">Sign Up
                        </button>
                        <a href="login.jsp">Back to Login</a>
                    </form>
                    
                </div>
            </div>
        </section>
        <!-- Bootstrap & jQuery JS -->
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/signup.js"></script>
    </body>

</html>
