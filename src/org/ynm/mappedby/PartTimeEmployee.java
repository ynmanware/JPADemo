package org.ynm.mappedby;

import javax.persistence.Entity;

@Entity(name = "PartTimeEmpl")
public class PartTimeEmployee extends Employee {
	public Float hourlyWage;
}