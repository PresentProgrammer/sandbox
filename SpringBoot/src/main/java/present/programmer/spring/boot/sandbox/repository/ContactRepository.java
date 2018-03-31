package present.programmer.spring.boot.sandbox.repository;

import present.programmer.spring.boot.sandbox.domain.Contact;

import java.util.List;

public interface ContactRepository {

    List<Contact> readAll();

    void create(Contact contact);
}
