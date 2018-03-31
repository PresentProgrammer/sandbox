package present.programmer.spring.boot.sandbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import present.programmer.spring.boot.sandbox.domain.Contact;
import present.programmer.spring.boot.sandbox.repository.ContactRepository;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/contacts")
class ContactController {

    private static final String CONTACTS = "contacts";
    private static final String CONTACTS_VIEW = "contacts";
    private static final String REDIRECT_TO_SHOW_ALL_CONTACTS = "redirect:/contacts";

    private final ContactRepository repository;

    @Autowired
    public ContactController(final ContactRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = GET)
    public String showAll(Map<String, Object> model) {
        model.put(CONTACTS, repository.readAll());
        return CONTACTS_VIEW;
    }

    @RequestMapping(method = POST)
    public String submit(final Contact contact) {
        repository.create(contact);
        return REDIRECT_TO_SHOW_ALL_CONTACTS;
    }
}
