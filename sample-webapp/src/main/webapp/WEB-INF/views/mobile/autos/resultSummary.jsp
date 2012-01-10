<%@ taglib prefix="f" uri="/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="mobile" tagdir="/WEB-INF/tags/mobile" %>


<tiles:importAttribute name="result" />

<%-- ${f:toString(result.item.colour)} --%>
<%-- <br/><b>${result.item.postcode}</b> --%>

<div class="round" style="width:100px">
<span style="width:100px">${result.item.carMake}<span style="font-weight: normal;"> ${result.item}</span></span>
<c:set var="score" value="${f:forwardsScore(result,'Good Price Match')}"/>
<span style="left:150px; font-size: smaller; margin-left: 10px; background-color: ${f:floatToCssRGBColorSat(score,0.26)}">&pound;${f:toString(result.item.price)}</span>
</div>

<span class="overall-score" style="background-color: ${f:toCssRGBColor(result.score)}">
${f:toPercent(result.score)}%</span>

<mobile:distance result="${result}" />

<c:set var="score" value="${f:forwardsScore(result,'Power')}"/>
<span class="round" style="background-color: ${f:floatToCssRGBColorSat(score,0.26)}; margin-left:6px;">${f:toString(result.item.horsePower)} hp</span>
