package present.programmer.spring.boot.sandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SuppressWarnings("unused")
public class HelloWorldController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/dimon")
    @ResponseBody
    String dimon() {
        return "Dimon Limon!";
    }

    @RequestMapping("/artur")
    @ResponseBody
    String artur() {
        return "Artur Perdun!";
    }
}
