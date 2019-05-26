<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="ConfigureEmployee" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/jquery.jspf" %>
<script src="<c:url value="/static/js/jquery.validate.min.js"/>"></script>
<script>
	$().ready(function() {
		$("#employeeForm").validate({
				rules: {
					firstName: "required",
					lastName: "required",
					wageRate: {
						required: true,
						number: true,
						min: 0
					},
					actualWorkhours: {
						required: true,
						number: true,
						range: [0, 200]
					}
				},
				messages: {
					firstName: "Please enter a first name",
					lastName: "Please enter a last name",
					wageRate: {
						required: "Please enter a wage rate",
						number: "wage rate should be a number",
						min: "Wage rate can't be lower then 0"
					},
					actualWorkhours: {
						required: "Please enter a work hours",
						number: "Workhours should be represented by number",
						range: "Amount of workhours should be in 0-200 range"
					}
				}
		});
	});
	</script>
</head>
<body>


<form id="employeeForm" method="POST" action="employees/change" >
	<input type="hidden" name="id" value="${employee.id}" />
	<input type="hidden" name="job" value="${employee['class'].simpleName}"/>
	<table><tr>
		<td><label for="firstName">First name</label></td>
		<td><input type='text' id="firstName" name="firstName" maxlength="20" value="${employee.firstName}"></td>
	</tr>
	<tr>
		<td><label for="lastName">Last name</label></td>
		<td><input type='text' id="lastName" name="lastName" maxlength="20" value="${employee.lastName}"></td>
	</tr>
	<tr>
		<td><label for="wageRate">Wage rate</label></td>
		<td><input type='text' id="wageRate" name="wageRate" maxlength="6" value="<fmt:formatNumber value="${employee.wageRate}" minFractionDigits="0"/>"></td>
	</tr>
	<tr>
		<td><label for="actualWorkhours">Actual workhours</label></td>
		<td><input type='text' id="actualWorkhours" name="actualWorkhours" maxlength="3" value="${employee.actualWorkhours}"></td>
	</tr>
	</table>
	<button type="submit">Update</button>
	</form>
</body>
</html>