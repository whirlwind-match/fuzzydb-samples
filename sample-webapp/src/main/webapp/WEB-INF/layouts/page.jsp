<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		
		<style type="text/css" media="screen">   
		  	@import url("<c:url value="/resources/dojo/resources/dojo.css"/>");
		/*   	@import url("<c:url value="/resources/dijit/themes/a11y/a11y.css"/>"); */
		/*   	@import url("<c:url value="/resources/dijit/themes/claro/claro.css"/>"); */
		/*    	@import url("<c:url value="/resources/dijit/themes/nihilo/nihilo.css"/>");  */
		   	@import url("<c:url value="/resources/dijit/themes/soria/soria.css"/>"); 
		/*    	@import url("<c:url value="/resources/dijit/themes/tundra/tundra.css"/>");  */
		<%--     @import url("<c:url value="/resources/styles/ours.css"/>"); --%>
		 	@import url("<c:url value="/static/css/site.css"/>"); 
		 	@import url("<c:url value="/static/css/page.css"/>"); 
		</style>     
		
		<script djconfig="parseOnLoad: true" src="<c:url value="/resources/dojo/dojo.js"/>" type="text/javascript"></script>
		<script type="text/javascript" src="<c:url value="/resources/spring/Spring.js" />"> </script>
		<script type="text/javascript" src="<c:url value="/resources/spring/Spring-Dojo.js" />"> </script>	
		<script type="text/javascript">dojo.require("dojo.parser");</script>

<%-- 
		<link rel="stylesheet" href="<c:url value="/resources/page.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" href="<c:url value="/resources/form.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" href="<c:url value="/resources/messages/messages.css" />" type="text/css" media="screen" />
 --%>		
<%-- 		<title><tiles:insertAttribute name="title"/></title>	 --%>
	</head>
	<body class="soria spring">
		<div id="wrap">
  
		  	<div id="menu">
				<h1><a href="<c:url value="/"/>">Home</a></h1>
				<tiles:insertTemplate template="menu.jsp" />
		    </div>
		    <div id="main">
		    	<div id="body">
					<tiles:insertAttribute name="content" />
				</div>		
			</div>		
		</div>		
	</body>
</html>
