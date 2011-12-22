<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Connect" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<h3>Connect to Twitter</h3>

<form action="<c:url value="/connect/twitter" />" method="POST">
	<div class="formInfo">
		<p>
			You haven't created any connections with Twitter yet. Click the button to connect with your Twitter account. 
			(You'll be redirected to Twitter where you'll be asked to authorize the connection.)
		</p>
	</div>
	<p><button type="submit"><img src="<c:url value="/resources/social/twitter/connect-with-twitter.png" />"/></button></p>
	<label for="postTweet"><input id="postTweet" type="checkbox" name="postTweet" /> Post a tweet about this app</label>
</form>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
