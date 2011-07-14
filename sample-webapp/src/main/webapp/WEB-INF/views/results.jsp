<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/functions" %>

<html>
<head>
	<title>${heading}</title>
</head>
<body>


<h1>
	${heading}
</h1>

<h2>Match style: ${style}</h2>
    <c:forEach items="${results}" var="result">
	    <div style="float:left; border: solid gray 1px; width:250px; margin: 2px; padding: 5px; min-height: 420px;">
	    	<span style="display: block;float: right; padding-right: 10px">matches: 
	    		<a href="?name=${result.item}">lax</a>
	    		<a href="?name=${result.item}&style=similarPeopleStrict">strict</a>
	    	</span>
	    	<span style="text-decoration: underline; font-weight: bold; font-size: larger">${result.item}</span>&nbsp;
		    <div style="margin: 2px; padding: 12 2 2 2"><b>Overall Score:</b>
		    	<div style="width: 100px; text-align:center; background-color: ${f:toCssRGBColor(result.score)}">
		    	${f:toPercent(result.score)}%</div>
		    	</div>
		    	<div style="padding: 15px 2px"><b>Scores:</b>
				    <c:forEach items="${result.score.scorerAttrNames}" var="match">
				    	<c:set var="fwd" value="${f:forwardsScore(result,match)}"/>
				    	<div style="padding-left: 10px">
				    		<span style="float:right; width: 50px; text-align:center; background-color: ${f:floatToCssRGBColor(fwd)}">${f:round(fwd * 100.1)}%</span>
				    		${match}: 
				    	</div>
				    </c:forEach> 
		    	<div style="padding: 15px 2px"><b>Attributes:</b>
				    <c:forEach items="${result.item.attributes}" var="item">
				    	<div  style="padding-left: 10px">${item.key}: ${f:toString(item.value)}</div>
				    </c:forEach> 
		    	</div>
		    </div>
	    </div>
    </c:forEach> 

</body>
</html>
