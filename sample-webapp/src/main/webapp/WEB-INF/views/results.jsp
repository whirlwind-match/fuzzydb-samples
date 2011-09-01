<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/functions" %>

<html>
<head>
	<title>Matches for ${subject}:</title>
</head>
<body>


	<h3>Click match styles here to experiment with strict vs soft matches in determining overall score:</h3>
	<ul>
	    <li><a href="?ref=${ref}">similar people - soft</a></li>
	    <li><a href="?ref=${ref}&style=similarPeopleStrict">similar people - strict</a></li>
	</ul>

	<hr>
	<h3>Matches for ${subject} using match style: ${style}</h3>
	<p>
	<em>Click on 'matches' link to change to find matches for that person</em>
	</p>
	
    <c:forEach items="${results}" var="result">
	    <div style="float:left; border: solid gray 1px; width:250px; margin: 2px; padding: 5px; min-height: 420px;">
	    	<span style="display: block;float: right; padding-right: 10px"> 
	    		<a href="?ref=${result.item.ref}&style=${style}">matches</a>
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
