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
	font-size: 85%;
}

#report td {
	padding: 3px 7px;
	font-size: 85%;
}

tr.created td {
	background-color: Ivory;
	color: black;
}

tr.int td {
	background-color: LightGrey;
	color: black;
}

tr.test td {
	background-color: Orange;
	color: black;
}

tr.testPassed td {
	background-color: LightGreen;
	color: black;
}

tr.testFailed td {
	background-color: LightSlateGrey;
	color: black;
}

tr.prod td {
	background-color: Green;
	color: black;
}

tr.prodPassed td {
	background-color: DarkGreen;
	color: black;
}

tr.prodFailed td {
	background-color: Red;
	color: black;
}

tr.default td {
	background-color: White;
	color: black;
}
</style>

<table border="1" id="report" cellpadding="1" cellspacing="1">
	<tr>
		<th width="11%">Project</th>
		<th width="1%">Buildlife</th>
		<th width="14%">Version</th>
		<th width="1%">Status</th>
		<th width="11%">Created On</th>
		<th width="11%">Deployed to INT on</th>
		<th width="11%">Deployed to TEST on</th>
		<th width="11%">Deployed to PROD on</th>
		<th width="27%">Changes</th>
	</tr>
	<c:forEach items="${buildLives}" var="buildLife">
		<c:choose>
			<c:when test="${buildLife.liveVerifPassedOn ne '-'}">
				<c:set var="rowStyle" value="prodPassed" />
				<c:set var="status" value="Live Verif Passed"/>
			</c:when>
			<c:when test="${buildLife.liveVerifFailedOn ne '-'}">
				<c:set var="rowStyle" value="prodFailed" />
				<c:set var="status" value="Live Verif Failed"/>
			</c:when>
			<c:when test="${buildLife.deployedToProdOn ne '-'}">
				<c:set var="rowStyle" value="prod" />
				<c:set var="status" value="In Prod"/>
			</c:when>
			<c:when test="${buildLife.testingPassedOn ne '-'}">
				<c:set var="rowStyle" value="testPassed" />
				<c:set var="status" value="Testing Passed"/>
			</c:when>
			<c:when test="${buildLife.testingFailedOn ne '-'}">
				<c:set var="rowStyle" value="testFailed" />
				<c:set var="status" value="Testing Failed"/>
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