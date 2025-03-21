<jsp:useBean id="message" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include  page="templates/header.jsp"/>
<body class="p-3 m-0 border-0 bd-example m-0 border-0">
<jsp:include  page="templates/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>

<div class="alert alert-danger" role="alert">
    <strong>${message}</strong>
</div>

</body>
</html>