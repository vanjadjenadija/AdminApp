<%@page import="com.example.adminapp.beans.LogBean" %>
<%@page import="com.example.adminapp.models.Log" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="logBean" type="com.example.adminapp.beans.LogBean" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <title>Logs</title>
</head>
<body>
<h1>Logs</h1>
<%
    for (Log log : logBean.getAll()) {
        out.println("<br/> Content: " + log.getMessage() + "<br />");
        out.println("DateTime: " + log.getDateTime() + "<br />");
        out.println("<hr />" + "<br />");
    }
%>
</body>
</html>