package org.ynm.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
	public static void main(String[] args) {

		createEntry();
		createEntry();
		createEntry();
		createEntry();

		// modifyEntry();

		// deleteEntry();

		// deleteBoth();

		printCurrentEntries();
	}

	/**
	 * This method deletes the entries from both tables i.e. Employee and
	 * Contact when cascade-type is set to CascadeType.ALL
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

	/**
	 * 
	 */
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

	/**
	 * 
	 */
	private static void modifyEntry() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		Employee empl = em.find(Employee.class, 3L);
		if (empl != null) {
			empl.setName("Anoop");
		}

		em.getTransaction().commit();
		em.close();
	}

	/**
	 * 
	 */
	private static void printCurrentEntries() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select t from Employee t");
		List<Employee> employeeList = q.getResultList();
		for (Employee employee : employeeList) {
			System.out.println(employee.getName());

			// PaymentDetails is already fetched as it is OneToOne mapping
			System.out.println("Payment Details: " + employee.getPaymentDetails().getAccountNumber());

			// Till this line, the contacts are not fetched from the database
			// Following line retrieves the contacts from database
			List<Contact> contacts = employee.contacts;
			for (Contact contact : contacts) {
				System.out.println("Contact: " + contact);
			}
		}

		em.close();

	}

	/**
	 * 
	 */
	private static void createEntry() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = factory.createEntityManager();

		// create new Employee
		em.getTransaction().begin();
		Employee emp = new Employee();
		emp.setName("Name " + getRandomString());
		emp.setLocation("Location " + getRandomNumber());
		PaymentDetails pd = new PaymentDetails();
		pd.setAccountNumber("Acc " + getRandomNumber());
		pd.setBankName(getBankName());
		pd.setEmployee(emp);
		emp.setPaymentDetails(pd);

		emp.contacts = new ArrayList<Contact>();
		emp.contacts.add(new Contact(getRandomContact(), emp));
		emp.contacts.add(new Contact(getRandomContact(), emp));

		// read the existing entries and write to console
		Query q = em.createQuery("select t from Employee t");
		List<Employee> employeeList = q.getResultList();
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		// following are fixed steps
		em.persist(emp);
		em.getTransaction().commit();
		em.close();

	}

	private static String getRandomString() {
		return "Y-" + new Random(1000).nextLong()/100000;
	}

	private static String getRandomContact() {
		return "" + new Random(10000000).nextLong();
	}

	private static Long getRandomNumber() {
		return new Random(1000000).nextLong();
	}

	private static String getBankName() {
		if (System.currentTimeMillis() % 2 == 0) {
			return "ICICI";
		} else {
			return "HDFC";
		}
	}

}
