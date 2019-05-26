<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="title" value="ViewEmployee" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<table>
	<tr>
		<th colspan="2">Employee information</th>
	</tr>
	<tr>
		<td><b>First name</b></td>
		<td><input type="text" value="${employee.firstName}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td><b>Last name</b></td>
		<td><input type="text" value="${employee.lastName}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td><b>Wage rate</b></td>
		<td><input type="text" value="${employee.wageRate}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td><b>Actual workhours</b></td>
		<td><input type="text" value="${employee.actualWorkhours}" readonly="readonly"/></td>
	</tr>
	<tr>
		<td><b>Job</b></td>
		<td><input type="text" value="${employee['class'].simpleName}" readonly="readonly"/></td>
	</tr>
	</table>
	<a href="employees">back</a>
</body>
</html>