package gov.kotkov.mikhail.exercise5.employee;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Programmer")
public class Programmer extends Employee {

	@Override
	public BigDecimal calculateSalary() {
		return getWageRate().multiply(new BigDecimal(
				getWorkhoursPercentage()/100)).setScale(2, RoundingMode.HALF_UP);
	}

}
