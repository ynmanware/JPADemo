package org.ynm.inheritance;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {
	public Float hourlyWage;
}