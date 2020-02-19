<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <form:form action="processForm" modelAttribute="student">
            Name: <form:input path="name" />
            <br>
            Mark: <form:input path="mark" />
            <br>
            <a href="/students/saveStudent?id=${student.id}&name=${name}&mark=${mark}">Save</a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="/students/showAll">Cancel</a>
        </form:form>
    </body>
</html>
