/**
 * This Groovy script can be run by Spring Boot CLI.
 * See https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-installing-the-cli
 * ... or Google "Spring Boot CLI".
 */
@RestController
@SuppressWarnings("GroovyUnusedDeclaration")
class ThisWillActuallyRun {

    @RequestMapping("/")
    static String home() {
        "Hello World!"
    }
}