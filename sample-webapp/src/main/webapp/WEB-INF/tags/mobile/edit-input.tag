<%@ tag body-content="empty" %> 
<%@ attribute name="attrName" required="true" %>
<%@ attribute name="popupHelp" required="true" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:input id="${attrName}" path="${attrName}" placeholder="${popupHelp}" /><form:errors path="${attrName}"/>
