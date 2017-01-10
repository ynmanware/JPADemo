package org.ynm.mappedby;

import javax.persistence.Entity;

@Entity(name = "FullTimeEmpl")
public class FullTimeEmployee extends Employee {
	public Integer salary;
}