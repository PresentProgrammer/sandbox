import org.junit.Test;

public class SomeTests {

    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    @Test
    public void m() {
        for (int i = -10; i <= 10; i++) {
            System.out.println(i + ", " + getDirectionIndex(i));
        }
    }

    private int getDirectionIndex(final int direction) {
        return (DIRECTIONS.length + direction % DIRECTIONS.length) % DIRECTIONS.length;
    }
}
