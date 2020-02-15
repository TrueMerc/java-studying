<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <form:form action="processForm" modelAttribute="student">
            Name: <form:input path="name" />
            <br>
            Mark: <form:input path="mark" />
            <br>
        </form:form>
    </body>
</html>
