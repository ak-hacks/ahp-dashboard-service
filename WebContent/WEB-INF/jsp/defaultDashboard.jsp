<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3 align="center">${fn:toUpperCase(programme.name)}</h3>
<table border="1" class="report">
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
		<c:forEach items="${programme.projects}" var="project"
			varStatus="loopStatus">
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
				<td><a
					href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.mostRecentBuildLife}">${project.mostRecentReleaseNumber}</a></td>
				<td><a
					href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inIntBuildLife}">${project.inIntReleaseNumber}</a></td>
				<td><a
					href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForTestBuildLife}">${project.readyForTestReleaseNumber}</a></td>
				<td><a
					href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inTestBuildLife}">${project.inTestReleaseNumber}</a></td>
				<td><a
					href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForProdBuildLife}">${project.readyForProdReleaseNumber}</a></td>
				<td><a
					href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inProdBuildLife}">${project.inProdReleaseNumber}</a></td>
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