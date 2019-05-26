package gov.kotkov.mikhail.exercise5.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gov.kotkov.mikhail.exercise5.employee.Employee;
import gov.kotkov.mikhail.exercise5.repository.EmployeeRepository;

@Transactional(readOnly = true)
@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Transactional
	public Employee save(@Valid Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@Transactional
	public List<Employee> save(@Valid List<Employee> employees) {
		return employeeRepository.save(employees);
	}

	public Employee find(long employeeId) {
		return employeeRepository.findOne(employeeId);
	}
	
	public List<Employee> find(List<Long> ids) {
		return employeeRepository.findAll(ids);
	}

	@Transactional
	public void delete(Employee employeeToDelete) {
		employeeRepository.delete(employeeToDelete);
	}
	
}