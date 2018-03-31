package present.programmer.spring.boot.sandbox.repository;

import org.springframework.stereotype.Repository;
import present.programmer.spring.boot.sandbox.domain.Contact;

import java.util.List;

@Repository
class StubContactRepository implements ContactRepository {

    @Override
    public List<Contact> readAll() {
        return null;
    }

    @Override
    public void create(final Contact contact) {

    }
}
