<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Login" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<h3>Your Facebook Profile</h3>
<p>Hello, <c:out value="${profile.firstName}"/>!</p>
<dl>
	<dt>Facebook ID:</dt>
	<dd><c:out value="${profile.id}"/></dd>
	<dt>Name:</dt>
	<dd><c:out value="${profile.name}"/></dd>
	<dt>Email:</dt>
	<dd><c:out value="${email}"/></dd>
	<dt>Gender:</dt>
	<dd><c:out value="${profile.gender}"/></dd>
	<dt>Birthday:</dt>
	<dd><c:out value="${profile.birthday}"/></dd>
	<dt>Relationship Status:</dt>
	<dd><c:out value="${profile.relationshipStatus}"/></dd>
</dl>

<img src="http://graph.facebook.com/<c:out value="${profile.id}"/>/picture" align="middle"/>

<c:url value="/connect/facebook" var="disconnectUrl"/>
<form id="disconnect" action="${disconnectUrl}" method="post">
	<button type="submit">Disconnect from Facebook</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
