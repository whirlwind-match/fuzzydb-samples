<%@ taglib prefix="f" uri="http://fuzzydb.org/jsp/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="mobile" tagdir="/WEB-INF/tags/mobile" %>


<tiles:importAttribute name="result" />

<%-- ${f:toString(result.item.colour)} --%>
<%-- <br/><b>${result.item.postcode}</b> --%>

<div style="float:left; margin-right:4px; width:52%; overflow: hidden; " >
	<div style="width:100px">${result.item.carMake}<span style="font-weight: normal;"> ${result.item}</span></div>

	<span style="float:left; margin-right:10px">${f:toString(result.item.year)}</span>

	<c:set var="score" value="${f:forwardsScore(result,'Good Price Match')}"/>
	<span class="round" style="float:right; background-color: ${f:floatToCssRGBColorSat(score,0.26)}">&pound;${f:toString(result.item.price)}</span>
</div>

<div style="float:left; margin: 0 2px; width:21%" >
		<mobile:distance result="${result}" />
		<br>
		<c:set var="score" value="${f:forwardsScore(result,'Power')}"/>
		<span style="background-color: ${f:floatToCssRGBColorSat(score,0.26)};" 
			class="round">${f:toString(result.item.horsePower)} hp</span>
</div>


<span class="overall-score" style="background-color: ${f:toCssRGBColor(result.score)}">
${f:toPercent(result.score)}</span>
<span></span>

