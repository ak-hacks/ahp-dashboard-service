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
<tr class="created"><td>Created</td></tr>
<tr class="int"><td>Deployed to INT</td></tr>
<tr class="test"><td>Deployed to TEST</td></tr>
<tr class="testPassed"><td>Testing PASSED</td></tr>
<tr class="testFailed"><td>Testing FAILED</td></tr>
<tr class="prod"><td>Deployed to PROD</td></tr>
<tr class="prodPassed"><td>Live verification PASSED</td></tr>
<tr class="prodFailed"><td>Live verification FAILED</td></tr>
</table>