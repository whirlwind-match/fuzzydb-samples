
<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<style>
<!--
.ui-content {
    padding: 0 ;
}
.ui-corner-top {
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
.ui-corner-bottom {
    border-bottom-left-radius: 0;
    border-bottom-right-radius: 0;
}
.ui-icon {display: none;}
.ui-collapsible-heading a .ui-btn-inner {
   	padding-left: 12px;
	text-overflow:clip;
}
.ui-collapsible-set {
    margin: 0;
}
.ui-content .ui-listview {
    margin: 0 -15px;
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
	
			<tiles:insertAttribute name="resultDetail" >
				<tiles:putAttribute name="result" value="${result}"></tiles:putAttribute>
			</tiles:insertAttribute>
		</div>
    </c:forEach> 
</div>

<div >
	<a style="float:left" data-role="button" data-inline="true" data-icon="plus" data-ajax="false" href="createItems?numItems=10">+10</a>

	<c:if test="${startNextPage > 0}">
   		<a style="float:right" data-theme="d" data-role="button" data-inline="true" data-icon="arrow-r" data-ajax="false" 
   		accesskey="n" href="?ref=${result.item.ref}&amp;style=${style}&amp;start=${startNextPage}&amp;pageSize=${pageSize}">next page</a>
	</c:if>
</div>

