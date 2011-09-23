<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Sign Up" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="So, you'd like to register, eh?">


<form:form action="signup" method="post" >

	<table>
	<tr>
		<td>Email:</td>
		<td><form:input id="email" path="email" /> <form:errors path="email"/></td>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "email", 
            	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Enter Your Email (e.g. a@a.com)", required : true}})); </script>
	</tr>
	<tr>
		<td>Password:</td>
		<td><form:input id="password" path="password" /> <form:errors path="password"/></td>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "password", 
            	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "At least 6 characters", required : true}})); </script>
	</tr>
	</table>
	<input type="submit" value="Sign up"/>
</form:form>

</div>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
