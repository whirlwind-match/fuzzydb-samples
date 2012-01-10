<%@ taglib prefix="f" uri="/functions" %>
<%@ taglib prefix="sample" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="result" />


	<div  style="font-size: 100%; padding-left: 10px"><br/><b>${f:toString(result.item.mealTypes)}</b></div>
	<br/><b>Location: ${result.item.postcode}&nbsp; </b>

	<!-- Forward scores -->
	<sample:what-matches result="${result}"/>

   	<div style="float: right; padding-right: 10px; padding-top: 30px"> 
   		<a  data-ajax="false" href="?ref=${result.item.ref}&amp;style=${style}">matches</a>
   	</div>
	<div style="clear:left; float:none" ></div>
