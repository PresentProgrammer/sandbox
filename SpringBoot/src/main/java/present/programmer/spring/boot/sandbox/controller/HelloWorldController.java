package present.programmer.spring.boot.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SuppressWarnings("unused")
class HelloWorldController {

    private static final String HTML = "<html><body><a href=\"/artur\">Pipka</a></body></html>";

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return HTML;
    }

    @RequestMapping("/artur")
    @ResponseBody
    String artur() {
        return "Hello, Big Boss!";
    }
}
