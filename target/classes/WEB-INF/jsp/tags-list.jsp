<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include  page="templates/header.jsp"/>
<body class="p-3 m-0 border-0 bd-example m-0 border-0">
<jsp:include  page="templates/navbar.jsp">
    <jsp:param name="page" value="tags"/>
</jsp:include>

<h1>Create new tag</h1>
<form:form method="POST" action="/tags/add" modelAttribute="tagCreateDto">
    <div class="mb-3">
        <label for="name">Tag name</label>
        <input type="text"
               class="form-control" name="name" id="name" aria-describedby="helpId" placeholder="">
        <small id="helpId" class="form-text text-muted">Name of the tag</small>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>

<h1>List of tags</h1>
<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="tags" scope="request" type="java.util.List"/>
    <c:forEach items="${tags}" var="tag">
        <tr>
            <td>${tag.name}</td>
            <td><a class="btn btn-danger" href="/tags/${tag.id}/delete" role="button">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>