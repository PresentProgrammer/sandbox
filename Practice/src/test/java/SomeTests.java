import org.junit.Test;

public class SomeTests {

    @Test
    public void m() {
        final String str = new StringBuilder()
                .append('\\')
                .append("\\")
                .toString();
        System.out.println(str);
    }
}
