package gov.kotkov.mikhail.exercise5.web;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import gov.kotkov.mikhail.exercise5.config.TestWebConfig;
import gov.kotkov.mikhail.exercise5.employee.Employee;
import gov.kotkov.mikhail.exercise5.employee.Manager;
import gov.kotkov.mikhail.exercise5.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestWebConfig.class)
@WebAppConfiguration
public class EmployeeControllerTest {

	MockMvc mockMvc;
	@Autowired
	EmployeeService mockEmployeeService;
	
	@Autowired
	private WebApplicationContext context;

	@Before
	public void init() {
		reset(mockEmployeeService);
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
	}
	
	@Test
	public void testEmployees() throws Exception {
		mockMvc.perform(get("/employees")).andExpect(status().isOk())
                .andExpect(view().name("employees"));
	}
	
	@Test
	public void testAddEmployeePage() throws Exception{
		mockMvc.perform(post("/employees").param("addEmployeePage", ""))
			.andExpect(status().isOk())
			.andExpect(view().name(EmployeeController.ADD));
	}
	
	@Test
	public void testAddEmployee() throws Exception {
		mockMvc.perform(post("/employees/add")
			.param("firstName", "Petr")
			.param("lastName", "Perviy")
			.param("wageRate", "500")
			.param("job", "Manager"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name(EmployeeController.REDIRECT));
	}
	
	@Test
	public void testRemoveEmployee() throws Exception {
		Employee employeeToDelete = createEmployee();
		when(mockEmployeeService.find(employeeToDelete.getId())).thenReturn(employeeToDelete);
		mockMvc.perform(post("/employees")
		.param("removeEmployeeId", "1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name(EmployeeController.REDIRECT));
	}
	
	@Test
	public void testChangeEmployeePage() throws Exception{
		Employee employeeToChange = createEmployee();
		when(mockEmployeeService.find(employeeToChange.getId())).thenReturn(employeeToChange);
		mockMvc.perform(post("/employees")
				.param("configureEmployeeId", "1"))
			.andExpect(status().isOk())
			.andExpect(view().name("configureEmployee"))
			.andExpect(model().attribute("employee", is(employeeToChange)));
	}
	
	@Test
	public void testChangeEmployee() throws Exception {
		mockMvc.perform(post("/employees/change")
		.param("id", "1")
		.param("firstName", "Petr")
		.param("lastName", "Perviy")
		.param("wageRate", "400")
		.param("actualWorkhours", "160")
		.param("job", "Programmer"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name(EmployeeController.REDIRECT));
	}
	
	public Employee createEmployee() {
		Employee employee = new Manager();
		employee.setId(1);
		employee.setFirstName("Petr");
		employee.setLastName("Perviy");
		employee.setWageRate(new BigDecimal(500));
		employee.setActualWorkhours(140);
		return employee;
	}

}
