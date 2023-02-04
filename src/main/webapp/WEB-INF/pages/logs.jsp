<%@page import="com.example.adminapp.beans.LogBean" %>
<%@page import="com.example.adminapp.models.Log" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="logBean" type="com.example.adminapp.beans.LogBean" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <title>Logs</title>
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
<%@include file="header.jsp"%>
<main role="main" class="container">
    <br>
    <h1>Logs</h1>
    <br>
    <table id="example" class="table table-striped" style="width:100%">
        <caption>Logs</caption>
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Message</th>
            <th scope="col">Level</th>
            <th scope="col">Date/Time</th>
            <th scope="col">Logger</th>
        </tr>
        </thead>
        <tbody>
        <% for (Log log : logBean.getAll()) { %>
        <tr>
            <td>
                <%=log.getId()%>
            </td>
            <td>
                <%=log.getMessage()%>
            </td>
            <td>
                <%=log.getLevel()%>
            </td>
            <td>
                <%=log.getDateTime()%>
            </td>
            <td>
                <%=log.getLogger()%>
            </td>
        </tr>
        <% } %>

        </tbody>
        <tfoot>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Message</th>
            <th scope="col">Level</th>
            <th scope="col">Date/Time</th>
            <th scope="col">Logger</th>
        </tr>
        </tfoot>
    </table>
</main>
</body>
</html>