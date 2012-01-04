<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul data-role="listview">
	
	<li>
		<h3>Fuzzy Samples</h3>
		<p>Various examples</p>

		<ul>
			<li><a data-ajax="false" href="autos/search">Autos</a></li>
<!-- 			<li><a data-ajax="false" href="people/search">People</a></li> -->
<!-- 			<li><a data-ajax="false" href="cafes/search" title="Food joints - will prompt for guest sign in">Food joints</a></li> -->
		</ul>
	</li>
	<li>
	<h3>Connections</h3>
		<p>Social Connections</p>

		<ul>
			<li><a data-ajax="false" href="<c:url value="/connect"/>">Connections</a></h4></li>

			<li>Twitter
				<h4><a href="<c:url value="/twitter"/>">Twitter</a></h4>
				<ul>

					<c:if test="${connectedToTwitter}">
						<li><a href="<c:url value="/twitter"/>">User Profile</a></li>
						<li><a href="<c:url value="/twitter/timeline"/>">Timeline</a></li>
						<li><a href="<c:url value="/twitter/friends"/>">Friends</a></li>
						<li><a href="<c:url value="/twitter/followers"/>">Followers</a></li>
						<li><a href="<c:url value="/twitter/messages"/>">Messages</a></li>
						<li><a href="<c:url value="/twitter/trends/daily"/>">Daily Trends</a></li>
					</c:if>
				</ul>
			</li>
			</ul>
			
<%-- <h4><a href="<c:url value="/facebook"/>">Facebook</a></h4> --%>
<%-- <c:if test="${connectedToFacebook}"> --%>
<!-- <ul class="menu"> -->
<%-- 	<li><a href="<c:url value="/facebook"/>">User Profile</a></li> --%>
<%-- 	<li><a href="<c:url value="/facebook/feed"/>">Feed</a></li> --%>
<%-- 	<li><a href="<c:url value="/facebook/friends"/>">Friends</a></li> --%>
<%-- 	<li><a href="<c:url value="/facebook/albums"/>">Albums</a></li> --%>
<!-- </ul> -->
<%-- </c:if> --%>
	
	</li>

</ul>
