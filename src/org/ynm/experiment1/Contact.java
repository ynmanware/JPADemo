package org.ynm.experiment1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contact {

	public Contact(String string, Employee emp) {
		this.number = string;
		this.employee = emp;
	}

	public Contact() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String number;

	private Employee employee;

}
