package gov.kotkov.mikhail.exercise5.bookkeeper;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import gov.kotkov.mikhail.exercise5.employee.Employee;

class Bookkeeper {

	public Map<Employee, BigDecimal> calculateSalariesForEmployees(List<Employee> employees) {
		return employees.parallelStream().collect(Collectors.toMap(Function.identity(), Employee::calculateSalary));
	}
}
