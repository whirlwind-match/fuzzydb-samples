<%@ taglib prefix="f" uri="/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="mobile" tagdir="/WEB-INF/tags/mobile" %>

<tiles:importAttribute name="result" />

<span style="font-weight: bold; font-size: larger">${result.item}</span>
<span class="overall-score" style="background-color: ${f:toCssRGBColor(result.score)}">
${f:toPercent(result.score)}%</span>

<br/>
<mobile:distance result="${result}" />
<span style="font-size: larger; padding-left: 10px"> ${result.item.establishmentType}</span>
