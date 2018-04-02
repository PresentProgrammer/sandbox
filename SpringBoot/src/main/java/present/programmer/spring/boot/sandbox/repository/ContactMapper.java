package present.programmer.spring.boot.sandbox.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import present.programmer.spring.boot.sandbox.domain.Contact;
import present.programmer.spring.boot.sandbox.exception.DatabaseInteractionException;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
class ContactMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(final ResultSet resultSet, final int unusedRowNumber) {
        try {
            return mapDataToContact(resultSet);
        } catch (final SQLException e) {
            throw new DatabaseInteractionException("failed to map Contact data.", e);
        }
    }

    //
    // Auxiliary Methods
    //

    private static Contact mapDataToContact(final ResultSet resultSet) throws SQLException {
        final Contact contact = new Contact();
        contact.setId(resultSet.getLong("id"));
        contact.setFirstName(resultSet.getString("firstName"));
        contact.setLastName(resultSet.getString("lastName"));
        contact.setPhoneNumber(resultSet.getString("phoneNumber"));
        contact.setEmailAddress(resultSet.getString("emailAddress"));
        return contact;
    }
}
