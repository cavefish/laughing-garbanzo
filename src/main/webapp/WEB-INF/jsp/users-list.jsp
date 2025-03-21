<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include  page="templates/header.jsp"/>
<body class="p-3 m-0 border-0 bd-example m-0 border-0">
<jsp:include  page="templates/navbar.jsp">
    <jsp:param name="page" value="users"/>
</jsp:include>

<h1>Create new user</h1>
<%--@elvariable id="userDto" type="dev.cavefish.minipost.dto.UserDto"--%>
<form:form method="POST" action="/users/add" modelAttribute="userCreateDto">
    <div class="mb-3">
        <label for="alias">User alias</label>
        <input type="text"
               class="form-control" name="alias" id="alias" placeholder="">
        <small class="form-text text-muted">Alias of the user</small>
    </div>
    <div class="mb-3">
        <label for="password">Password</label>
        <input type="password"
               class="form-control" name="password" id="password" placeholder="">
        <small class="form-text text-muted">Password of the user</small>
    </div>
    <div class="mb-3">
        <label for="email">User email</label>
        <input type="text"
               class="form-control" name="email" id="email" placeholder="">
        <small class="form-text text-muted">EMail of the user</small>
    </div>
    <div class="mb-3">
        <label for="firstName">User First Name</label>
        <input type="text"
               class="form-control" name="firstName" id="firstName" placeholder="">
        <small class="form-text text-muted">First name of the user</small>
    </div>
    <div class="mb-3">
        <label for="lastName">User Last Name</label>
        <input type="text"
               class="form-control" name="lastName" id="lastName" placeholder="">
        <small class="form-text text-muted">Last name of the user</small>
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>

<h1>List of users</h1>
<table class="table">
    <thead>
    <tr>
        <th>Alias</th>
        <th>EMail</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.alias}</td>
            <td>${user.email}</td>
            <td>${user.lastName}, ${user.firstName}</td>
            <td><a class="btn btn-danger" href="/users/${user.alias}/delete" role="button">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>