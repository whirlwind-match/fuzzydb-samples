<jsp:directive.include file="/WEB-INF/includes/includes.jsp"/>
<c:set var="title" scope="page" value='Matches for ${subject}' />
<jsp:directive.include file="/WEB-INF/includes/header.jsp"/>
<script type="text/javascript">dojo.require("dijit.TitlePane");</script>

<jsp:directive.include file="searchFormFragment.jsp"/>

<div dojoType="dijit.TitlePane" style="width: 100%" title="Options" open="false">

	<div style="float:left; width: 30%">
		<h3>Add more people:</h3>
		<ul>
		    <li><a href="createItems?numItems=10">Add 10</a></li>
		    <li><a href="createItems?numItems=100">Add 100</a></li>
		    <li><a href="createItems?numItems=1000">Add 1000</a></li>
		</ul>
	</div>

	<div style="float:left; margin-left:10px">
	<h3>Click below to focus through a different lens:</h3>
	<ul>
	    <li><a href="?ref=${ref}">similar people - soft</a></li>
	    <li><a href="?ref=${ref}&amp;style=similarPeopleStrict">similar people - strict</a></li>
	</ul>
	</div>

	<div style="clear:left; float:none"></div> <!-- Strangely FF isn't accepting /> ending for empty -->
</div>
<div dojoType="dijit.TitlePane" style="width: 100%; clear:left; float:none; display:block" title="Matches for ${subject}"> <!--  using match style: ${style} -->
	<p>
	    <em>Click on 'matches' link to change to find matches for that person</em>
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
    	
	    <div class="item-outer" style="border: ${borderwidth}; min-height: 300px;">
	    	<span style="font-weight: bold; font-size: larger">${result.item}</span>
	    	<span style="font-size: larger; padding-left: 10px"> ${f:toString(result.item.age)}</span>
	    	<span class="overall-score" style="background-color: ${f:toCssRGBColor(result.score)}">
	    	${f:toPercent(result.score)}%</span>

	    	<br/><b>${result.item.postcode}</b>
	    	<c:if test="${!empty f:toString(f:forwardsScore(result,'Distance'))}">
	    	 ${f:toString(f:forwardsScore(result,'Distance'))} miles
	    	</c:if>
	    	<div  style="font-size: 100%; padding-left: 10px"><br/><b>${f:toString(result.item.newspapers)}</b></div>

				<!-- Forward scores -->
	    	<div style="padding: 11px 2px"><b>What matches?</b>
			    <c:forEach items="${result.score.scoreEntryNames}" var="match">
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
			    		<span class="score" style="background-color: ${f:floatToCssRGBColorSat(fwd,0.26)}">${f:round(fwd * 100.1)}%</span>
			    		${match}: 
			    	</div>
				    	</c:otherwise>
			    	</c:choose>
			    </c:forEach> 
		    </div>
	    	<div style="padding: 2px 2px 0px 2px; float:left;">
<!--		    	<div class="attribute">Ages wanted: <b>${f:toString(result.item.ageRange)}</b></div>
-->		    	<div class="attribute">Male: <b>${result.item.isMale}</b></div>
		    	<div class="attribute">Salary: <b>${result.item.salary}</b></div>
		    	<div class="attribute">Smoke: <b>${f:toString(result.item.smoke)}</b></div>
 		    	<div class="attribute">Work Postcode: <b>${result.item.workPostcode}</b></div>
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