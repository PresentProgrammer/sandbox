package present.programmer.spring.boot.sandbox.repository;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import present.programmer.spring.boot.sandbox.exception.BadApplicationConfigurationException;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.Thread.currentThread;
import static org.apache.commons.io.Charsets.UTF_8;

@Component
class SqlQueries {

    private static final String SQL_FOLDER = "sql";
    private static final String SELECT_CONTACTS_SQL = SQL_FOLDER + "/select-contacts.sql";
    private static final String INSERT_CONTACT_SQL = SQL_FOLDER + "/insert-contact.sql";

    String selectContacts() {
        return getSql(SELECT_CONTACTS_SQL);
    }

    String insertContact() {
        return getSql(INSERT_CONTACT_SQL);
    }

    //
    // Auxiliary Methods
    //

    private static String getSql(final String path) {
        try {
            return getFileContent(path);
        } catch (final IOException e) {
            throw new BadApplicationConfigurationException("failed reading content of " + path, e);
        }
    }

    private static String getFileContent(final String path) throws IOException {
        final ClassLoader classLoader = currentThread().getContextClassLoader();
        final InputStream inputStream = classLoader.getResourceAsStream(path);
        return IOUtils.toString(inputStream, UTF_8);
    }
}
