<%@ taglib prefix="sample" tagdir="/WEB-INF/tags" %>
<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Sign Up" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="So, you'd like to register, eh?">


<c:if test="${not empty message}">
	<div class="${message.type.cssClass}">${message.text}</div>
</c:if>

<form:form action="signup" method="post" modelAttribute="signupForm" >

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
	<tr>
		<td>First Name:</td>
		<td><sample:edit-input attrName="firstName" popupHelp="Enter Your first name (optional)"/></td>
	</tr>
	<tr>
		<td>Last Name:</td>
		<td><sample:edit-input attrName="lastName" popupHelp="Enter your last name (optional)"/></td>
	</tr>
	</table>
	<input type="submit" value="Sign up"/>
</form:form>

</div>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
