package org.ynm.inheritance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *  *  This hierarchy will still create just one table like following entry
+------------+------------------+--------+------------+
| EMPLOYEEID | DTYPE            | SALARY | HOURLYWAGE |
+------------+------------------+--------+------------+
|          7 | FullTimeEmployee |  10000 |       NULL |
|         29 | PartTimeEmployee |   NULL |      50.12 |
|         75 | FullTimeEmployee |  10000 |       NULL |
+------------+------------------+--------+------------+
 * 
 * @author Yogesh.Manware
 *
 */
public class Test {

	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPADemo");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		FullTimeEmployee empl = new FullTimeEmployee();
		empl.employeeId = (int) (Math.random() * 100);
		empl.salary = 10000;
		em.persist(empl);
		
		PartTimeEmployee pEmpl = new PartTimeEmployee();
		pEmpl.employeeId = (int) (Math.random() * 100);
		pEmpl.hourlyWage = 50.12F;
		em.persist(pEmpl);
		
		em.getTransaction().commit();
		em.close();
	}
}
