<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>

<div dojoType="dijit.TitlePane" style="width: 100%" title="Home">

<%-- We've had ${count} page views. --%>
		<sec:authorize access="isAuthenticated()">
			<p>${name}</p>
			<pre>${connectionsToProviders}</pre>
		</sec:authorize>

	<ul>
		<li><a href="autos/search">Autos</a></li>
		<li><a href="people/search">People</a></li>
		<li><a href="cafes/search" title="Food joints - will prompt for guest sign in">Food joints</a></li>

		
		<sec:authorize access="isAuthenticated()">
		<li><a href="<spring:url value="/logout" htmlEscape="true" />">Logout</a></li>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
		<li><a href="<spring:url value="/signup" htmlEscape="true" />">Sign up</a></li>
		</sec:authorize>
		
	</ul>

	<!-- TWITTER SIGNIN -->
	<form id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST">
		<button type="submit"><img src="<c:url value="/resources/social/twitter/sign-in-with-twitter-d.png"/>" /></button>
	</form>

	<!-- FACEBOOK SIGNIN -->
	<form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
        <input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
		<button type="submit"><img src="<c:url value="/resources/social/facebook/sign-in-with-facebook.png"/>" /></button>
	</form>
</div>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>