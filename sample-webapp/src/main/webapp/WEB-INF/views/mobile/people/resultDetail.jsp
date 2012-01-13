<%@ taglib prefix="f" uri="/functions" %>
<%@ taglib prefix="sample" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="result" />


   	<div  style="font-size: 100%; padding-left: 10px"><br/><b>${f:toString(result.item.newspapers)}</b></div>

	<!-- Forward scores -->
	<sample:what-matches result="${result}"/>

   	<div style="padding: 2px 2px 0px 2px; float:left;">
    	<div class="attribute">Male: <b>${result.item.isMale}</b></div>
    	<div class="attribute">Salary: <b>${result.item.salary}</b></div>
    	<div class="attribute">Smoke: <b>${f:toString(result.item.smoke)}</b></div>
	    	<div class="attribute">Work Postcode: <b>${result.item.workPostcode}</b></div>
   	</div>

   	<div style="float: right; padding-right: 10px; padding-top: 30px"> 
   		<a  data-ajax="false" href="?ref=${result.item.ref}&amp;style=${style}">matches</a>
   	</div>
	<div style="clear:left; float:none" ></div>
