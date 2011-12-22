<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Disconnect" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<h3>Connected to Twitter</h3>

<form id="disconnect" method="post">
	<div class="formInfo">
		<p>This application is already connected to your Twitter account.
			Click the button if you wish to disconnect.			
	</div>

	<button type="submit">Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

<p><a href="<c:url value="/twitter" />">View your Twitter profile</a></p>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
