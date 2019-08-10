package present.programmer.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import present.programmer.hibernate.domain.Book;
import present.programmer.hibernate.domain.Library;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

            doInJPA(() -> sessionFactory, session -> {
                Library _library = session.find(Library.class, 1L);
                _library.getBooks().add(book1);
                _library.getBooks().add(book2);
                session.flush();
                System.out.println();
            });

            doInJPA(() -> sessionFactory, session -> {
                Library _library = session.find(Library.class, 1L);
                _library.getBooks().remove(book1);
                session.flush();
            });

            doInJPA(() -> sessionFactory, session -> {
                Library _library = session.find(Library.class, 1L);
                assertEquals(1, _library.getBooks().size());
                assertTrue(_library.getBooks().contains(book2));
            });
        }
    }
}
