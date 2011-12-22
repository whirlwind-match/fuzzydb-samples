<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>

<div dojoType="dijit.TitlePane" style="width: 100%" title="Home">

<%-- We've had ${count} page views. --%>

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

</div>
<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>