
<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<style>
<!--
.ui-icon {display: none;}
.ui-collapsible-heading a .ui-btn-inner {
   	padding-left: 12px;
	text-overflow:clip;
}
.ui-collapsible-set {
    margin: 1em 0;
}

.ui-btn-inner {
}
-->
</style>


<div data-role="collapsible-set" data-theme="c">

	
    <c:forEach var="result" items="${results}">
	   	<c:set var="borderwidth" value=" solid #EEE 2px;"></c:set>
	   	<c:if test="${f:toPercent(result.score) eq 100}">
	    	<c:set var="borderwidth" value="solid #1E1 2px;"></c:set>
	   	</c:if>

		<div data-role="collapsible" data-collapsed="true">
	    	
			<h3>
				<tiles:insertAttribute name="resultSummary" >
					<tiles:putAttribute name="result" value="${result}"></tiles:putAttribute>
				</tiles:insertAttribute>
			</h3>
	
				
	
<%-- 			<div style="border: ${borderwidth}"> --%>
				<tiles:insertAttribute name="resultDetail" >
					<tiles:putAttribute name="result" value="${result}"></tiles:putAttribute>
				</tiles:insertAttribute>
		    	
<!-- 			</div> -->
		</div>
    </c:forEach> 
</div>
<ul data-role="listview">
	<c:if test="${startNextPage > 0}">
	<li>
   		<a accesskey="n" href="?ref=${result.item.ref}&amp;style=${style}&amp;start=${startNextPage}&amp;pageSize=${pageSize}">next page</a>
	</li>
	</c:if>
	<li><a data-ajax="false" href="createItems?numItems=10">+10</a></li>
</ul>