package present.programmer.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import present.programmer.hibernate.domain.User;

public class HibernateTest {

	private static final int ID = 5;

	public static void main(String[] args) {
		try (final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
			final Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user());
			session.getTransaction().commit();
		}
	}

	private static User user() {
		final User user = new User();
		user.setId(ID);
		user.setName(ID + "th User");
		user.setAddress(ID + "th user's address");
		user.setJoined(new Date());
		user.setDescription(ID + "th description goes here");
		return user;
	}
}
