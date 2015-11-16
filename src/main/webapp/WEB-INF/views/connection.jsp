<%-- 
    Document   : hello
    Created on : Nov 13, 2015, 9:58:53 AM
    Author     : George Shumakov
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello :: Paddypower</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" >
    </head>

    <body> 
        
        <p class="bg-warning">A limitation of the Apache Tomcat will skip recording requests without 'Host' in the HTTP request header. For details see  HTTP/1.1 specification </p>
        <h1>Client request</h1>
        <p>Client ip address is <strong><span class="bg-success">${client.ip}</span></strong></p>
        <p>Client browser is <strong><span class="bg-success">${client.userAgent}</span></strong></p>
        <p>Client server time request at  <strong><span class="bg-success"><fmt:formatDate type="both" 
                        dateStyle="long" timeStyle="long" 
                        value="${client.dateTime}" /></span></strong></p>
        <hr>
        <a href="connections-list">Show all requests</a>
    </body> 
</html>
