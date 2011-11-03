<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>

<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="How about searching for someone...">


<form:form action="search" method="post" >

	<table>
	<tr>
		<td>Age:</td>
		<td><form:input id="age" path="age" /> <form:errors path="age"/></td>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "age", 
            	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Enter their rough age...", required : false}})); </script>
	</tr>
	<tr>
		<td>Salary:</td>
		<td><form:input id="salary" path="salary" /> <form:errors path="salary"/></td>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "salary", 
            	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Are they raking it in?", required : false}})); </script>
	</tr>
	<tr>
		<td>Location:</td>
		<td><form:input id="location" path="attributes['location']" /> <form:errors path="attributes['location']"/></td>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "location", 
            	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Any clue where? (Try the first part of a UK postcode e.g. SE1)", required : false}})); </script>
	</tr>
	<tr>
		<td>Newspapers:</td>
		<td><form:checkboxes id="newspapers" path="newspapers" items="${newspapers}"/> <form:errors path="newspapers"/></td>
            <script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "newspapers", 
            	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "Gets their news from... Rupert Murdoch?", required : false}})); </script>
	</tr>
	</table>
	<input type="submit" value="Search"/>
</form:form>

</div>
