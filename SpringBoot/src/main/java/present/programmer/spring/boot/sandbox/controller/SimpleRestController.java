package present.programmer.spring.boot.sandbox.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import present.programmer.spring.boot.sandbox.domain.AjaxResponse;
import present.programmer.spring.boot.sandbox.domain.Availability;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static present.programmer.spring.boot.sandbox.domain.Availability.emailIsAvailable;
import static present.programmer.spring.boot.sandbox.domain.Availability.emailIsNotAvailable;
import static present.programmer.spring.boot.sandbox.domain.Availability.userNameIsAvailable;
import static present.programmer.spring.boot.sandbox.domain.Availability.userNameIsNotAvailable;

@RestController
@RequestMapping("/rest")
public class SimpleRestController {

    @RequestMapping(value = "/simple-ajax-response", produces = APPLICATION_JSON_UTF8_VALUE)
    public AjaxResponse simpleAjaxResponse(@RequestParam("userName") final String userName) {
        return new AjaxResponse("Hello, " + userName + ", from " + getClass().getSimpleName());
    }

    @RequestMapping(value = "/availability/user-name", produces = APPLICATION_JSON_UTF8_VALUE)
    public Availability checkIfUserNameAvailable(@RequestParam("userName") final String userName) {
        if (userName.startsWith("notAvailable")) {
            return userNameIsNotAvailable();
        } else {
            return userNameIsAvailable();
        }
    }

    @RequestMapping(value = "/availability/email/{email}", produces = APPLICATION_JSON_UTF8_VALUE)
    public Availability checkIfEmailAvailable(@PathVariable("email") final String email) {
        if (email.startsWith("notAvailable")) {
            return emailIsNotAvailable();
        } else {
            return emailIsAvailable();
        }
    }
}
