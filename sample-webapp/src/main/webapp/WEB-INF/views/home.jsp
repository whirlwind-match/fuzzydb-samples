<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>

<div dojoType="dijit.TitlePane" style="width: 100%" title="Home">

We've had ${count} page views.

	<ul>
		<li><a href="people/search">Person matching</a></li>
		<li><a href="cafes/search">Food establishments</a></li>
		
		<sec:authorize access="isAuthenticated()">
		<li><a href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />">Logout</a></li>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
		<li><a href="<spring:url value="/signup" htmlEscape="true" />">Sign up</a></li>
		</sec:authorize>
		
	</ul>

</div>
<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>