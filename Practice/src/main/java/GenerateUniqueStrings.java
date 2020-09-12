import java.util.ArrayList;
import java.util.List;

/**
 * Problem: mock interview
 * Time complexity: O(n!)
 * Space complexity: O(n ^ 2)
 **/
public class GenerateUniqueStrings {

    private static final char FIRST_CHAR = 'A';

    public List<String> generateStrings(int n) {
        return gen(new ArrayList<>(), n, "", 0);
    }

    private static List<String> gen(List<String> result, int n, String stringSoFar, int maxStep) {
        if (stringSoFar.length() == n) {
            result.add(stringSoFar);
            return result;
        }
        for (int i = 0; i <= maxStep; i++) {
            gen(result, n, stringSoFar + (char) (FIRST_CHAR + i), Math.max(maxStep, i + 1));
        }
        return result;
    }

    public static void main(final String[] args) {
        System.out.println(new GenerateUniqueStrings().generateStrings(4));
    }
}