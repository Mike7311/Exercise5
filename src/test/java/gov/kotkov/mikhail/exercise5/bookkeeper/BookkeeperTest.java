package gov.kotkov.mikhail.exercise5.bookkeeper;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import gov.kotkov.mikhail.exercise5.bookkeeper.Bookkeeper;
import gov.kotkov.mikhail.exercise5.employee.Employee;
import gov.kotkov.mikhail.exercise5.employee.Manager;
import gov.kotkov.mikhail.exercise5.employee.Programmer;

public class BookkeeperTest {

	private Bookkeeper bookkeeper;
	
	@Before
	public void setup() {
		bookkeeper = new Bookkeeper();
	}
	
	@Test
	public void testCalculateSalariesForEmployees() {
		Programmer.setStandardWorkhours(160);
		Manager.setStandardWorkhours(160);
		Employee programmer1 = new Programmer();
		programmer1.setFirstName("Jonathan");
		programmer1.setLastName("Bill");
		programmer1.setWageRate(valueOf(600));
		programmer1.setActualWorkhours(120);
		Employee programmer2 = new Programmer();
		programmer2.setFirstName("Jonathan");
		programmer2.setLastName("BUll");
		programmer2.setWageRate(valueOf(600));
		programmer2.setActualWorkhours(180);
		Employee manager1 = new Manager();
		manager1.setFirstName("Pedro");
		manager1.setLastName("Gonsales");
		manager1.setWageRate(valueOf(400));
		manager1.setActualWorkhours(120);
		Employee manager2 = new Manager();
		manager2.setFirstName("Ruslan");
		manager2.setLastName("Boshirov");
		manager2.setWageRate(valueOf(400));
		manager2.setActualWorkhours(180);
		Map<Employee, BigDecimal> result = bookkeeper.calculateSalariesForEmployees(Arrays.asList(
				new Employee[]{programmer1, programmer2, manager1, manager2}));
		Map<Employee, BigDecimal> expected = new HashMap<>();
		expected.put(programmer1, valueOf(450).setScale(2, RoundingMode.HALF_UP));
		expected.put(programmer2, valueOf(675).setScale(2, RoundingMode.HALF_UP));
		expected.put(manager1, valueOf(300).setScale(2, RoundingMode.HALF_UP));
		expected.put(manager2, valueOf(400).setScale(2, RoundingMode.HALF_UP));
		assertEquals(expected, result);
	}
	
}
