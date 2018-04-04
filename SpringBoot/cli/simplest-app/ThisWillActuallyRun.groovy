@RestController
class ThisWillActuallyRun {

    @RequestMapping("/")
    static String home() {
        "Hello World!"
    }
}