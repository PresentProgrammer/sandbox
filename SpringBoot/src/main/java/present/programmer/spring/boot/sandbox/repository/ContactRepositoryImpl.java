package present.programmer.spring.boot.sandbox.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import present.programmer.spring.boot.sandbox.domain.Contact;

import java.util.List;

@Repository
class ContactRepositoryImpl implements ContactRepository {

    private final JdbcOperations jdbc;
    private final SqlQueries sqlQueries;
    private final ContactMapper contactMapper;

    @Autowired
    ContactRepositoryImpl(final JdbcOperations jdbc, final SqlQueries sqlQueries, final ContactMapper contactMapper) {
        this.jdbc = jdbc;
        this.sqlQueries = sqlQueries;
        this.contactMapper = contactMapper;
    }

    @Override
    public List<Contact> readAll() {
        return jdbc.query(sqlQueries.selectContacts(), contactMapper);
    }

    @Override
    public void create(final Contact contact) {
        jdbc.update(sqlQueries.insertContact(), contact.getFirstName(), contact.getLastName(),
                contact.getPhoneNumber(), contact.getEmailAddress());
    }
}
