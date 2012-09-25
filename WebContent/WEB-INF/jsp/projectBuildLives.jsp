<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
body {
	font-family: Arial, Helvetica, Sans-Serif;
	font-size: 100%
}

#report {
	text-align: left;
	font-size: 0.900em;
}

#report th {
	background: #7CB8E2 url(/ahpsvc/images/header_bkg.png) repeat-x scroll
		center left;
	color: #fff;
	padding: 7px 12px;
	text-align: left;
	font-size: 90%;
}

#report td {
	padding: 3px 7px;
	font-size: 85%;
}

tr.created td {
	background-color: #FFB90F;
	color: black;
}

tr.int td {
	background-color: #FFFF00;
	color: black;
}

tr.test td {
	background-color: #00FFFF;
	color: black;
}

tr.prod td {
	background-color: #8CDD81;
	color: black;
}

tr.default td {
	background-color: #FFFFFF;
	color: black;
}
</style>

<table border="1" id="report" cellpadding="1" cellspacing="1">
	<tr>
		<th width="8%">Project</th>
		<th width="3%">Buildlife</th>
		<th width="2%">Version</th>
		<th width="2%">Status</th>
		<th width="15%">Created On</th>
		<th width="15%">Deployed to INT on</th>
		<th width="15%">Deployed to TEST on</th>
		<th width="15%">Deployed to PROD on</th>
		<th width="25%">Changes</th>
	</tr>
	<c:forEach items="${buildLives}" var="buildLife">
		<c:choose>
			<c:when test="${buildLife.deployedToProdOn ne '-'}">
				<c:set var="rowStyle" value="prod" />
				<c:set var="status" value="In Prod"/>
			</c:when>
			<c:when test="${buildLife.deployedToTestOn ne '-'}">
				<c:set var="rowStyle" value="test" />
				<c:set var="status" value="In Test"/>
			</c:when>
			<c:when test="${buildLife.deployedToIntOn ne '-'}">
				<c:set var="rowStyle" value="int" />
				<c:set var="status" value="In Int"/>
			</c:when>
			<c:when test="${buildLife.actualWorkspaceDate ne '-'}">
				<c:set var="rowStyle" value="created" />
				<c:set var="status" value="Built"/>
			</c:when>
			<c:otherwise>
				<c:set var="rowStyle" value="default" />
				<c:set var="status" value="Unknown"/>
			</c:otherwise>
		</c:choose>
		<tr class="${rowStyle}">
			<td>${buildLife.projectName}</td>
			<td><a
				href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${buildLife.id}">${buildLife.id}</a></td>
			<td>${buildLife.lastStampValue}</td>
			<td>${status}</td>
			<td>${buildLife.actualWorkspaceDate}</td>
			<td>${buildLife.deployedToIntOn}</td>
			<td>${buildLife.deployedToTestOn}</td>
			<td>${buildLife.deployedToProdOn}</td>
			<td>${buildLife.changes}</td>
		</tr>
	</c:forEach>
</table>