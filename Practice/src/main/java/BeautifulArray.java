import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #932
 * Time complexity: O()
 * Space complexity: O()
 **/
public class BeautifulArray {

    private List<Integer> a;
    private boolean[] used;

    public int[] beautifulArray(final int n) {
        this.used = new boolean[n];
        this.a = new ArrayList<>(n);
        for (int elem = 1; elem <= n; elem++) {
            insertIntoA(elem);
        }
        return aToIntArray();
    }

    private void insertIntoA(final int elem) {
        if (!used[elem - 1]) {
            for (int i = 0; i < a.size(); i++) {
                final int aj = elem * 2 - a.get(i);
                if (aj > 0 && aj <= used.length) {
                    System.out.println(aj);
                    insertIntoA(aj);
                }
            }
            a.add(elem);
            used[elem - 1] = true;
        }
    }

    private int[] aToIntArray() {
        final int[] array = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            array[i] = a.get(i);
        }
        return array;
    }

    public static void main(final String[] args) {
//        for (int n = 1; n <= 1000; n++) {
//            System.out.println(Arrays.toString(new BeautifulArray().beautifulArray(n)));
//        }
        System.out.println(Arrays.toString(new BeautifulArray().beautifulArray(12)));
    }
}