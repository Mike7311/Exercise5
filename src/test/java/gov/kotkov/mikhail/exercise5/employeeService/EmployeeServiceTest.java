package gov.kotkov.mikhail.exercise5.employeeService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import gov.kotkov.mikhail.exercise5.config.DataConfig;
import gov.kotkov.mikhail.exercise5.employee.Employee;
import gov.kotkov.mikhail.exercise5.employee.Manager;
import gov.kotkov.mikhail.exercise5.employee.Programmer;
import gov.kotkov.mikhail.exercise5.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
  classes = { DataConfig.class }, 
  loader = AnnotationConfigContextLoader.class)
@Transactional
public class EmployeeServiceTest {
     
    @Resource
    private EmployeeService employeeService;
    
    private Employee testEmployee;
    
    @Before
    public void setup() {
    	testEmployee = new Programmer();
    	testEmployee.setFirstName("John");
    	testEmployee.setLastName("Bill");
    	testEmployee.setWageRate(new BigDecimal(500));
    	testEmployee.setActualWorkhours(150);
    }
    
    @Test
    public void testFind() {
    	employeeService.save(testEmployee);
    	assertEquals(testEmployee, employeeService.find(testEmployee.getId()));
    }
    
    @Test
    public void testDelete() {
    	employeeService.save(testEmployee);
    	employeeService.delete(testEmployee);
    	assertNull(employeeService.find(testEmployee.getId()));
    }
    
    @Test
    public void testSaveMultiple() {
    	Employee testEmployee2  = new Manager();
    	testEmployee2.setFirstName("Ruslan");
    	testEmployee2.setLastName("Bashirov");
    	testEmployee2.setWageRate(new BigDecimal(400));
    	testEmployee2.setActualWorkhours(160);
    	employeeService.save(Arrays.asList(testEmployee, testEmployee2));
    	assertEquals(2, employeeService.findAll().size());
    }
    
}