<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<%@ taglib prefix="sample" tagdir="/WEB-INF/tags" %>

<script type="text/javascript">dojo.require("dijit.TitlePane");</script>
<div dojoType="dijit.TitlePane" style="width: 100%" title="How about searching for some grub?">


<form:form action="search" method="post" >

	<table>
	<tr>
		<td>Location:</td>
		<td><sample:edit-input attrName="postcode" popupHelp="Any clue where? (Try the first part of a UK postcode e.g. SE1)" /></td>
	</tr>
	<tr>
		<td>Establishment type: </td>  
		<td><form:radiobuttons path="establishmentType" items="${establishmentTypes}" /></td>
	</tr>
	<tr>
		<td>Meal Types:</td>
		<td><form:checkboxes id="mealTypes" path="mealTypes" items="${mealTypes}"/> <form:errors path="mealTypes"/></td>
	</tr>
	</table>
	<input type="submit" value="Search"/>
</form:form>

</div>
