<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3 align="center">Sysconfig</h3>
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
			<c:forEach items="${programme.projects}" var="project" varStatus="loopStatus"
				end="2">
				<c:choose>
					<c:when test='${(loopStatus.index)%2 eq 0}'>
						<c:set var="rowColor" value="grey" scope="page" />
					</c:when>
					<c:otherwise>
						<c:set var="rowColor" value="white" scope="page" />
					</c:otherwise>
				</c:choose>
				<tr class="${rowColor}">
					<td width="8%"><a href="/ahpsvc/rest/project/${project.projectName}">${project.projectName}</a></td>
					<td width="15%"><a
						href="${baseBuildLifeURL}${project.mostRecentBuildLife}">${project.mostRecentReleaseNumber eq project.inIntReleaseNumber ? '>>' : project.mostRecentReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inIntBuildLife}">${project.inIntReleaseNumber eq project.readyForTestReleaseNumber ? '>>' : project.inIntReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForTestBuildLife}">${project.readyForTestReleaseNumber eq project.inTestReleaseNumber ? '>>' : project.readyForTestReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inTestBuildLife}">${project.inTestReleaseNumber eq project.readyForProdReleaseNumber ? '>>' : project.inTestReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForProdBuildLife}">${project.readyForProdReleaseNumber eq project.inProdReleaseNumber ? '>>':project.readyForProdReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inProdBuildLife}">${project.inProdReleaseNumber}</a></td>
					<td width="2%"><div class="arrow"></div></td>
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

	<h3 align="center">Core and Utilities</h3>
	<table border="1" class="report">
		<tbody>
			<c:forEach items="${programme.projects}" var="project" varStatus="loopStatus"
				begin="3" end="10">
				<c:choose>
					<c:when test='${(loopStatus.index)%2 eq 0}'>
						<c:set var="rowColor" value="grey" scope="page" />
					</c:when>
					<c:otherwise>
						<c:set var="rowColor" value="white" scope="page" />
					</c:otherwise>
				</c:choose>
				<tr class="${rowColor}">
					<td width="8%"><a href="/ahpsvc/rest/project/${project.projectName}">${project.projectName}</a></td>
					<td width="15%"><a
						href="${baseBuildLifeURL}${project.mostRecentBuildLife}">${project.mostRecentReleaseNumber eq project.inIntReleaseNumber ? '>>' : project.mostRecentReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inIntBuildLife}">${project.inIntReleaseNumber eq project.readyForTestReleaseNumber ? '>>' : project.inIntReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForTestBuildLife}">${project.readyForTestReleaseNumber eq project.inTestReleaseNumber ? '>>' : project.readyForTestReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inTestBuildLife}">${project.inTestReleaseNumber eq project.readyForProdReleaseNumber ? '>>' : project.inTestReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForProdBuildLife}">${project.readyForProdReleaseNumber eq project.inProdReleaseNumber ? '>>':project.readyForProdReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inProdBuildLife}">${project.inProdReleaseNumber}</a></td>
					<td width="2%"><div class="arrow"></div></td>
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

	<h3 align="center">Servlets</h3>
	<table border="1" class="report">
		<tbody>
			<c:forEach items="${programme.projects}" var="project" varStatus="loopStatus"
				begin="11">
				<c:choose>
					<c:when test='${(loopStatus.index)%2 eq 0}'>
						<c:set var="rowColor" value="grey" scope="page" />
					</c:when>
					<c:otherwise>
						<c:set var="rowColor" value="white" scope="page" />
					</c:otherwise>
				</c:choose>
				<tr class="${rowColor}">
					<td width="8%"><a href="/ahpsvc/rest/project/${project.projectName}">${project.projectName}</a></td>
					<td width="15%"><a
						href="${baseBuildLifeURL}${project.mostRecentBuildLife}">${project.mostRecentReleaseNumber eq project.inIntReleaseNumber ? '>>' : project.mostRecentReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inIntBuildLife}">${project.inIntReleaseNumber eq project.readyForTestReleaseNumber ? '>>' : project.inIntReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForTestBuildLife}">${project.readyForTestReleaseNumber eq project.inTestReleaseNumber ? '>>' : project.readyForTestReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inTestBuildLife}">${project.inTestReleaseNumber eq project.readyForProdReleaseNumber ? '>>' : project.inTestReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForProdBuildLife}">${project.readyForProdReleaseNumber eq project.inProdReleaseNumber ? '>>':project.readyForProdReleaseNumber}</a></td>
					<td width="15%"><a
						href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inProdBuildLife}">${project.inProdReleaseNumber}</a></td>
					<td width="2%"><div class="arrow"></div></td>
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