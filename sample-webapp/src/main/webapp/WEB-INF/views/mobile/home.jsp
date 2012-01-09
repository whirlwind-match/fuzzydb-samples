<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul data-role="listview">
	
	<li>
		<h3>Fuzzy Samples</h3>
		<p>Various examples</p>

		<ul>
			<li><a data-ajax="false" href="<c:url value="/mobile/autos/search"/>">Autos</a></li>
			<li><a data-ajax="false" href="<c:url value="/mobile/people/search"/>">People</a></li>
			<li><a data-ajax="false" href="<c:url value="/mobile/cafes/search"/>">Food joints</a></li>
		</ul>
	</li>
	<li>
	<h3>Connections</h3>
		<p>Social Connections</p>

		<ul>
			<li><a data-ajax="false" href="<c:url value="/connect"/>">Connections</a></h4></li>

			<li>Facebook
				<ul>
					<c:choose>
					<c:when test="${connectedToFacebook}">
						<li><a href="<c:url value="/facebook"/>">User Profile</a></li>
						<li><a href="<c:url value="/facebook/feed"/>">Feed</a></li>
						<li><a href="<c:url value="/facebook/friends"/>">Friends</a></li>
						<li><a href="<c:url value="/facebook/albums"/>">Albums</a></li>
					</c:when>				
					<c:otherwise>
						<li>
						<h3>Connect to Facebook</h3>
		
						<form data-ajax="false" action="<c:url value="/connect/facebook" />" method="POST">
							<input type="hidden" name="scope" value="publish_stream,user_photos,offline_access" />
							<div class="formInfo">
								<p>You aren't connected to Facebook yet. Click the button to connect with your Facebook account.</p>
							</div>
							<p><button data-role="none" type="submit"><img src="<c:url value="/resources/social/facebook/connect_light_medium_short.gif" />"/></button></p>
							<label for="postToWall">
							<input id="postToWall" type="checkbox" name="postToWall" /> Tell your friends about us on your Facebook wall</label>
						</form>
						</li>
					</c:otherwise>
					</c:choose>
				</ul>
			</li>
			<li>Twitter
				<ul>

					<c:if test="${connectedToTwitter}">
						<li><a href="<c:url value="/twitter"/>">User Profile</a></li>
						<li><a href="<c:url value="/twitter/timeline"/>">Timeline</a></li>
						<li><a href="<c:url value="/twitter/friends"/>">Friends</a></li>
						<li><a href="<c:url value="/twitter/followers"/>">Followers</a></li>
						<li><a href="<c:url value="/twitter/messages"/>">Messages</a></li>
						<li><a href="<c:url value="/twitter/trends/daily"/>">Daily Trends</a></li>
					</c:if>
					<li>
						<h4><a href="<c:url value="/twitter"/>">Twitter</a></h4>
					</li>
					
					<li>
					<form data-ajax="false" action="<c:url value="/connect/twitter" />" method="POST">
						<div class="formInfo">
							<p>
								You haven't created any connections with Twitter yet. Click the button to connect with your Twitter account. 
								(You'll be redirected to Twitter where you'll be asked to authorize the connection.)
							</p>
						</div>
						<p><button type="submit"><img src="<c:url value="/resources/social/twitter/connect-with-twitter.png" />"/></button></p>
						<label for="postTweet">
						<input id="postTweet" type="checkbox" name="postTweet" /> Post a tweet about this app</label>
					</form>
					</li>
				</ul>
			</li>
			</ul>
	</li>

</ul>
