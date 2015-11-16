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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello :: Paddypower</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" >
    </head> 
    <body>
        <a href="connection">Back to connection</a>
        <h1>Clients HTTP requests report
            <c:out value="${empty from ? '' : ' from '}" />
            <fmt:formatDate type="date" value="${from}" /> 
            <c:out value="${empty to ? '' : ' to '}" />
            <fmt:formatDate type="date" value="${to}" /></h1>
        <div  class="container-fluid">
                <form class="form-inline" action="connections-list">
                    <div class="form-group">
                        <label for="fromLabel">Starting date</label>
                        <input type="date" class="form-control" name="from" id="fromLabel" autocomplete="on">
                    </div>
                    <div class="form-group">
                        <label for="toLabel">Ending date</label>
                        <input type="date" class="form-control" name="to" id="toLabel" autocomplete="on">
                    </div>
                    <input type="submit"  class="btn btn-primary">
                </form>
        </div>
        <div  class="container-fluid">
            <table class="table table-condensed table-hover table-bordered">
                <tr>
                    <th>ip</th>
                    <th>browser</th>
                    <th>datetime</th>
                </tr>
                <c:forEach items="${clients}" var="client">
                    <tr>
                        <td>${client.ip}</td> 
                        <td>${client.userAgent}</td>
                        <td><fmt:formatDate type="both" 
                                        dateStyle="long" timeStyle="long" 
                                        value="${client.dateTime}" /></td>
                    </tr>
                </c:forEach>
            </table>
    </body> 
</html>
