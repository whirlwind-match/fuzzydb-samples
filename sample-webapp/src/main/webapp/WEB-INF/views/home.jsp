<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!
</h1>

We've had ${count} page views.


	<ul>
		<li><a href="createPeople">create a few characters</a></li>
		<li><a href="matches">Do a fuzzy query</a></li>
		
		<sec:authorize access="isAuthenticated()">
		<li><a href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />">Logout</a></li>
		</sec:authorize>
	</ul>
</body>
</html>
