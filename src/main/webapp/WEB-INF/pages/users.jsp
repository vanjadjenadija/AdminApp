<%@ page import="com.example.adminapp.models.User" %>
<%@ page import="com.example.adminapp.beans.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" type="com.example.adminapp.beans.UserBean" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <title>Users</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });

    </script>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-primary">
    <a class="navbar-brand" href="?action=logs" style="margin-left: 10px">Admin application</a>
    <div class="container d-flex flex-wrap">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="?action=users">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?action=categories">Categories</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="?action=logs">Logs</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?action=sign-out">Sign out</a>
            </li>
        </ul>
    </div>
</nav>
<main role="main" class="container">
    <br>
    <h1>Users</h1>
    <button type="button" class="btn btn-success" onclick="location.href='?action=addUser'">
        <span class="fa fa-plus"></span>
        Add new user
    </button>
    <hr>
    <table id="example" class="table table-striped" style="width:100%">
        <caption>Users</caption>
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Username</th>
            <th scope="col">E-mail</th>
            <th scope="col">Phone number</th>
            <th scope="col">City</th>
            <th scope="col">Avatar URL</th>
            <th scope="col">Status</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (User user : userBean.getAll()) { %>
        <tr>
            <td>
                <%=user.getId()%>
            </td>
            <td>
                <%=user.getFirstName()%>
            </td>
            <td>
                <%=user.getLastName()%>
            </td>
            <td>
                <%=user.getUsername()%>
            </td>
            <td>
                <%=user.getEmail()%>
            </td>
            <td>
                <%=user.getPhoneNumber()%>
            </td>
            <td>
                <%=user.getCity()%>
            </td>
            <td>
                <% if (user.getAvatarUrl() != null && !user.getAvatarUrl().isBlank()) { %>
                <img src="<%=user.getAvatarUrl()%>" width="16" height="16" alt="Avatar"/>
                <%}%>
            </td>
            <td>
                <%=user.getStatus()%>
            </td>
            <td>
                <div class="d-flex flex-row mb-3">
                    <div>
                        <button type="button" class="btn" onclick="location.href='?action=updateUser'">
                            <span class="fa fa-pencil text-warning"></span>
                        </button>
                    </div>
                    <div>
                        <button type="button" class="btn" onclick="location.href='?action=deleteUser'">
                            <span class="fa fa-trash text-danger"></span>
                        </button>
                    </div>
                </div>
            </td>
        </tr>
        <% } %>

        </tbody>
        <tfoot>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Username</th>
            <th scope="col">E-mail</th>
            <th scope="col">Phone number</th>
            <th scope="col">City</th>
            <th scope="col">Avatar URL</th>
            <th scope="col">Status</th>
            <th scope="col">Actions</th>
        </tr>
        </tfoot>
    </table>

</main>
</body>
</html>