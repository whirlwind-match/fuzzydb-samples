<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<%@ taglib prefix="sample" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="mobile" tagdir="/WEB-INF/tags/mobile" %>


<form:form action="search" method="post" >

		<fieldset data-role="controlgroup" data-type="horizontal">
			<legend>Location: (UK postcode e.g. CB4 or CB4 ODW)</legend>
			<mobile:edit-input attrName="postcode" popupHelp="Any clue where? (Try the first part of a UK postcode e.g. SE1)" />
		</fieldset>

		<fieldset data-role="controlgroup" data-type="horizontal">
			<legend>Age:</legend>
			<mobile:edit-input attrName="age" popupHelp="Enter their rough age..." />
		</fieldset>
		
		<fieldset data-role="controlgroup" data-type="horizontal">
			<legend>Newspapers:</legend>
			<form:checkboxes id="newspapers" path="newspapers" items="${newspapers}"/> <form:errors path="newspapers"/>
		</fieldset>
		
	<input type="submit" value="Search"/>
</form:form>

