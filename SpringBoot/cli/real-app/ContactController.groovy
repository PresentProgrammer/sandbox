import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

@Grab("thymeleaf-spring5")

@Controller
@RequestMapping("/cli/contacts")
class ContactController {

    @Autowired
    ContactRepository repository

    @RequestMapping(method = GET)
    String home(Map<String, Object> model) {
        model["contacts"] = repository.readAll()
        "home"
    }

    @RequestMapping(method = POST)
    String submit(Contact contact) {
        repository.create(contact);
        "redirect:/cli/contacts"
    }
}