<%-- 
    Document   : signup
    Created on : Jan 15, 2024, 8:37:01 PM
    Author     : yuh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta content="width=device-width, initial-scale=1.0" name="viewport" />
        <title>Sign Up</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet" />
        <meta content="noindex, nofollow" name="robots" />
        <link rel="stylesheet" href="css/signup.css">
    </head>

    <body>
        <!-- Sign Up Form -->
        <section class="container mt-5" id="signup">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <form id="signupForm">
                        <div class ="form-title">Sign Up</div>
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input class="form-control" id="name" placeholder="Enter your name" required="" type="text" />
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input class="form-control" id="email" placeholder="Enter your email" required=""
                                   type="email" />
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input class="form-control" id="password" placeholder="Enter your password" required=""
                                   type="password" />
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password:</label>
                            <input class="form-control" id="confirmPassword" placeholder="Confirm your password" required=""
                                   type="password" />
                        </div>
                        <div class="captcha">
                            <label for="captcha">Enter Captcha:</label>
                            <div class="preview"></div>
                            <div class="input-group">
                                <input class="form-control" id="captcha" placeholder="Enter the captcha" required=""
                                       type="text" />
                                <button class="captcha-refresh">
                                    <i class="fa fa-refresh"></i>
                                </button>
                            </div>
                        </div>
                        <button class="btn btn-primary w-100" type="submit">
                            Sign Up
                        </button>
                    </form>
                </div>
            </div>
        </section>
        <!-- Bootstrap & jQuery JS -->
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/signup.js"></script>
    </body>

</html>

