package org.ynm.crud.pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.ynm.crud.Contact;
import org.ynm.crud.Employee;
import org.ynm.crud.PaymentDetails;

/**
 * 
 * Demonstrate CRUD operations using JPA and mysql + JPQL
 * 
 * Database queries varies based on the type of mapping. Read the inline
 * comments in printCurrentEntries() method
 * 
 * To fetch the data eagerly, we can use fetch = FetchType.EAGER attribute
 * 
 * @author Yogesh.Manware
 *
 */
public class Test {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
	EntityManager em = factory.createEntityManager();

	public static void main(String[] args) {

		Test t = new Test();

		int startAt = 1;
		int pageSize = 5;
		int interation = 1;
		while (true) {

			System.out.println("Iteraction: " + interation++);

			if (!t.printCurrentEntries(startAt, pageSize)) {
				break;
			}

			startAt = startAt + pageSize;
		}

		t.em.close();
	}

	/**
	 * 
	 */
	private boolean printCurrentEntries(int startAt, int pageSize) {

		Query q = em.createQuery("select t from Employee t");
		q.setFirstResult(startAt);
		q.setMaxResults(pageSize);

		List<Employee> employeeList = q.getResultList();

		if (employeeList.isEmpty()) {
			return false;
		}

		for (Employee employee : employeeList) {
			System.out.println("employee Id: " + employee.getId());
		}

		return true;
	}
}
