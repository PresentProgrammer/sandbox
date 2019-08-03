package present.programmer.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import present.programmer.hibernate.domain.User;
import present.programmer.hibernate.domain.User.UserBuilder;

public class HibernateTest {

	private static final int ID = 6;

	public static void main(String[] args) {
		try (final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
			try (final Session session = sessionFactory.openSession()) {
				session.beginTransaction();
				session.save(buildUser());
				session.getTransaction().commit();
			}
			try (final Session session = sessionFactory.openSession()) {
				User retrievedUser = session.get(User.class, ID);
				System.out.println(retrievedUser.toString());
			}
		}
	}

	private static User buildUser() {
		final UserBuilder builder = User.builder();
		return builder.id(ID).name("Peppa").build();
	}
}
