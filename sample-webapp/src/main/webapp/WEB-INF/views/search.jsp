<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Search" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>

<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="How about searching for someone...">


<form:form action="search" method="post" >

	<table>
	<tr>
		<td>Age:</td>
		<td><form:input id="age" path="attributes['age']" /> <form:errors path="attributes['age']"/></td>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "age", 
            	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Enter their rough age...", required : false}})); </script>
	</tr>
	<tr>
		<td>Salary:</td>
		<td><form:input id="salary" path="attributes['salary']" /> <form:errors path="attributes['salary']"/></td>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "salary", 
            	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Are they raking it in?", required : false}})); </script>
	</tr>
	</table>
	<input type="submit" value="Search"/>
</form:form>

</div>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
