package org.ynm.test1;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestConnection {
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
		System.out.println(factory.createEntityManager().isOpen());
	}
}
