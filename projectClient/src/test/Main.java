package test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import de.tudresden.business.businessobjects.User;

public class Main {
	private static final String PERSISTENCE_UNIT_NAME = "ScheduleDS";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		// Read the existing entries and write to console
		Query q = em.createQuery("select u from User u");
		List<User> todoList = q.getResultList();
		for (User todo : todoList) {
			System.out.println(todo);
		}
		System.out.println("Size: " + todoList.size());

		// Create new todo
//		em.getTransaction().begin();
//		User todo = new User();
//		todo.setSummary("This is a test");
//		todo.setDescription("This is a test");
//		em.persist(todo);
//		em.getTransaction().commit();

		em.close();
	}
}