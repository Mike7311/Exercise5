package gov.kotkov.mikhail.exercise5.employee;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Manager")
public class Manager extends Employee {

	@Override
	public BigDecimal calculateSalary() {
		double ratio = getWorkhoursPercentage()/100;
		return ratio > 1.0 ? getWageRate() 
				: getWageRate().multiply(valueOf(ratio)).setScale(2, RoundingMode.HALF_UP);
	}	
}
