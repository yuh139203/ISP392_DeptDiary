<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>List of Users</title>
        <link href="assets/img/logo.png" rel="icon">
        <link href="css/listUser.css" rel="stylesheet">
    </head>
    <body>
           
        <h1 class="title">List of users</h1>
        <p style="color: green;">${noti}</p>
        <c:set var="page" value="${page}" />

        <table >
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Date of Birth</th>
                    <th>Phone Number</th>
                    <th>Email</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${data}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.dateOfBirth}</td>
                        <td>${user.phoneNumber}</td>
                        <td>${user.email}</td>
                        <td>${user.address}</td>
                        <td class="action-buttons" >
                            <form action="edit_profile_user" method="get">
                                <input type="hidden" name="id" value="${user.id}">
                                <button type="submit" class="update-button">Update</button>
                            </form>
                            <form action="delete_user_account" method="post">
                                <input type="hidden" name="id" value="${user.id}">
                                <button type="submit" class="delete-button" >Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="pagination"> 
            <c:forEach begin="${1}" end="${num}" var="i">
                <a class="${i==page?"active":""}" href="list_user?page=${i}">${i}</a>
            </c:forEach>
        </div>
        <a href="admin?id= ${sessionScope.userLogin.id}" class="back-button"><button>Back</button></a>
    </body>
</html>
