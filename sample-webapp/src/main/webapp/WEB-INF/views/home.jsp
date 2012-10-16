<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>

<div dojoType="dijit.TitlePane" style="width: 100%" title="Home">

<!-- We've had ${count} page views. -->
<!-- 
 		<sec:authorize access="isAuthenticated()">
			<p>${name}</p>
			<pre>${connectionsToProviders}</pre>
		</sec:authorize>
 -->

	<h2>Examples of a desktop site</h2>
	<ul>
		<li><a href="autos/search">Autos</a></li>
		<li><a href="people/search">People</a></li>
		<li><a href="cafes/search" title="Dining Out - will prompt for guest sign in">Dining Out</a></li>
	</ul>
	<h2>Examples of a mobile site</h2>
	<ul>
		<li><a href="mobile/autos/search">Autos</a></li>
		<li><a href="mobile/people/search">People</a></li>
		<li><a href="mobile/cafes/search" title="Dining Out - will prompt for guest sign in">Dining Out</a></li>
	</ul>
	
	<h2>Some bits imported from Spring Social that you could use</h2>
	<ul>
		<li><a href="mobile/home">Mobile root menu</a></li>
	</ul>
	
	<h2>Spring Security Example</h2>
	<ul>
		
		<sec:authorize access="isAuthenticated()">
		<li><a href="<spring:url value="/logout" htmlEscape="true" />">Logout</a></li>
		</sec:authorize>
		<sec:authorize access="!isAuthenticated()">
		<li><a href="<spring:url value="/signup" htmlEscape="true" />">Sign up</a></li>
		</sec:authorize>
	</ul>

	<c:if test="${connectedToFacebook}">
		<h4><a href="<c:url value="/facebook"/>">Facebook</a></h4>
		<li><a href="<c:url value="/facebook"/>">User Profile</a></li>
	</c:if>
</div>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>