<%-- 
    Document   : hello
    Created on : Nov 13, 2015, 9:58:53 AM
    Author     : George Shumakov
--%>

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
        <p>Client server time request at ${client.time}</p>
    </body> 
</html>
