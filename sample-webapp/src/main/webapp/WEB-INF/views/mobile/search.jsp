
<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<ul data-role="listview">
	
	<li>
		<h3>Fuzzy Search</h3>
		<ul>
			<li>
				<tiles:insertAttribute name="searchForm"  />
			</li>
		</ul>
	</li>

    <c:forEach var="result" items="${results}">
    	<c:set var="borderwidth" value=" solid #EEE 2px;"></c:set>
    	<c:if test="${f:toPercent(result.score) eq 100}">
	    	<c:set var="borderwidth" value="solid #1E1 2px;"></c:set>
    	</c:if>
    	
		<li style="border: ${borderwidth}">

			<tiles:insertAttribute name="resultSummary" >
				<tiles:putAttribute name="result" value="${result}"></tiles:putAttribute>
			</tiles:insertAttribute>
			

	    	<ul>
	    		<li>
					<tiles:insertAttribute name="resultDetail" >
						<tiles:putAttribute name="result" value="${result}"></tiles:putAttribute>
					</tiles:insertAttribute>
	    		</li>
	    	</ul>
	    	
		</li>
    </c:forEach> 
	
	<c:if test="${startNextPage > 0}">
	<li>
   		<a accesskey="n" href="?ref=${result.item.ref}&amp;style=${style}&amp;start=${startNextPage}&amp;pageSize=${pageSize}">next page</a>
	</li>
	</c:if>
	<li><a data-ajax="false" href="createItems?numItems=10">+10</a></li>
</ul>
