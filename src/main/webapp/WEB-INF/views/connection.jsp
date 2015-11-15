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
        <title>Hello :: Paddypower</title> 
    </head>
    <style>
        .allert {color: red; font-weight: bold;}
        .marked {font-weight: bold;}
    </style>
    <body> 
        <h1>Client connection</h1>
        <div class="allert">A limitation of the Apache Tomcat will skip recording requests without 'Host' in the HTTP request header. For details see  HTTP/1.1 specification </div>
        <p>Client ip address is <span class="marked">${client.ip}</span></p>
        <p>Client browser is <span class="marked">${client.userAgent}</span></p>
        <p>Client server time request at  <span class="marked"><fmt:formatDate type="both" 
                        dateStyle="long" timeStyle="long" 
                        value="${client.dateTime}" /></span></p>
        <hr>
        <a href="connections-list">Show all requests</a>
    </body> 
</html>
