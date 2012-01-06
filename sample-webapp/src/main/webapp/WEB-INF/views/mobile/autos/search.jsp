
<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>

<ul data-role="listview">
	
	<li>
		<h3>Fuzzy Search</h3>
		<ul>
			<li>
<jsp:directive.include file="searchFormFragment.jsp"/>
			</li>
		</ul>
	</li>

	<li>
		<h3>Results</h3>
		<p>Click on 'matches' link to find matches for that vehicle</p>
	</li>

	
	<c:if test="${startNextPage > 0}">
	<li>
   		<a accesskey="n" href="?ref=${result.item.ref}&amp;style=${style}&amp;start=${startNextPage}&amp;pageSize=${pageSize}">next page</a>
	</li>
	</c:if>

    <c:forEach var="result" items="${results}">
    	<c:set var="borderwidth" value=" solid #EEE 2px;"></c:set>
    	<c:if test="${f:toPercent(result.score) eq 100}">
	    	<c:set var="borderwidth" value="solid #1E1 2px;"></c:set>
    	</c:if>
    	
		<li style="border: ${borderwidth}">
	    	<span style="font-weight: bold; font-size: larger">${f:toString(result.item.colour)} ${result.item.carMake} ${result.item}</span>
	    	<span style="font-size: larger; padding-left: 10px"> £${f:toString(result.item.price)}</span>
	    	<span class="overall-score" style="background-color: ${f:toCssRGBColor(result.score)}">
	    	${f:toPercent(result.score)}%</span>

	    	<br/><b>${result.item.postcode}</b>
	    	<c:if test="${!empty f:toString(f:forwardsScore(result,'Distance'))}">
	    		<span>&nbsp; ${f:toString(f:forwardsScore(result,'Distance'))} miles</span>
	    	</c:if>

	    	<ul>
	    		<li>
			    	<div  style="font-size: 100%; padding-left: 10px"><br/><b>${f:toString(result.item.options)}</b></div>
		
					<!-- Forward scores -->
					<sample:what-matches result="${result}"/>

			    	<div style="padding: 2px 2px 0px 2px; float:left;">
				    	<div class="attribute">Combined (mpg): <b>${f:toString(result.item.mpgCombined)}</b></div>
				    	<div class="attribute">Emissions (gCO2/km): <b>${f:toString(result.item.co2emissions)}</b></div>
				    	<div class="attribute">Power (bhp): <b>${f:toString(result.item.horsePower)}</b></div>
				    	<div class="attribute">Distance:  ${f:toString(f:forwardsScore(result,'Distance'))} miles</div>
			    	</div>
			    	<div style="float: right; padding-right: 10px; padding-top: 30px"> 
			    		<a  data-ajax="false" href="?ref=${result.item.ref}&amp;style=${style}">matches</a>
			    	</div>
					<div style="clear:left; float:none" ></div>
	    		</li>
	    	</ul>
	    	
		<li>
    </c:forEach> 
</ul>
