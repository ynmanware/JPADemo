package org.ynm.inheritance;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 
 * @author Yogesh.Manware
 *
 */
@Entity(name = "Emp")
public abstract class Employee {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) does not work
	protected Integer employeeId;

}