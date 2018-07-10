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
@SuppressWarnings("unused")
class ContactController {

    private static final String CONTACTS = "contacts";
    private static final String CONTACTS_VIEW = "Contacts";
    private static final String ALTERNATIVE_CONTACT_FORM = "AlternativeContactForm";
    private static final String REDIRECT_TO_SHOW_ALL_CONTACTS = "redirect:/contacts";

    private final ContactRepository repository;

    @Autowired
    ContactController(final ContactRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = GET)
    String showAll(Map<String, Object> model) {
        model.put(CONTACTS, repository.readAll());
        return CONTACTS_VIEW;
    }

    @RequestMapping(method = POST)
    String submit(final Contact contact) {
        repository.create(contact);
        return REDIRECT_TO_SHOW_ALL_CONTACTS;
    }

    @RequestMapping("/ajax-sandbox/alternative-contact-form")
    String alternativeContactForm() {
        return ALTERNATIVE_CONTACT_FORM;
    }
}
