import org.springframework.jdbc.core.JdbcTemplate

@Grab("h2")

import java.sql.ResultSet
import org.springframework.jdbc.core.RowMapper

class ContactRepository {

    @Autowired
    JdbcTemplate jdbcTemplate

    List<Contact> readAll() {
        jdbcTemplate.query(
                "SELECT id, firstName, lastName, phoneNumber, emailAddress " +
                        "FROM CONTACTS " +
                        "ORDER BY firstName, lastName",
                new RowMapper<Contact>() {
                    Contact mapRow(ResultSet resultSet, int unusedRowNumber) {
                        new Contact(
                                id: resultSet.getLong(1),
                                firstName: resultSet.getString(2),
                                lastName: resultSet.getString(3),
                                phoneNumber: resultSet.getString(4),
                                emailAddress: resultSet.getString(5)
                        )
                    }
                })
    }

    void create(Contact contact) {
        jdbcTemplate.update(
                "INSERT INTO CONTACTS (firstName, lastName, phoneNumber, emailAddress) " +
                        "VALUES (?, ?, ?, ?)",
                contact.firstName, contact.lastName, contact.phoneNumber, contact.emailAddress)
    }
}