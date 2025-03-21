<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="dev.cavefish.minipost.controller.JspHelper" %>
<jsp:useBean id="posts" scope="request" type="java.util.List"/>
<jsp:useBean id="users" scope="request" type="java.util.List"/>
<html>
<jsp:include page="templates/header.jsp"/>
<body class="p-3 m-0 border-0 bd-example m-0 border-0">
<jsp:include page="templates/navbar.jsp">
    <jsp:param name="page" value="users"/>
</jsp:include>

<h1>Create new post</h1>
<%--@elvariable id="userDto" type="dev.cavefish.minipost.dto.UserDto"--%>
<form:form method="POST" action="/posts/add" modelAttribute="userDto">
    <div class="mb-3">
        <label for="createdByAlias">Created by</label>
        <select class="form-select" id="createdByAlias" name="createdByAlias">
            <c:forEach items="${users}" var="user">
                <option value="${user.alias}">${user.lastName}, ${user.firstName} (${user.alias})</option>
            </c:forEach>
        </select>
        <small class="form-text text-muted">User that created the post</small>
    </div>
    <div class="mb-3">
        <label for="tags">Tags</label>
        <input type="text"
               class="form-control" name="tags" id="tags" placeholder="">
    </div>
    <div class="mb-3">
        <label for="title">Post title</label>
        <input type="text"
               class="form-control" name="title" id="title" placeholder="">
    </div>
    <div class="mb-3">
        <label for="content">Post content</label>
        <input type="text"
               class="form-control" name="content" id="content" placeholder="">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form:form>

<h1>List of posts</h1>
<form:form method="GET" action="/posts">
    <div class="mb-3">
        <label for="searchCreatedByAlias">Created by</label>
        <select class="form-select" id="searchCreatedByAlias" name="createdByAlias">
            <option value="">Any</option>
            <c:forEach items="${users}" var="user">
                <option value="${user.alias}">${user.lastName}, ${user.firstName} (${user.alias})</option>
            </c:forEach>
        </select>
        <small class="form-text text-muted">User that created the post</small>
    </div>
    <div class="mb-3">
        <label for="searchTags">Tags</label>
        <input type="text"
               class="form-control" name="tags" id="searchTags" placeholder="">
    </div>
    <div class="mb-3">
        <label for="searchFromDate">From</label>
        <input type="datetime-local"
               class="form-control" name="fromDate" id="searchFromDate" placeholder="">
    </div>
    <div class="mb-3">
        <label for="searchToDate">To</label>
        <input type="datetime-local"
               class="form-control" name="toDate" id="searchToDate" placeholder="">
    </div>
    <button type="submit" class="btn btn-primary">Search</button>
</form:form>


<table class="table">
    <thead>
    <tr>
        <th>Created by</th>
        <th>Date</th>
        <th>Tags</th>
        <th>Title</th>
        <th>Content</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${posts}" var="post">
        <tr>
            <td>${post.createdBy.alias}</td>
            <td>${post.createdAt}</td>
            <td>${JspHelper.MapAndConcat(post.tags, it -> it.name)}</td>
            <td>${post.title}</td>
            <td>${post.content}</td>
            <td><a class="btn btn-danger" href="/posts/${post.id}/delete" role="button">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>