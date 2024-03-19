<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link href="assets/img/logo.png" rel="icon">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <style>
            body {
                background-color: #f8f9fa; /* Màu nền giống với trang profile */
            }
            .container {
                margin-top: 50px;
                background-color: #ffffff; /* Màu nền cho container */
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Đổ bóng */
                max-width: 500px;
            }
            .form-group label {
                font-weight: bold;
            }
            .btn-primary {
                background-color: #007bff; /* Màu nút chính */
                border-color: #007bff;
            }
            .btn-primary:hover {
                background-color: #0056b3; /* Màu nền khi di chuột qua nút */
                border-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form action="admin_change_user_pass" method="post">

                <input type="hidden" name="id" value="${user.id}">

                <c:if test="${noti ne null}">
                    <p style="color: green;">${notification}</p>
                </c:if>
                <c:if test="${noti ne null}">
                    <p style="color: red;">${notificationError}</p>
                </c:if>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="account">Account</label>
                            <input type="text" readonly name="account" value="${user.userName}" class="form-control" placeholder="Account">
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="newPassword">New Password</label>
                            <input type="password" name="newPassword" value="${newPassword}" class="form-control" id="newPassword" placeholder="New Password">
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="retypePassword">Re-type Password</label>
                            <input type="password" name="retypePassword" value="${retypePassword}" class="form-control" id="retypePassword" placeholder="Re-type Password">
                        </div>
                    </div>
                </div>
                <!--                <div>
                                    <img src="login" alt="CAPTCHA">
                                    <input class="input" type="text" name="captchaInput" placeholder="Enter CAPTCHA" required>
                                    <c:if test="${not empty captchaError}">
                                        <div style="color: red;">${captchaError}</div>
                                    </c:if>
                                    <button type="submit" name="refreshCaptcha" value="true">Refresh Captcha</button>
                                </div>-->


                <button type="submit" class="btn btn-primary">Update Password</button>
                <a class="btn btn-primary ml-4" href="edit_profile_user?id=${user.id}">Back</a>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.6/dist/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
    </body>
</html>
