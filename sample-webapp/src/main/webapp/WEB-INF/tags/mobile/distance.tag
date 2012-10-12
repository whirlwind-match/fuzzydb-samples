<%@ tag body-content="empty" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://fuzzydb.org/jsp/functions" %>
<%@ attribute name="result" type="java.lang.Object" required="true" %>

<!-- There is a score result of "Distance" as well as the score for the relevant scorer -->
<c:set var="score" value="${f:forwardsScore(result,'Distance')}"/>
<c:if test="${!empty f:toString(score)}">
	<span class="round" style="background-color: ${f:floatToCssRGBColorSat(f:forwardsScore(result,'Within 100 miles'),0.26)}">${f:toString(score)} miles</span>
</c:if>
