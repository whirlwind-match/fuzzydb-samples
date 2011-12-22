<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Connect" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<h3>Connect to Facebook</h3>

<form action="<c:url value="/connect/facebook" />" method="POST">
	<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
	<div class="formInfo">
		<p>You aren't connected to Facebook yet. Click the button to connect with your Facebook account.</p>
	</div>
	<p><button type="submit"><img src="<c:url value="/resources/social/facebook/connect_light_medium_short.gif" />"/></button></p>
	<label for="postToWall"><input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about us on your Facebook wall</label>
</form>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
