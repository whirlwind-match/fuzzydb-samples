<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<li><a href="createMatt">create Matt</a></li>
		<li><a href="createMorePeople">create others</a></li>
		<li><a href="matches">Do a fuzzy query</a></li>
	</ul>
</body>
</html>
