package present.programmer.spring.boot.sandbox.repository;

import org.springframework.stereotype.Repository;
import present.programmer.spring.boot.sandbox.domain.Contact;

import java.util.ArrayList;
import java.util.List;

@Repository
class StubContactRepository implements ContactRepository {

    private final List<Contact> contacts = new ArrayList<>();

    @Override
    public List<Contact> readAll() {
        return contacts;
    }

    @Override
    public void create(final Contact contact) {
        contacts.add(contact);
    }
}
