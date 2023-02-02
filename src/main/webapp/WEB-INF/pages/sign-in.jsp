<%@page import="com.example.adminapp.beans.AdminBean" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="error.jsp" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Sign in</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="styles/sign-in.css" rel="stylesheet">
</head>

<body class="text-center">
<form class="form-sign-in" method="post" action="?action=login">
    <img src="images/cart.svg" alt="Icon" width="60" height="60"/>
    <h1 class="h3 mb-3 font-weight-normal">Admin Application</h1>
    <label for="username" class="sr-only">Username</label>
    <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
    <button class="btn btn-lg btn-primary btn-block" type="submit" name="submit">Sign in</button>
    <h5><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%>
    </h5>
    <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
</form>
</body>
</html>