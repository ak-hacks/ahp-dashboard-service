<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<base target="_blank">
<style type="text/css">
body
{
    font-family:Georgia, "Times New Roman",Times, serif;
}

tr.header th {
	background-color: #507786;
	color: white;
}

tr.odd td {
	background-color: white;
	color: black;
    font-family:"Courier New", Courier, monospace;
}

tr.even td {
	background-color: #D1D1D1;
	color: black;
    font-family:"Courier New", Courier, monospace;
}

tr.oddsmall td {
	background-color: white;
	color: black;
    font-family:"Courier New", Courier, monospace;
    font-size:small;
}

tr.evensmall td {
	background-color: #D1D1D1;
	color: black;
    font-family:"Courier New", Courier, monospace;
    font-size:small;
}

A:link {text-decoration: none; color: #3A5FCD;}
A:visited {text-decoration: none; color: #3A5FCD;}
A:hover {text-decoration: underline; color: red;}

</style>

<script src="http://code.jquery.com/jquery-latest.js"></script>

</head>
<body>
<table border="1">
	<thead>
		<tr class="header">
			<th>Project Name</th>
			<th>Latest BuildLife</th>
			<th>In Int</th>
			<th>Ready for Test</th>
			<th>In Test</th>
			<th>Ready for Prod</th>
			<th>In Prod</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${projects}" var="project" varStatus="loopStatus">
			<tr class="${loopStatus.index % 2 == 0 ? 'even' : 'odd'}">
				<td rowspan="2" id="projectName"><a href="/ahpsvc/project/${project.projectName}">${project.projectName}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.mostRecentBuildLife}">${project.mostRecentReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inIntBuildLife}">${project.inIntReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForTestBuildLife}">${project.readyForTestReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inTestBuildLife}">${project.inTestReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.readyForProdBuildLife}">${project.readyForProdReleaseNumber}</a></td>
				<td><a href="http://ahp.svc.ft.com/tasks/project/BuildLifeTasks/viewBuildLife?buildLifeId=${project.inProdBuildLife}">${project.inProdReleaseNumber}</a></td>
			</tr>
			<tr class="${loopStatus.index % 2 == 0 ? 'evensmall' : 'oddsmall'}" id="row${loopStatus}">
				<td>${project.mostRecentBuildChanges}</td>
				<td>${project.inIntBuildChanges}</td>
				<td>${project.readyForTestBuildChanges}</td>
				<td>${project.inTestBuildChanges}</td>
				<td>${project.readyForProdBuildChanges}</td>
				<td>${project.inProdBuildChanges}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>