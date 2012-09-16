<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
tr.created td {
	background-color: #FFA500;
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
	background-color: #008000;
	color: black;
}

tr.default td {
	background-color: #FFFFFF;
	color: black;
}
</style>

<table border="1">
	<tr>
		<th>Project Name</th>
		<th>Buildlife ID</th>
		<th>Release Version Number</th>
		<th>Created On</th>
		<th>Deployed to INT on</th>
		<th>Deployed to TEST on</th>
		<th>Deployed to PROD on</th>
		<th>Changes</th>
	</tr>
	<c:forEach items="${buildLives}" var="buildLife">
		<c:choose>
			<c:when test="${buildLife.deployedToProdOn ne '-'}">
				<c:set var="rowStyle" value="prod" />
			</c:when>
			<c:when test="${buildLife.deployedToTestOn ne '-'}">
				<c:set var="rowStyle" value="test" />
			</c:when>
			<c:when test="${buildLife.deployedToIntOn ne '-'}">
				<c:set var="rowStyle" value="int" />
			</c:when>
			<c:when test="${buildLife.actualWorkspaceDate ne '-'}">
				<c:set var="rowStyle" value="created" />
			</c:when>
			<c:otherwise>
				<c:set var="rowStyle" value="default" />
			</c:otherwise>
		</c:choose>
		<tr class="${rowStyle}">
			<td>${buildLife.projectName}</td>
			<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${buildLife.id}">${buildLife.id}</a></td>
			<td>${buildLife.lastStampValue}</td>
			<td>${buildLife.actualWorkspaceDate}</td>
			<td>${buildLife.deployedToIntOn}</td>
			<td>${buildLife.deployedToTestOn}</td>
			<td>${buildLife.deployedToProdOn}</td>
			<td>${buildLife.changes}</td>
		</tr>
	</c:forEach>



	<tr class="default">
		<td>default</td>
		<td>default</td>
	</tr>
	<tr class="created">
		<td>created</td>
		<td>created</td>
	</tr>
	<tr class="int">
		<td>int</td>
		<td>int</td>
	</tr>
	<tr class="test">
		<td>test</td>
		<td>test</td>
	</tr>
	<tr class="prod">
		<td>prod</td>
		<td>prod</td>
	</tr>
</table>