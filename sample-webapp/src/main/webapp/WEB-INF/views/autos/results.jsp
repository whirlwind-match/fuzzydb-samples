<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value='Matches for ${subject}' />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>

<jsp:directive.include file="searchFormFragment.jsp"/>


<div dojoType="dijit.TitlePane" style="width: 100%" title="Options">

	<div style="float:left; width: 30%">
		<h3>Add more vehicles:</h3>
		<ul>
		    <li><a href="createItems?numItems=10">Add 10</a></li>
		    <li><a href="createItems?numItems=100">Add 100</a></li>
		    <li><a href="createItems?numItems=1000">Add 1000</a></li>
		</ul>
	</div>

	<div style="float:left; margin-left:10px">
	<h3>Click below to focus through a different lens:</h3>
	<ul>
	    <li><a href="?ref=${ref}&amp;style=autosMatchEverything">Match on all characteristics</a></li>
	    <li><a href="?ref=${ref}&amp;style=matchPriceAndLocation">Match price and location only</a></li>
	    <li><a href="?ref=${ref}&amp;style=matchPriceAndLocation30miles">Match price and location (30 miles)</a></li>
	</ul>
	</div>

	<div style="clear:left; float:none"></div> <!-- Strangely FF isn't accepting /> ending for empty -->
</div>
<div dojoType="dijit.TitlePane" style="width: 100%; clear:left; float:none; display:block" title="Matches for ${subject}"> <!--  using match style: ${style} -->
	<p>
		<em>Click on 'matches' link to find matches for that vehicle</em>
	</p>
	<c:if test="${startNextPage > 0}">
   		<a accesskey="n" href="?ref=${result.item.ref}&amp;style=${style}&amp;start=${startNextPage}&amp;pageSize=${pageSize}">next page</a>
	</c:if>

	<p/>
    <c:forEach var="result" items="${results}">
    	<c:set var="borderwidth" value=" solid #EEE 2px;"></c:set>
    	<c:if test="${f:toPercent(result.score) eq 100}">
	    	<c:set var="borderwidth" value="solid #1E1 2px;"></c:set>
    	</c:if>
    	
	    <div style="background-color:#F8F8F8; float:left; border: ${borderwidth}; border-radius: 10px; width:340px; margin: 5px; padding: 9px; min-height: 300px; box-shadow: 4px 4px 8px 0px #AAA;">
	    	<span style="font-weight: bold; font-size: larger">${f:toString(result.item.colour)} ${result.item}</span>
	    	<span style="font-size: larger; padding-left: 10px"> £${f:toString(result.item.price)}</span>
	    	<span style="float:right; width: 55px; height:22px; padding-top: 5px; border-radius: 5px; text-align:center; font-weight: bold; background-color: ${f:toCssRGBColor(result.score)}">
	    	${f:toPercent(result.score)}%</span>

	    	<br/><b>${result.item.postcode}</b>
	    	<c:if test="${!empty f:toString(f:forwardsScore(result,'Distance'))}">
	    	 ${f:toString(f:forwardsScore(result,'Distance'))} miles
	    	</c:if>
	    	<div  style="font-size: 100%; padding-left: 10px"><br/><b>${f:toString(result.item.options)}</b></div>

				<!-- Forward scores -->
	    	<div style="padding: 11px 2px"><b>What matches?</b>
			    <c:forEach items="${result.score.scorerAttrNames}" var="match">
			    	<c:set var="fwd" value="${f:forwardsScore(result,match)}"/>
			    	<c:choose>
				    	<c:when test="${match eq 'Distance'}">
<!-- 			    	<div style="padding-left: 10px"> -->
<%-- 	    				<span style="float:right; width: 50px; text-align:center">${f:round(fwd)}</span> --%>
<%--     					${match} (miles):  --%>
<!--     				</div> -->
				    	</c:when>
				    	<c:otherwise>
			    	<div style="padding-left: 10px; padding-top: 1px;">
			    		<span style="float:right; width: 50px; border-radius: 5px; text-align:center; background-color: ${f:floatToCssRGBColorSat(fwd,0.26)}">${f:round(fwd * 100.1)}%</span>
			    		${match}: 
			    	</div>
				    	</c:otherwise>
			    	</c:choose>
			    </c:forEach> 
		    </div>
	    	<div style="padding: 2px 2px 0px 2px; float:left;">
		    	<div  style="font-size: 80%; padding-left: 10px">Combined (mpg): <b>${f:toString(result.item.mpgCombined)}</b></div>
		    	<div  style="font-size: 80%; padding-left: 10px">Emissions (gCO2/km): <b>${f:toString(result.item.co2emissions)}</b></div>
		    	<div  style="font-size: 80%; padding-left: 10px">Power (bhp): <b>${f:toString(result.item.horsePower)}</b></div>
<%-- 		    	<div  style="font-size: 80%; padding-left: 10px">Location: <b>${f:toString(result.item.postcode)}</b></div> --%>
<%-- 		    	<div  style="font-size: 80%; padding-left: 10px">Colour: <b>${f:toString(result.item.colour)}</b></div> --%>
	    	</div>
	    	<span style="display: block;float: right; padding-right: 10px; padding-top: 30px"> 
	    		<a href="?ref=${result.item.ref}&amp;style=${style}">matches</a>
	    	</span>
			<div style="clear:left; float:none" ></div>
	    </div>
    </c:forEach> 
	<div style="clear:left; float:none"></div>
</div>
<jsp:directive.include file="/WEB-INF/includes/footer.jsp"/>