<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="title" value="Employees" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
	<table id="mainTable">
	<form:form method="POST" action="employees">
		<tr>
			<td>
		 	<table id="contentTable" class="tablesorter">
		 	<thead>
			<tr id=headers>
				<th>Id</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Wage rate</th>
				<th>Actual workhours</th>
				<th>Job</th>
				<th colspan="3">Actions</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="employee" items="${employees}">
					<tr>
						<td>${employee.id}</td>
						<td>${employee.firstName}</td>
						<td>${employee.lastName}</td>
						<td>${employee.wageRate}</td>
						<td>${employee.actualWorkhours}</td>
						<td>${employee['class'].simpleName}</td>
						<td><button name="viewEmployeeId" type="submit" value='${employee.id}'>view</button></td>
						<td><button name="removeEmployeeId" type="submit" value='${employee.id}'>remove</button></td>
						<td><button name="configureEmployeeId" type="submit" value='${employee.id}'>configure</button></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</td>
		<td>
		<div id="controlDiv">
	 		<button name="addEmployeePage" type="submit">Add</button>
	 	</div>
		</td>
		</tr>
	</form:form>
	</table>
</body>
</html>