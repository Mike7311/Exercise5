package gov.kotkov.mikhail.exercise5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gov.kotkov.mikhail.exercise5.employee.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
