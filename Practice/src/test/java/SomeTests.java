import org.junit.Test;

public class SomeTests {

    @Test
    public void m() {
        int check = 0B1, result = 0;
        while (check != 0) {
            check <<= 1;
            System.out.println(check);
        }
    }
}
