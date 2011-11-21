<%@ tag body-content="empty" %> 
<%@ attribute name="attrName" required="true" %>
<%@ attribute name="popupHelp" required="true" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:input id="${attrName}" path="${attrName}" /><form:errors path="${attrName}"/>
<script type="text/javascript">Spring.addDecoration(new Spring.ElementDecoration({elementId : "${attrName}", 
	widgetType : "dijit.form.ValidationTextBox", widgetAttrs : {promptMessage: "${popupHelp}", required : false}}));</script>
