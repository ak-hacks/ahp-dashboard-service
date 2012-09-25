<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<base target="_blank">
<style type="text/css">
body {
	font-family: Arial, Helvetica, Sans-Serif;
	font-size: 100%
}

#report {
	border-collapse: collapse;
}

#report h4 {
	margin: 0px;
	padding: 0px;
}

#report img {
	float: right;
}

#report ul {
	margin: 10px 0 10px 40px;
	padding: 0px;
}

#report th {
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
#report div.arrow {
	background: transparent url(/ahpsvc/images/arrows.png) no-repeat scroll
		0px -16px;
	width: 16px;
	height: 16px;
	display: block;
}

#report div.up {
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
	color: #26466D;
}

A:visited {
	color: #26466D;
}

A:active {
	color: #26466D;
}

A:hover {
	text-decoration: underline;
	color: red;
}
</style>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">  
$(document).ready(function(){
    $("#report tr:odd").addClass("odd");
    $("#report tr:not(.odd)").hide();
    $("#report tr:first-child").show();
    
    $("#report tr.odd").click(function(){
        $(this).next("tr").toggle();
        $(this).find(".arrow").toggleClass("up");
    });
    //$("#report").jExpand();
});
</script> 

</head>
<body>
<table border="1" id="report" cellpadding="5" cellspacing="5">
	<thead>
		<tr class="header">
			<th width="8%">Project</th>
			<th width="15%">Latest BuildLife</th>
			<th width="15%">In Int</th>
			<th width="15%">Ready for Test</th>
			<th width="15%">In Test</th>
			<th width="15%">Ready for Prod</th>
			<th width="15%">In Prod</th>
			<th width="2%"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${projects}" var="project" varStatus="loopStatus">
				<c:choose>
					<c:when test='${(loopStatus.index)%2 eq 0}'>
						<c:set var="rowColor" value="grey" scope="page" />
					</c:when>
					<c:otherwise>
						<c:set var="rowColor" value="white" scope="page" />
					</c:otherwise>
				</c:choose>
				<tr class="${rowColor}">
				<td><a href="/ahpsvc/rest/project/${project.projectName}">${project.projectName}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.mostRecentBuildLife}">${project.mostRecentReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inIntBuildLife}">${project.inIntReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForTestBuildLife}">${project.readyForTestReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inTestBuildLife}">${project.inTestReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForProdBuildLife}">${project.readyForProdReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inProdBuildLife}">${project.inProdReleaseNumber}</a></td>
				<td><div class="arrow"></div></td>
			</tr>
			<tr>
				<td><i>Changes:</i></td>
				<td>${project.mostRecentBuildChanges}</td>
				<td>${project.inIntBuildChanges}</td>
				<td>${project.readyForTestBuildChanges}</td>
				<td>${project.inTestBuildChanges}</td>
				<td>${project.readyForProdBuildChanges}</td>
				<td>${project.inProdBuildChanges}</td>
				<td></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>