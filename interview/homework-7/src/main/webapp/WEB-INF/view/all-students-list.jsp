<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Manager Home</title>
    </head>
    <body>
        <div align="center">
            <h1>Contact List</h1>
            <h3><a href="/newContact">New Contact</a></h3>
            <table border="1">
                <th>No</th>
                <th>Name</th>
                <th>Mark</th>

                <c:forEach var="student" items="${students}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${student.name}</td>
                    <td>${student.mark}</td>
                    <td>
                        <a href="/students/showStudentById?id=${student.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/students/deleteStudentById?id=${student.id}">Delete</a>
                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>