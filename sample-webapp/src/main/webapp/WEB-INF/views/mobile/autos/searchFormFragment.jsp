<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<%@ taglib prefix="sample" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="mobile" tagdir="/WEB-INF/tags/mobile" %>


<form:form action="search" method="post" >

		<fieldset data-role="controlgroup" data-type="horizontal">
			<legend>Location: (UK postcode e.g. CB4 or CB4 ODW)</legend>
			<mobile:edit-input attrName="postcode" popupHelp="Any clue where? (Try the first part of a UK postcode e.g. SE1)" />
		</fieldset>

		<fieldset data-role="controlgroup" data-type="horizontal">
			<legend>Target price:</legend>
			<mobile:edit-input attrName="price" popupHelp="Enter the *lowest* price you think will work..." />
		</fieldset>


		<fieldset data-role="">
<!-- 			<legend>What options would you like:</legend> -->
			<form:select id="options" path="options" >
				<form:option value="">Choose options</form:option>
				<form:options items="${options}"/>
			</form:select>
		</fieldset>

<!-- 		<legend>Colour:</legend> -->
<%-- 		<td><form:select path="colour"> --%>
<%-- 			<form:option value="" /> --%>
<%-- 			<form:options items="${colours}"/>  --%>
<%-- 		</form:select><form:errors path="colour"/> --%>

<!-- 		<legend>Make:</legend> -->
<%-- 		<td><form:select path="carMake"> --%>
<%-- 			<form:option value="" /> --%>
<%-- 			<form:options items="${carMakes}"/>  --%>
<%-- 		</form:select><form:errors path="carMake"/> --%>
	<input type="submit" value="Search"/>
</form:form>

