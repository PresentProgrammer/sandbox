package present.programmer.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import present.programmer.hibernate.domain.Book;
import present.programmer.hibernate.domain.Library;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;
import static org.junit.Assert.assertFalse;

public class HibernateTests {

    @Test
    public void sandboxTest() {
        try (final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            doInJPA(() -> sessionFactory, session -> {
                final Library library = new Library();
                library.setName("Artur's library");
                session.persist(library);
            });

            final Book book1 = new Book();
            book1.setTitle("High-Performance Java Persistence");

            final Book book2 = new Book();
            book2.setTitle("Java Persistence with Hibernate");

            final Library library =doInJPA(() -> sessionFactory, session -> {
                Library _library = session.find(Library.class, 1L);
                _library.getBooks().add(book1);
                _library.getBooks().add(book2);
                session.flush();
                return _library;
            });

            assertFalse(library.getBooks().contains(book1));
            assertFalse(library.getBooks().contains(book2));
            System.out.println("Hello!");
        }
    }
}
