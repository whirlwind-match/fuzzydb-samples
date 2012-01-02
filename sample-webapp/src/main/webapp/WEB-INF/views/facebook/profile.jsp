<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>

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

<div>
	<img src="http://graph.facebook.com/<c:out value="${profile.id}"/>/picture" align="middle"/>
</div>
	<h3>Checkins:</h3>
<div>
	<c:forEach var="checkin" items="${places.checkins}">
	<h4>${checkin.place.name} on ${checkin.createdTime}</h4>
		${checkin.place.location.latitude},${checkin.place.location.longitude}
	</c:forEach>
</div>



<c:url value="/connect/facebook" var="disconnectUrl"/>
<form id="disconnect" action="${disconnectUrl}" method="post">
	<button type="submit">Disconnect from Facebook</button>	
	<input type="hidden" name="_method" value="delete" />
</form>
