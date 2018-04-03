package present.programmer.spring.boot.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/name/{userName}")
    @ResponseBody
    String greetUser(@PathVariable final String userName) {
        return "Hello, " + userName + "!";
    }

    @RequestMapping("/name/peppa")
    @ResponseBody
    String greetBigBoss() {
        return "Hello, Peppa Pig!";
    }
}
