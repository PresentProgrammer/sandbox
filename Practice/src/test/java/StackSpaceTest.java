public class StackSpaceTest {

    public void problemMainMethod() {
        stackTest(0, 0);
    }

    private static void stackTest(final int count, final int count2) {
        System.out.println(count);
        System.out.println(count2);
        stackTest(count + 1, count2 + 2);
    }

    public static void main(final String[] args) {
        new Template().problemMainMethod();
    }
}
