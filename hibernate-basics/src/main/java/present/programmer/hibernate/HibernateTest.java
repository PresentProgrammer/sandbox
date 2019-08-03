package present.programmer.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import present.programmer.hibernate.domain.User;

public class HibernateTest {

	private static final int ID = 1;

	public static void main(String[] args) {
		try (final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
			try (final Session session = sessionFactory.openSession()) {
				session.beginTransaction();
				session.save(User.builder().id(ID).name("Peppa").build());
				session.getTransaction().commit();
			}
			try (final Session session = sessionFactory.openSession()) {
				User retrievedUser = session.get(User.class, ID);
				System.out.println(retrievedUser.toString());
			}
		}
	}
}
