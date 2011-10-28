<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value="Matches for ${subject}" />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>

<jsp:directive.include file="/WEB-INF/includes/searchForm.jsp"/>

<div dojoType="dijit.TitlePane" style="width: 100%" title="Options">

	<div style="float:left; width: 30%">
	<h3>Add more people:</h3>
	<ul>
	    <li><a href="createPeople?numPeople=10">Add 10</a></li>
	    <li><a href="createPeople?numPeople=100">Add 100</a></li>
	    <li><a href="createPeople?numPeople=1000">Add 1000</a></li>
	</ul>
	</div>

	<div style="float:left; margin-left:10px">
	<h3>Click match styles here to experiment with strict vs soft matches in determining overall score:</h3>
	<ul>
	    <li><a href="?ref=${ref}">similar people - soft</a></li>
	    <li><a href="?ref=${ref}&style=similarPeopleStrict">similar people - strict</a></li>
	</ul>
	</div>
	<div style="clear:left; float:none" />
</div>
<div dojoType="dijit.TitlePane" style="width: 100%" title="Matches for ${subject} using match style: ${style}">
	<p>
	<em>Click on 'matches' link to change to find matches for that person</em>
	</p>
		<c:if test="${startNextPage > 0}">
	   		<a href="?ref=${result.item.ref}&style=${style}&start=${startNextPage}&pageSize=${pageSize}">next page</a>
		</c:if>

	<p>
    <c:forEach items="${results}" var="result">
	    <div style="background-color:#F8F8F8; float:left; border: solid #EEE 0px; border-radius: 10px; width:270px; margin: 5px; padding: 9px; min-height: 410px; box-shadow: 4px 4px 8px 0px #AAA;">
	    	<span style="display: block;float: right; padding-right: 10px"> 
	    		<a href="?ref=${result.item.ref}&style=${style}">matches</a>
	    	</span>
	    	<span style="font-weight: bold; font-size: larger">${result.item}</span>&nbsp;
		    <div style="margin: 2px; padding: 11 2 2 2"><b>Overall Score:</b>
		    	<span style="float:right; width: 100px; border-radius: 5px; text-align:center; background-color: ${f:toCssRGBColor(result.score)}">
		    	${f:toPercent(result.score)}%</div>
		    	</span>
		    	<div style="padding: 11px 2px"><b>Scores:</b>
		    	</div>
		    	<c:set var="fwd" value="${f:forwardsTotal(result)}"/>
		    	<div style="padding-left: 10px">
		    		<span style="float:right; width: 50px; border-radius: 5px; text-align:center; background-color: ${f:floatToCssRGBColor(fwd)}">${f:round(fwd * 100.1)}%</span>
		    		Forwards: 
		    	</div>
		    	<c:set var="fwd" value="${f:reverseTotal(result)}"/>
		    	<div style="padding-left: 10px; padding-top: 1px">
		    		<span style="float:right; width: 50px; border-radius: 5px; text-align:center; background-color: ${f:floatToCssRGBColor(fwd)}">${f:round(fwd * 100.1)}%</span>
		    		Reverse: 
		    	</div>
		    	<div style="padding: 11px 2px"><b>Scores (fwd):</b>
				    <c:forEach items="${result.score.scorerAttrNames}" var="match">
				    	<c:set var="fwd" value="${f:forwardsScore(result,match)}"/>
				    	<c:choose>
					    	<c:when test="${match eq 'Distance'}">
				    	<div style="padding-left: 10px">
		    				<span style="float:right; width: 50px; text-align:center">${f:round(fwd)}</span>
	    					${match} (miles): 
	    				</div>
					    	</c:when>
					    	<c:otherwise>
				    	<div style="padding-left: 10px; padding-top: 1px;">
				    		<span style="float:right; width: 50px; border-radius: 5px; text-align:center; background-color: ${f:floatToCssRGBColor(fwd)}">${f:round(fwd * 100.1)}%</span>
				    		${match}: 
				    	</div>
					    	</c:otherwise>
				    	</c:choose>
				    </c:forEach> 
		    	<div style="padding: 11px 2px 0px 2px"><b>Attributes:</b>
				    <c:forEach items="${result.item.attributes}" var="item">
				    	<div  style="font-size: 80%; padding-left: 10px">${item.key}: <b>${f:toString(item.value)}</b></div>
				    </c:forEach> 
		    	</div>
		    </div>
	    </div>
    </c:forEach> 
	<div style="clear:left; float:none" />
</div>
<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>