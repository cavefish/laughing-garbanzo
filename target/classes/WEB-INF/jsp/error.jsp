<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include  page="templates/header.jsp"/>
<body class="p-3 m-0 border-0 bd-example m-0 border-0">
<jsp:include  page="templates/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>


<% if(response.getStatus() == 500){ %>
<div class="alert alert-danger" role="alert">
    <strong><%=exception.getMessage() %></strong>
</div>
<%}else {%>
<div class="alert alert-danger" role="alert">
    <strong>Hi There, error code is <%=response.getStatus() %></strong>
</div>
<%} %>
</body>
</html>