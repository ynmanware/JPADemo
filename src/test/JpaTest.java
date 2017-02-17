package test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.ynm.crud.Employee;

public class JpaTest {

	private static final String PERSISTENCE_UNIT_NAME = "JPADemo";
	private EntityManagerFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// read the existing entries
		Query q = em.createQuery("select m from Employee m");
		// Persons should be empty

		// do we have entries?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// No, so lets create new entries
		if (createNewEntries) {
			assertTrue(q.getResultList().size() == 0);
			for (int i = 0; i < 40; i++) {
				Employee empl = new Employee();
				empl.setName("Emp_" + i);
				empl.setLocation("VIC_" + i);
				em.persist(empl);
				// now persists the family person relationship
				em.persist(empl);
			}
		}

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();
	}

	@Test
	public void checkAvailablePeople() {

		// now lets check the database and see if the created entries are there
		// create a fresh, new EntityManager
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select t from Employee t");
		List<Employee> employeeList = q.getResultList();
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		em.close();
	}
}