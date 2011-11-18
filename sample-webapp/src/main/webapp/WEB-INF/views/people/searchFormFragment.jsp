<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<%@ taglib prefix="sample" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="How about searching for someone...">


<form:form action="search" method="post" >

	<table>
	<tr>
		<td>Age:</td>
		<td><sample:edit-input attrName="age" popupHelp="Enter their rough age..." /></td>
	</tr>
	<tr>
		<td>Salary:</td>
		<td><sample:edit-input attrName="salary" popupHelp="Are they raking it in?" /></td>
	</tr>

	<tr>
		<td>Smoke: </td>  
		<td><form:radiobuttons path="smoke" items="${smokeOptions}" /></td>
	</tr>
	<tr>
		<td>Location:</td>
		<td><sample:edit-input attrName="postcode" popupHelp="Any clue where? (Try the first part of a UK postcode e.g. SE1)" /></td>
	</tr>
	<tr>
		<td>Newspapers:</td>
		<td><form:checkboxes id="newspapers" path="newspapers" items="${newspapers}"/> <form:errors path="newspapers"/></td>
	</tr>
	</table>
	<input type="submit" value="Search"/>
</form:form>

</div>
