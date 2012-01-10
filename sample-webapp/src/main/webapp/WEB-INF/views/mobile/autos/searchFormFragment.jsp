<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<%@ taglib prefix="sample" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="mobile" tagdir="/WEB-INF/tags/mobile" %>


<form:form action="search" method="post" >

		<fieldset data-role="controlgroup" data-type="horizontal">
			<mobile:edit-input attrName="postcode" popupHelp="Where? (Try the first part of a UK postcode e.g. SE1)" />
		</fieldset>

		<fieldset data-role="controlgroup" data-type="horizontal">
			<mobile:edit-input attrName="price" popupHelp="Target price..." />
		</fieldset>


		<fieldset data-role="">
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

