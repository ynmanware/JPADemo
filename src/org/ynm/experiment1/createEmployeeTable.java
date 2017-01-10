package org.ynm.experiment1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author Yogesh.Manware
 *
 */
public class createEmployeeTable {
	public static void main(String[] args) {

		// createEntry();

		// modifyEntry();

		deleteEntry();

		// deleteBoth();
	}

	/**
	 * This method deletes the entries from both tables i.e. Employee and
	 * Contact when cascadetype is set to CascadeType.ALL
	 */
	private static void deleteEntry() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		Employee empl = em.find(Employee.class, 2L);
		em.remove(empl);
		// following are fixed steps

		em.getTransaction().commit();
		em.close();
	}

	private static void deleteBoth() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		Employee empl = em.find(Employee.class, 1L);
		if (empl != null) {
			em.remove(empl);
		}

		Contact c1 = em.find(Contact.class, 1L);
		Contact c2 = em.find(Contact.class, 2L);

		em.remove(c1);
		em.remove(c2);

		// following are fixed steps

		em.getTransaction().commit();
		em.close();
	}

	private static void modifyEntry() {
		// TODO Auto-generated method stub

	}

	private static void createEntry() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = factory.createEntityManager();

		// create new Employee
		em.getTransaction().begin();
		Employee emp = new Employee();
		emp.setName("Yogesh");
		emp.setLocation("Molbourne");
		emp.contacts = new ArrayList<Contact>();
		emp.contacts.add(new Contact("12234", emp));
		emp.contacts.add(new Contact("44534", emp));

		// read the existing entries and write to console
		Query q = em.createQuery("select t from Employee t");
		List<Employee> employeeList = q.getResultList();
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("Size: " + employeeList.size());

		// following are fixed steps
		em.persist(emp);
		em.getTransaction().commit();
		em.close();

	}
}
