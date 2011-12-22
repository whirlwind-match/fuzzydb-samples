<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Connect" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>


<h3>Your Connections</h3>

<c:forEach var="providerId" items="${providerIds}">
	<c:set var="connections" value="${connectionMap[providerId]}" />
	<spring:message code="${providerId}.displayName" var="providerDisplayName" />
	<div class="accountConnection">
		<spring:message code="${providerId}.icon" var="iconUrl"/>
		<h4><img src="<c:url value="${iconUrl}" />" width="36" height="36" />${providerDisplayName}</h4>
		
		<p>
		<c:if test="${not empty connections}">
			You are connected to ${providerDisplayName} as ${connections[0].displayName}.
		</c:if>
		<c:if test="${empty connections}">
			You are not yet connected to ${providerDisplayName}.
		</c:if>
		Click <a href="<c:url value="/connect/${providerId}" />">here</a> to manage your ${providerDisplayName} connection.
		</p>
	</div>
</c:forEach>

<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>
