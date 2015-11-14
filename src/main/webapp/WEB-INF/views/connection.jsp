<%-- 
    Document   : hello
    Created on : Nov 13, 2015, 9:58:53 AM
    Author     : George Shumakov
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head> 
        <title>Hello :: Spring Application</title> 
    </head> 
    <body> 
        <h1>Hello - Spring Application</h1> 
        <p>Client ip addres is ${client.ip}</p>
        <p>Client browser is ${client.agent}</p>
        <p>Client server time request at  <fmt:formatDate type="both" 
                        dateStyle="long" timeStyle="long" 
                        value="${client.time}" /></p>
        <hr>
        <a href="connection-list">Show all requests</a>
    </body> 
</html>
