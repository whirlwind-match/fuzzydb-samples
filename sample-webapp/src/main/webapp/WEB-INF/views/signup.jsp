<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Sign up</title>
</head>
<body>
<h1>
	So, you'd like to register, eh?
</h1>

<form:form action="signup" method="post" >

	<table>
	<tr>
		<td>Email:</td>
		<td><form:input path="email" /> <form:errors path="email"/></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><form:input path="password" /> <form:errors path="password"/></td>
	</tr>
	</table>
	<input type="submit" value="Sign up"/>
</form:form>


</body>
</html>
