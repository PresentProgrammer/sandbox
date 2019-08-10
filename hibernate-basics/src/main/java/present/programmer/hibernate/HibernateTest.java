package present.programmer.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import present.programmer.hibernate.domain.User;
import present.programmer.hibernate.domain.User.UserBuilder;

import java.util.List;

public class HibernateTest {

	private static final Long ID = 9L;

	public static void main(String[] args) {
		try (final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
			try (final Session session = sessionFactory.openSession()) {
				session.beginTransaction();
				session.save(buildUser());
				session.getTransaction().commit();
			}
			try (final Session session = sessionFactory.openSession()) {
				List<User> users = session.createQuery("select u from User u order by u.id", User.class).getResultList();
				System.out.println(users.toString());
			}
		}
	}

	private static User buildUser() {
		final UserBuilder builder = User.builder();
		return builder.id(ID).name("Zopa").build();
	}
}
