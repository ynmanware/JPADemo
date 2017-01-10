package org.ynm.mappedby;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author Yogesh.Manware
 *
 */
@MappedSuperclass
public abstract class Employee {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY) does not work
	protected Integer employeeId;

}