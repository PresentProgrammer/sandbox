package present.programmer.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import present.programmer.hibernate.domain.UserDetails;

public class HibernateTest {

	public static void main(String[] args) {
		try (final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
			final Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user());
			session.getTransaction().commit();
		}
	}

	private static UserDetails user() {
		final UserDetails user = new UserDetails();
		user.setUserId(3);
		user.setName("Third User");
		return user;
	}
}
