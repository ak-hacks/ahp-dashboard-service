<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<base target="_blank">
<style type="text/css">
body {
	font-family: Arial, Helvetica, Sans-Serif;
	font-size: 100%;
	color: #26466D;
}

.report {
	border-collapse: collapse;
}

.report h4 {
	margin: 0px;
	padding: 0px;
}

.report img {
	float: right;
}

.report ul {
	margin: 10px 0 10px 40px;
	padding: 0px;
}

.report th {
	background: #7CB8E2 url(/ahpsvc/images/header_bkg.png) repeat-x scroll
		center left;
	color: #fff;
	padding: 7px 12px;
	text-align: left;
}
/*
	#report td { background:#C7DDEE none repeat-x scroll center left; color:#000; padding:3px 9px; }
	#report tr.odd td { background:#fff repeat-x scroll center left; cursor:pointer; }
	*/
.report div.arrow {
	background: transparent url(/ahpsvc/images/arrows.png) no-repeat scroll
		0px -16px;
	width: 16px;
	height: 16px;
	display: block;
}

.report div.up {
	background-position: 0px 0px;
}

tr.grey td {
	background-color: #F0F0F0;
	color: black;
	padding: 7px 12px;
	font-size: 0.800em;
}

tr.white td {
	background-color: #CDCDCD;
	color: black;
	padding: 7px 12px;
	font-size: 0.800em;
}

A:link {
	text-decoration: none;
	color: #26466D;
}

A:visited {
	text-decoration: none;
	color: #26466D;
}

A:active {
	text-decoration: none;
	color: #26466D;
}

A:hover {
	text-decoration: none;
	color: red;
}
</style>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".report tr:odd").addClass("odd");
		$(".report tr:not(.odd)").hide();
		$(".report tr:first-child").show();

		$(".report tr.odd").click(function() {
			$(this).next("tr").toggle();
			$(this).find(".arrow").toggleClass("up");
		});
		//$("#report").jExpand();
	});
</script>

</head>
<body>
	<c:set var="baseBuildLifeURL" value="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=" scope="request"/>
	<c:choose>
		<c:when test="${programme.name eq 'newsroomsystems'}">
			<jsp:include page="nsOverallDashboard.jsp" />
		</c:when> 
		<c:otherwise>
			<jsp:include page="defaultDashboard.jsp" />
		</c:otherwise>
	</c:choose>
	
	<p><i>'>>' Indicates the release number is same as the next environment</i></p>
</body>
</html>