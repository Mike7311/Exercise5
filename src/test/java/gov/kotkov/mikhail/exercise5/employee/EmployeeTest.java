package gov.kotkov.mikhail.exercise5.employee;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import gov.kotkov.mikhail.exercise5.employee.Employee;
import gov.kotkov.mikhail.exercise5.employee.Programmer;

public class EmployeeTest {

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void testCorrectWorkhoursPercentage() {
		Programmer.setStandardWorkhours(160);
		Employee employee = new Programmer();
		employee.setActualWorkhours(140);
		assertEquals(87.5 , employee.getWorkhoursPercentage(), 2);
	}


}
