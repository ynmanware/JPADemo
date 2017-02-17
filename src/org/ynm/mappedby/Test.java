package org.ynm.mappedby;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Yogesh.Manware
 * 
 * In this example, two separate tables will get created 
 * 
 * mysql> select * from fulltimeempl;
+------------+--------+
| EMPLOYEEID | SALARY |
+------------+--------+
|         21 |  10000 |
+------------+--------+

mysql> select * from parttimeempl;
+------------+------------+
| EMPLOYEEID | HOURLYWAGE |
+------------+------------+
|         54 |      50.12 |
+------------+------------+

	
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
