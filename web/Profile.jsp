

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Update Profile</title>
    <link href="assets/img/logo.png" rel="icon">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
</head>
<body class="bg-light">
<div class="container mt-5">
    <h1 class="mb-4">Your Profile</h1>
    <p style="color: green;">${noti}</p>
    <form action="profile" method="post">
        
        <input type="hidden" name="id" value="${user.id}">
        
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="firstName">First Name</label>
                    <input type="text" name="firstname" value="${user.firstName}" class="form-control" id="firstName" placeholder="First Name">
                </div>
            </div>
            <div class="col-md-6">
                <div class="form-group">
                    <label for="lastName">Last Name</label>
                    <input type="text" name="lastname" value="${user.lastName}" class="form-control" id="lastName" placeholder="Last Name">
                </div>
            </div>
        </div>
        
        <div class="form-group">
            <label for="dob">Date Of Birth</label>
            <fmt:formatDate value="${user.dateOfBirth}" pattern="yyyy-MM-dd" var="formattedDate" />
            <input type="date" name="dob" value="${formattedDate}" class="form-control" id="dob" placeholder="Date Of Birth">
        </div>
        
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" name="address" value="${user.address}" class="form-control" id="address" placeholder="Address">
        </div>
        
        <div class="form-group">
            <label for="phone">Phone Number</label>
            <input type="text" name="phone" value="${user.phoneNumber}" class="form-control" id="phone" placeholder="Phone Number">
        </div>
        
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" name="email" value="${user.email}" class="form-control" id="email" placeholder="Email">
        </div>
        
        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" name="username" value="${user.userName}" class="form-control" id="username">
                </div>
            </div>
            <div class="col-md-2">
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" value="${user.passWord}" class="form-control" id="password">
                </div>
            </div>
        </div>                 
        <button type="submit" class="btn btn-primary">Update Profile</button>
        <a class="btn btn-primary ml-2" href="http://localhost:9999/ISP392_DeptDiary/welcome">Back</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.6/dist/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</body>
</html>
