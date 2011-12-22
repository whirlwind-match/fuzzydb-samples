<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Facebook - Disconnect" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<h3>Connected to Facebook</h3>

<form id="disconnect" method="post">
	<div class="formInfo">
		<p>
			This app is connected to your Facebook account.
			Click the button if you wish to disconnect.
		</p>		
	</div>
	<button type="submit">Disconnect</button>	
	<input type="hidden" name="_method" value="delete" />
</form>

<a href="<c:url value="/facebook"/>">View your Facebook profile</a>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
