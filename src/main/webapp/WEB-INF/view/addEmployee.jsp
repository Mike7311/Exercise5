<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="title" value="AddEmployee" scope="page"/>
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
				}
			},
			messages: {
				firstName: "Please enter a first name",
				lastName: "Please enter a last name",
				wageRate: {
					required: "Please enter a wage rate",
					number: "wage rate should be a number",
					min: "Wage rate can't be lower then 0"
				}
			}
	});
});
	</script>
</head>
<body>
<form method="POST" id="employeeForm" action="employees/add">
	<table>
	<tr>
		<td><label for="firstName">First name</label></td>
		<td><input type='text' id="firstName" name="firstName" maxlength="20" value="${employee.firstName}"></td>
	</tr>
	<tr>
		<td><label for="lastName">Last name</label></td>
		<td><input type='text' id="lastName" name="lastName" maxlength="20" value="${employee.lastName}"></td>
	</tr>
	<tr>
		<td><label for="wageRate">Wage rate</label></td>
		<td><input type='text' id="wageRate" name="wageRate" maxlength="6" value="${employee.wageRate}"></td>
	</tr>
	<tr>
		<td><select name="job">
    		<option value="Manager">Manager</option>
    		<option value="Programmer">Programmer</option>
		</select></td>
	</tr>
	</table>
	<input type="submit" value="Add"/>
	</form>
</body>
</html>