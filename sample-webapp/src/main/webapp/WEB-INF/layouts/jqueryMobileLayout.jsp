<!DOCTYPE html> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page session="false" %>
<html>
<!-- <script src="http://1.2.3.4/bmi-int-js/bmi.js" language="javascript"></script>  -->
<script src="/static/jqm/bmi.js" language="javascript"></script>
	<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"> 
	<title>fuzzydb Mobile Demo</title> 
<!-- 	<link rel="stylesheet"  href="//code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css" />   -->
<!-- 	<script src="//code.jquery.com/jquery-1.6.4.js"></script> -->
<!-- 	<script src="//code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js"></script> -->
	<link rel="stylesheet" href="<c:url value="/static/css/themes/fuzzydb.min.css"/>">
	<link rel="stylesheet"  href="<c:url value="/static/jqm/jquery.mobile-1.0.min.css"/>" />  
	<link rel="stylesheet" href="<c:url value="/static/css/site.css"/>" />
	<script src="<c:url value="/static/jqm/jquery-1.6.4.js"/>"></script>
	<script src="<c:url value="/static/jqm/jquery.mobile-1.0.min.js"/>"></script>
</head> 
<body> 

	<div data-role="page" class="type-interior">

		<div data-role="header" data-theme="f">
			<h1>fuzzydb Mobile Sample</h1>
			<a href="#" data-icon="home" data-iconpos="notext" data-direction="reverse" class="ui-btn-right jqm-home">Home</a>
	
		</div><!-- /header -->
	
		<div data-role="content">
			<tiles:insertAttribute name="content" />
		</div><!-- /content -->
	
		<div data-role="footer" class="footer-docs" data-theme="c">
				<p>&copy; 2011-2012 Whirlwind Match Ltd</p>
		</div>

	</div><!-- /page -->

	</body>
	</html>
<script language="javascript"><!--
bmi_SafeAddOnload(bmi_load,"bmi_orig_img",0);//-->
</script>