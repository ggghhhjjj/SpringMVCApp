<%-- 
    Document   : hello
    Created on : Nov 13, 2015, 9:58:53 AM
    Author     : George Shumakov
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
    <head> 
        <title>Hello :: Spring Application</title> 
    </head> 
    <body> 
        <h1>Clients report <fmt:formatDate type="date" value="${from}" /> - <fmt:formatDate type="date" value="${to}" /></h1>
        <div>
            <form action="connections-list">
                <input type="date" name="from" autocomplete="on">
                <input type="date" name="to" autocomplete="on">
                <input type="submit">
            </form>

        </div>

        <c:forEach items="${clients}" var="client">
            <div>ip: ${client.ip}, browser: ${client.agent}, date: <fmt:formatDate type="both" 
                                                                          dateStyle="long" timeStyle="long" 
                                                                          value="${client.time}" /></div>
            </c:forEach>

    </body> 
</html>
