<%@ taglib prefix="f" uri="/functions" %>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="result" />

	    	<span style="font-weight: bold; font-size: larger">${result.item}</span>
	    	<span style="font-size: larger; padding-left: 10px"> ${f:toString(result.item.age)} yrs</span>
	    	<span class="overall-score" style="background-color: ${f:toCssRGBColor(result.score)}">
	    	${f:toPercent(result.score)}%</span>
	    	
	    	<br/><b>${result.item.postcode}</b>
	    	<c:if test="${!empty f:toString(f:forwardsScore(result,'Distance'))}">
	    		<span>&nbsp; ${f:toString(f:forwardsScore(result,'Distance'))} miles</span>
	    	</c:if>
