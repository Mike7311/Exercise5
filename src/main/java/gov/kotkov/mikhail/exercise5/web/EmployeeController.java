package gov.kotkov.mikhail.exercise5.web;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gov.kotkov.mikhail.exercise5.employee.Employee;
import gov.kotkov.mikhail.exercise5.employee.Manager;
import gov.kotkov.mikhail.exercise5.employee.Programmer;
import gov.kotkov.mikhail.exercise5.service.EmployeeService;

@Controller
@RequestMapping("/employees*")
public class EmployeeController {

	private EmployeeService employeeService;
	
	public static final String REDIRECT = "redirect:/employees";
	
	public static final String VIEW = "viewEmployee";
	
	public static final String ADD = "addEmployee";
	
	public static final String CONFIGURE = "configureEmployee";
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping
	public String employees(Model model) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		return "employees";
	}
	
	@PostMapping(params = "viewEmployeeId")
	public String viewEmployee(Model model, @RequestParam("viewEmployeeId") long Id) {
		model.addAttribute("employee", employeeService.find(Id));
		return VIEW;
	}
	
	@PostMapping(params = "addEmployeePage")
	public String addEmployeePage() {
		return ADD;
	}
	
	@PostMapping("/add")
	public String addEmployee(@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName,
			@RequestParam("wageRate") BigDecimal wageRate,
			@RequestParam("job") String job
			) {
		Employee employee = null;
		switch(job) {
			case "Manager": employee = new Manager();
			break;
			case "Programmer": employee = new Programmer();
			break;
		}
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setWageRate(wageRate);
		employee.setActualWorkhours(0);
		employeeService.save(employee);
		return REDIRECT;
	}
	
	@PostMapping(params = "removeEmployeeId")
	public String removeEmployee(@RequestParam("removeEmployeeId") long Id) {
		Employee employeeToDelete = employeeService.find(Id);
		employeeService.delete(employeeToDelete);
		return REDIRECT;
	}
	
	@PostMapping(params = "configureEmployeeId")
	public String changeEmployeePage(Model model, @RequestParam("configureEmployeeId") long Id) {
		Employee employeeToChange = employeeService.find(Id);
		model.addAttribute("employee", employeeToChange);
		return CONFIGURE;
	}
	
	@PostMapping("/change")
	public String changeEmployee(
			@RequestParam("id") long id,
			@RequestParam("firstName") String firstName, 
			@RequestParam("lastName") String lastName,
			@RequestParam("wageRate") String wageRate,
			@RequestParam("actualWorkhours") int actualWorkhours,
			@RequestParam("job") String job
			) {
		Employee employee = null;
		switch(job) {
			case "Manager": employee = new Manager();
			break;
			case "Programmer": employee = new Programmer();
			break;
		}
		employee.setId(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setWageRate(new BigDecimal(wageRate));
		employee.setActualWorkhours(actualWorkhours);
		employeeService.save(employee);
		return REDIRECT;
	}
}
