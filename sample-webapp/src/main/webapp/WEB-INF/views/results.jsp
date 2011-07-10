<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>${heading}</title>
</head>
<body>
<h1>
	${heading}
</h1>

	<ul>
		<li><a href="createMatt">create Matt</a></li>
		<li><a href="createMorePeople">create others</a></li>
		<li><a href="mattsMatches">Do a fuzzy query</a></li>
	</ul>


	<pre>
	${results}
	</pre>

</body>
</html>
