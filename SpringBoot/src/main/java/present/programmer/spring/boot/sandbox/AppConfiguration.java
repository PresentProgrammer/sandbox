package present.programmer.spring.boot.sandbox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import present.programmer.spring.boot.sandbox.domain.Contact;

@Configuration
public class AppConfiguration {

	@Bean
	public Contact contact() {
		Contact contact = new Contact();
		contact.setId(10L);
		contact.setFirstName("Arturik");
		contact.setLastName("Drubadurik");
		contact.setEmailAddress("Drubadurik@Arturik.com");
		contact.setPhoneNumber("+372 12345678");
		return contact;
	}
}