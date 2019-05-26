package gov.kotkov.mikhail.exercise5.employee;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Employee {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(updatable = false, nullable = false)
	private long id;
	
	@Column(name = "first_name", nullable = false, length = 20)
	@NotEmpty
	@Size(max = 20)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 20)
	@NotEmpty
	@Size(max = 20)
	private String lastName;
	
	//wage for standard hours worked
	@Column(name = "wage_rate", nullable = false)
	@NotNull
	@Digits(integer=5, fraction=2)
	@Min(value = 0L, message = "The value must be positive")
	private BigDecimal wageRate;
	
	//actual hours worked
	@Column(name = "actual_workhours", nullable = false)
	@NotNull
	@Digits(integer=3, fraction=0)
	@Min(value = 0L, message = "The value must be positive")
	private int actualWorkhours;
	
	//standard amount of hours employee has to work
	@Getter
	@Setter
	private static int standardWorkhours;
	
	public void setWageRate(BigDecimal wageRate) {
		this.wageRate = wageRate.setScale(2, RoundingMode.HALF_UP);
	}
	
	public double getWorkhoursPercentage() {
		return ((double)actualWorkhours/standardWorkhours) * 100;
	}

	public abstract BigDecimal calculateSalary();
}
