<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Login" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="Spring Security Login">
	
	<h3>You'll need to login to visit that page</h3>

    <%-- this form-login-page form is also used as the
         form-error-page to ask for a login again.
         --%>
	<c:if test="${param.error eq 'bad_credentials'}">
 		<div class="error">
 			Bad user id or password<br>
 			Please try again or <a href="<c:url value="/signup" />">sign up</a>.
<!-- 	        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>. -->
		</div>
 	</c:if>
	<c:if test="${param.error eq 'multiple_users'}">
 		<div class="error">
 			Multiple local accounts are connected to the provider account.
 			Try again with a different provider or with your username and password.
 		</div>
 	</c:if>
    <c:if test="${not empty param.login_error}">
      <font color="red">Your login attempt was not successful, try again.<br/>
        Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
      </font>
    </c:if>
	<form name='f' action='login/authenticate'
		method='POST'>
    	<div>
            <label for="j_username">Email:</label>
            <input id="j_username" type='text' name='j_username' style="width:150px <c:if test="${not empty signinErrorMessage}">value="${SPRING_SECURITY_LAST_USERNAME}"</c:if>"/>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "j_username", widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Enter Your Email", required : true}})); </script>
        </div>
        <br/>
        <div>
            <label for="j_password">Password:</label>
            <input id="j_password" type='password' name='j_password' style="width:150px" />
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "j_password", widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Enter Your Password", required : true}})); </script>
        </div>
        <br/>
        <div class="submit">
            <script type="text/javascript">Spring.addDecoration(new Spring.ValidateAllDecoration({elementId:'proceed', event:'onclick'}));</script>
            <input id="proceed" type="submit" value="Submit"/>
            <input id="reset" type="reset" value="Reset"/>
        </div>
	</form>
	<p><a href="signup">Not a user, sign up here</a></p>
	
	<p>To try out, you can use:</p>
	<ul>
		<li>guest@fuzzydb.org / honoured</li>
	</ul>
	

	<!-- TWITTER SIGNIN -->
	<form id="tw_signin" action="<c:url value="/signin/twitter"/>" method="POST">
		<button type="submit"><img src="<c:url value="/resources/social/twitter/sign-in-with-twitter-d.png"/>" /></button>
	</form>

	<!-- FACEBOOK SIGNIN -->
	<form name="fb_signin" id="fb_signin" action="<c:url value="/signin/facebook"/>" method="POST">
        <input type="hidden" name="scope" value="email,user_birthday,user_location,user_checkins,publish_stream,user_photos,offline_access" />
		<button type="submit"><img src="<c:url value="/resources/social/facebook/sign-in-with-facebook.png"/>" /></button>
	</form>
</div>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
